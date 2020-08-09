package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Filial;
import util.JPAConnectionFactory;

public class FilialDAO extends DAO{
    
    private EntityManager em;

    public FilialDAO() {
        super(Filial.class);
    }
    
    public boolean verifica(Filial filial){
        boolean emUso = false;
        String jpql = "select f from Filial f where exists (select s from Setor s where s.filial.id = :pFilial)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<Filial> query = em.createQuery(jpql, Filial.class);
	query.setParameter("pFilial", filial.getId());
        query.setMaxResults(1);
	try {
            Filial resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}finally{
            em.close();
        }
        return emUso;
    }
}