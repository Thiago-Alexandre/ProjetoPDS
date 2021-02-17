package service;

import filter.AquisicaoFilter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;

public class AquisicaoPaginator<Aquisicao> extends LazyDataModel<Aquisicao> implements Serializable{
    
    private AquisicaoFilter filtro;
    private AquisicaoDAO dao;

    public AquisicaoPaginator(AquisicaoFilter filtro) {
        this.filtro = filtro;
        dao = new AquisicaoDAO();
    }
    
    @Override
    public List<Aquisicao> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filtro.setPrimeiroRegistro(first);
        filtro.setQuantidadeRegistros(pageSize);
        filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
        filtro.setPropriedadeOrdenacao(sortField);
        setRowCount(dao.quantidadeFiltrados(filtro));
        return (List<Aquisicao>) dao.filtrados(filtro);
    }
}