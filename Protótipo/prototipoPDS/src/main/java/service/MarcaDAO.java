package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Marca;
import util.JPAConnectionFactory;

public class MarcaDAO extends DAO{
    
    private EntityManager em;

    public MarcaDAO() {
        super(Marca.class);
    }
    
    public boolean verifica(Marca marca){
        boolean emUso = false;
        String jpql = "select m from Marca m where exists (select ma from Material ma where ma.marca.id = :pMarca)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<Marca> query = em.createQuery(jpql, Marca.class);
	query.setParameter("pMarca", marca.getId());
        query.setMaxResults(1);
	try {
            Marca resultado =  query.getSingleResult();
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