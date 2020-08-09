package service;

import filter.UsuarioFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.TipoUsuario;
import model.Usuario;
import org.hibernate.query.criteria.internal.OrderImpl;
import util.JPAConnectionFactory;

public class UsuarioDAO extends DAO{
    
    private EntityManager em;
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public List<Usuario> filtrados(UsuarioFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Usuario> typed = em.createQuery(pesquisa(filtro)).setFirstResult(filtro.getPrimeiroRegistro()).setMaxResults(filtro.getQuantidadeRegistros());
        em.close();
        return typed.getResultList();
    }
    
    
    private CriteriaQuery<Usuario> pesquisa (UsuarioFilter filtro){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Usuario> query = em.getCriteriaBuilder().createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);
        Path<String> nome = root.<String>get("nome");
        Path<Boolean> acesso = root.<Boolean>get("acesso");
        Predicate acessoFiltro = em.getCriteriaBuilder().equal(acesso, filtro.getAcesso());
        if (filtro.getNome() != null) {
            Predicate nomeFiltro = em.getCriteriaBuilder().like(nome, "%" + filtro.getNome() + "%");
            query.where(nomeFiltro, acessoFiltro);
        } else{
            query.where(acessoFiltro);
        }
        OrderImpl ordem = new OrderImpl(nome, filtro.isAscendente());
        query.orderBy(ordem);
        em.close();
        return query;
    }

    public int quantidadeFiltrados(UsuarioFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Usuario> typed = em.createQuery(pesquisa(filtro));
        em.close();
        return typed.getResultList().size();
    }
    
    public List<TipoUsuario> carregarTipos(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<TipoUsuario> query = em.getCriteriaBuilder().createQuery(TipoUsuario.class);
        Root<TipoUsuario> root = query.from(TipoUsuario.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<TipoUsuario> typed = em.createQuery(query);
        List<TipoUsuario> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public TipoUsuario buscarTipo(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        TipoUsuario tipo = em.find(TipoUsuario.class, id);
        em.close();
        return tipo;
    }
}