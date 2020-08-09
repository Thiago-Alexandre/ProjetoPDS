package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Responsavel;
import util.JPAConnectionFactory;

public class ResponsavelDAO extends DAO{
    
    private EntityManager em;

    public ResponsavelDAO() {
        super(Responsavel.class);
    }
    
    public boolean verifica(Responsavel responsavel){
        boolean emUso = false;
        String jpql = "select r from Responsavel r where exists (select ma from Material ma where ma.responsavelAtual.id = :pResponsavel)"
                + " or exists (select i from ItemInventario i where i.responsavel.id = :pResponsavel)"
                + " or exists (select s from Setor s where s.responsavel.id = :pResponsavel)"
                + " or exists (select mo from Movimentacao mo where mo.responsavelAntigo.id = :pResponsavel or mo.responsavelNovo.id = :pResponsavel)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<Responsavel> query = em.createQuery(jpql, Responsavel.class);
	query.setParameter("pResponsavel", responsavel.getId());
        query.setMaxResults(1);
	try {
            Responsavel resultado =  query.getSingleResult();
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