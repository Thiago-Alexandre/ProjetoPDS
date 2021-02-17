package service;

import filter.UsuarioFilter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;

public class UsuarioPaginator<Usuario> extends LazyDataModel<Usuario> implements Serializable{
    
    private UsuarioFilter filtro;
    private UsuarioDAO dao;

    public UsuarioPaginator(UsuarioFilter filtro) {
        this.filtro = filtro;
        dao = new UsuarioDAO();
    }
    
    @Override
    public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filtro.setPrimeiroRegistro(first);
        filtro.setQuantidadeRegistros(pageSize);
        filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
        filtro.setPropriedadeOrdenacao(sortField);
        setRowCount(dao.quantidadeFiltrados(filtro));
        return (List<Usuario>) dao.filtrados(filtro);
    }
}