package service;

import filter.BasicoFilter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.query.criteria.internal.OrderImpl;
import util.JPAConnectionFactory;

public class DAO<T> implements Serializable{
    
    private final Class<T> classe;
    private EntityManager em;

    public DAO(Class<T> classe) {
	this.classe = classe;
    }
    
    public void adiciona(T t) {
        em = JPAConnectionFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public void remove(T t) {
        em = JPAConnectionFactory.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(t));
        em.getTransaction().commit();
        em.close();
    }

    public void atualiza(T t) {
        em = JPAConnectionFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    public List<T> lista() {
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> lista = em.createQuery(query).getResultList();
        em.close();
        return lista;
    }

    public T buscaPorId(Integer id) {
        em = JPAConnectionFactory.getEntityManager();
        T instancia = em.find(classe, id);
        em.close();
        return instancia;
    }
    
    public List<T> filtrados(BasicoFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<T> typed = em.createQuery(pesquisa(filtro)).setFirstResult(filtro.getPrimeiroRegistro()).setMaxResults(filtro.getQuantidadeRegistros());
        em.close();
        return typed.getResultList();
    }
    
    private CriteriaQuery<T> pesquisa (BasicoFilter filtro){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        Root<T> root = query.from(classe);
        Path<String> nome = root.<String>get("nome");
        if (filtro.getNome() != null) {
            Predicate nomeFiltro = em.getCriteriaBuilder().like(nome, "%" + filtro.getNome() + "%");
            query.where(nomeFiltro);
        }
        OrderImpl ordem = new OrderImpl(nome, filtro.isAscendente());
        query.orderBy(ordem);
        em.close();
        return query;
    }

    public int quantidadeFiltrados(BasicoFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<T> typed = em.createQuery(pesquisa(filtro));
        em.close();
        return typed.getResultList().size();
    }
    
    
}