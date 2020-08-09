package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.FormaAquisicao;
import util.JPAConnectionFactory;

public class FormaAquisicaoDAO extends DAO{
    
    private EntityManager em;

    public FormaAquisicaoDAO() {
        super(FormaAquisicao.class);
    }
    
    public boolean verifica(FormaAquisicao forma){
        boolean emUso = false;
        String jpql = "select f from FormaAquisicao f where exists (select a from Aquisicao a where a.forma.id = :pForma)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<FormaAquisicao> query = em.createQuery(jpql, FormaAquisicao.class);
	query.setParameter("pForma", forma.getId());
        query.setMaxResults(1);
	try {
            FormaAquisicao resultado =  query.getSingleResult();
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