package service;

import filter.MaterialFilter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;

public class MaterialPaginator<Material> extends LazyDataModel<Material> implements Serializable{
    
    private MaterialFilter filtro;
    private MaterialDAO dao;

    public MaterialPaginator(MaterialFilter filtro) {
        this.filtro = filtro;
        dao = new MaterialDAO();
    }
    
    @Override
    public List<Material> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filtro.setPrimeiroRegistro(first);
        filtro.setQuantidadeRegistros(pageSize);
        filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
        filtro.setPropriedadeOrdenacao(sortField);
        setRowCount(dao.quantidadeFiltrados(filtro));
        return (List<Material>) dao.filtrados(filtro);
    }
}