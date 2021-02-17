package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Especificacao;
import util.JPAConnectionFactory;

public class EspecificacaoDAO extends DAO{
    
    private EntityManager em;

    public EspecificacaoDAO() {
        super(Especificacao.class);
    }
    
    public boolean verifica(Especificacao especificacao){
        boolean emUso = false;
        String jpql = "select e from Especificacao e where exists (select m from Material m where m.especificacao.id = :pEspecificacao)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<Especificacao> query = em.createQuery(jpql, Especificacao.class);
	query.setParameter("pEspecificacao", especificacao.getId());
        query.setMaxResults(1);
	try {
            Especificacao resultado =  query.getSingleResult();
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