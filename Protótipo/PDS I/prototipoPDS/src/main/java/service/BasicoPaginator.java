package service;

import filter.BasicoFilter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;

public class BasicoPaginator<T> extends LazyDataModel<T> implements Serializable{
    
    private final Class<T> classe;
    private BasicoFilter filtro;
    private DAO dao;

    public BasicoPaginator(Class<T> classe, BasicoFilter filtro) {
        this.classe = classe;
        this.filtro = filtro;
        dao = new DAO(this.classe);
    }
    
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filtro.setPrimeiroRegistro(first);
        filtro.setQuantidadeRegistros(pageSize);
        filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
        filtro.setPropriedadeOrdenacao(sortField);
        setRowCount(dao.quantidadeFiltrados(filtro));
        return dao.filtrados(filtro);
    }
}