package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.FormaBaixa;
import util.JPAConnectionFactory;

public class FormaBaixaDAO extends DAO{
    
    private EntityManager em;

    public FormaBaixaDAO() {
        super(FormaBaixa.class);
    }
    
    public boolean verifica(FormaBaixa forma){
        boolean emUso = false;
        String jpql = "select f from FormaBaixa f where exists (select b from Baixa b where b.forma.id = :pForma)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<FormaBaixa> query = em.createQuery(jpql, FormaBaixa.class);
	query.setParameter("pForma", forma.getId());
        query.setMaxResults(1);
	try {
            FormaBaixa resultado =  query.getSingleResult();
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