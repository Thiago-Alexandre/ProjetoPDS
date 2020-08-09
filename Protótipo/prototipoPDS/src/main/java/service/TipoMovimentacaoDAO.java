package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.TipoMovimentacao;
import util.JPAConnectionFactory;

public class TipoMovimentacaoDAO extends DAO{
    
    private EntityManager em;

    public TipoMovimentacaoDAO() {
        super(TipoMovimentacao.class);
    }
    
    public boolean verifica(TipoMovimentacao tipo){
        boolean emUso = false;
        String jpql = "select t from TipoMovimentacao t where exists (select m from Movimentacao m where m.tipo.id = :pTipo)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<TipoMovimentacao> query = em.createQuery(jpql, TipoMovimentacao.class);
	query.setParameter("pTipo", tipo.getId());
        query.setMaxResults(1);
	try {
            TipoMovimentacao resultado =  query.getSingleResult();
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