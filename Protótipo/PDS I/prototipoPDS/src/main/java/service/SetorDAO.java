package service;

import filter.SetorFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Filial;
import model.Responsavel;
import model.Setor;
import org.hibernate.query.criteria.internal.OrderImpl;
import util.JPAConnectionFactory;

public class SetorDAO extends DAO{
    
    private EntityManager em;
    
    public SetorDAO() {
        super(Setor.class);
    }
    
    public List<Setor> filtrados(SetorFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Setor> typed = em.createQuery(pesquisa(filtro)).setFirstResult(filtro.getPrimeiroRegistro()).setMaxResults(filtro.getQuantidadeRegistros());
        em.close();
        return typed.getResultList();
    }
    
    
    private CriteriaQuery<Setor> pesquisa (SetorFilter filtro){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Setor> query = em.getCriteriaBuilder().createQuery(Setor.class);
        Root<Setor> root = query.from(Setor.class);
        Path<String> nome = root.<String>get("nome");
        if (filtro.getNome() != null) {
            Predicate nomeFiltro = em.getCriteriaBuilder().like(nome, "%" + filtro.getNome() + "%");
            query.where(nomeFiltro);
        }
        OrderImpl ordem = new OrderImpl(nome, filtro.isAscendente());
        query.orderBy(ordem);
        em.close();
        return query;
    }

    public int quantidadeFiltrados(SetorFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Setor> typed = em.createQuery(pesquisa(filtro));
        em.close();
        return typed.getResultList().size();
    }
    
    public List<Responsavel> carregarResponsaveis(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Responsavel> query = em.getCriteriaBuilder().createQuery(Responsavel.class);
        Root<Responsavel> root = query.from(Responsavel.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<Responsavel> typed = em.createQuery(query);
        List<Responsavel> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public List<Filial> carregarFiliais(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Filial> query = em.getCriteriaBuilder().createQuery(Filial.class);
        Root<Filial> root = query.from(Filial.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<Filial> typed = em.createQuery(query);
        List<Filial> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public Responsavel buscarResponsavel(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        Responsavel responsavel = em.find(Responsavel.class, id);
        em.close();
        return responsavel;
    }
    
    public Filial buscarFilial(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        Filial filial = em.find(Filial.class, id);
        em.close();
        return filial;
    }
    
    public boolean verifica(Setor setor){
        boolean emUso = false;
        String jpql = "select s from Setor s where exists (select i from ItemInventario i where i.setor.id = :pSetor) "
                + "or exists (select ma from Material ma where ma.setorAtual.id = :pSetor) "
                + "or exists (select mo from Movimentacao mo where mo.setorNovo.id = :pSetor or mo.setorAntigo.id = :pSetor)";
        em = JPAConnectionFactory.getEntityManager();
	TypedQuery<Setor> query = em.createQuery(jpql, Setor.class);
	query.setParameter("pSetor", setor.getId());
        query.setMaxResults(1);
	try {
            Setor resultado =  query.getSingleResult();
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