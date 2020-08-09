package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.TipoUsuario;
import util.JPAConnectionFactory;

public class TipoUsuarioDAO extends DAO{
    
    private EntityManager em;

    public TipoUsuarioDAO() {
        super(TipoUsuario.class);
    }
    
    public boolean verifica(TipoUsuario tipo){
        boolean emUso = false;
        String jpql = "select t from TipoUsuario t where exists (select u from Usuario u where u.tipo.id = :pTipo)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<TipoUsuario> query = em.createQuery(jpql, TipoUsuario.class);
	query.setParameter("pTipo", tipo.getId());
        query.setMaxResults(1);
	try {
            TipoUsuario resultado =  query.getSingleResult();
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