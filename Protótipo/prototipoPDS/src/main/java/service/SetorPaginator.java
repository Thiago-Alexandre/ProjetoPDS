package service;

import filter.SetorFilter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;

public class SetorPaginator<Setor> extends LazyDataModel<Setor> implements Serializable{
    
    private SetorFilter filtro;
    private SetorDAO dao;

    public SetorPaginator(SetorFilter filtro) {
        this.filtro = filtro;
        dao = new SetorDAO();
    }
    
    @Override
    public List<Setor> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filtro.setPrimeiroRegistro(first);
        filtro.setQuantidadeRegistros(pageSize);
        filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
        filtro.setPropriedadeOrdenacao(sortField);
        setRowCount(dao.quantidadeFiltrados(filtro));
        return (List<Setor>) dao.filtrados(filtro);
    }
}