package service;

import filter.AquisicaoFilter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import model.FormaAquisicao;
import model.Aquisicao;
import model.Usuario;
import org.hibernate.query.criteria.internal.OrderImpl;
import util.JPAConnectionFactory;

public class AquisicaoDAO extends DAO{
    
    private EntityManager em;
    
    public AquisicaoDAO() {
        super(Aquisicao.class);
    }
    
    public List<Aquisicao> filtrados(AquisicaoFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Aquisicao> typed = em.createQuery(pesquisa(filtro)).setFirstResult(filtro.getPrimeiroRegistro()).setMaxResults(filtro.getQuantidadeRegistros());
        em.close();
        return typed.getResultList();
    }
    
    
    private CriteriaQuery<Aquisicao> pesquisa (AquisicaoFilter filtro){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Aquisicao> query = em.getCriteriaBuilder().createQuery(Aquisicao.class);
        Root<Aquisicao> root = query.from(Aquisicao.class);
        Path<Date> data = root.<Date>get("data");
        OrderImpl ordem = new OrderImpl(data, filtro.isAscendente());
        query.orderBy(ordem);
        em.close();
        return query;
    }

    public int quantidadeFiltrados(AquisicaoFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Aquisicao> typed = em.createQuery(pesquisa(filtro));
        em.close();
        return typed.getResultList().size();
    }
    
    public List<FormaAquisicao> carregarFormas(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<FormaAquisicao> query = em.getCriteriaBuilder().createQuery(FormaAquisicao.class);
        Root<FormaAquisicao> root = query.from(FormaAquisicao.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<FormaAquisicao> typed = em.createQuery(query);
        List<FormaAquisicao> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public List<Usuario> carregarUsuarios(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Usuario> query = em.getCriteriaBuilder().createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<Usuario> typed = em.createQuery(query);
        List<Usuario> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public FormaAquisicao buscarForma(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        FormaAquisicao forma = em.find(FormaAquisicao.class, id);
        em.close();
        return forma;
    }
    
    public Usuario buscarUsuario(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }
}