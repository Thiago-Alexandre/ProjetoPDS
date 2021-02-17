package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.EstadoFisico;
import util.JPAConnectionFactory;

public class EstadoFisicoDAO extends DAO{
    
    private EntityManager em;

    public EstadoFisicoDAO() {
        super(EstadoFisico.class);
    }
    
    public boolean verifica(EstadoFisico estado){
        boolean emUso = false;
        String jpql = "select e from EstadoFisico e where exists (select ip from ItemPatrimonio ip where ip.estado.id = :pEstadoFisico)"
                + " or exists (select i from ItemInventario i where i.estado.id = :pEstadoFisico)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<EstadoFisico> query = em.createQuery(jpql, EstadoFisico.class);
	query.setParameter("pEstadoFisico", estado.getId());
        query.setMaxResults(1);
	try {
            EstadoFisico resultado =  query.getSingleResult();
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