package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import util.JPAUtil;

public class DAO<T> {
    
    private final Class<T> classe;

    public DAO(Class<T> classe) {
	this.classe = classe;
    }
    
    public void adiciona(T t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        System.out.println("Entity fechada");
    }

    public void remove(T t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(t));
        em.getTransaction().commit();
        em.close();
        System.out.println("Entity fechada");
    }

    public void atualiza(T t) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
        System.out.println("Entity fechada");
    }

    public List<T> lista() {
        EntityManager em = JPAUtil.getEntityManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> lista = em.createQuery(query).getResultList();
        em.close();
        System.out.println("Entity fechada");
        return lista;
    }

    public T buscaPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        T instancia = em.find(classe, id);
        em.close();
        System.out.println("Entity fechada");
        return instancia;
    }
}