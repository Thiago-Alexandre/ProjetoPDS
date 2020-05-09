package util;

import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/*Classe para utilizar paginação real em dataTable*/

public class QueryDataModel<T> extends LazyDataModel<T> {
    
    private String jpql;
    
    public QueryDataModel(String jpql, String jpqlCount) {
        this.jpql = jpql;
        Long count = (Long) JPAUtil.getEntityManager().createQuery(jpqlCount).getSingleResult();
        setRowCount(count.intValue());
    }
    
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return JPAUtil.getEntityManager().createQuery(jpql)
        .setFirstResult(first)
        .setMaxResults(pageSize)
        .getResultList();
    }
    /*
    
    Métodos u precisam ser sobrescritos para usar paginação real junto com função de seleção do dataTable
    
    @Override
    public T getRowData(String rowKey){
        
    }
    
    @Override
    public String getRowKey(T objeto){
        
    }
    */
}