package service;

import filter.MaterialFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import model.Especificacao;
import model.Marca;
import model.Material;
import org.hibernate.query.criteria.internal.OrderImpl;
import util.JPAConnectionFactory;

public class MaterialDAO extends DAO{
    
    private EntityManager em;
    
    public MaterialDAO() {
        super(Material.class);
    }
    
    public List<Material> filtrados(MaterialFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Material> typed = em.createQuery(pesquisa(filtro)).setFirstResult(filtro.getPrimeiroRegistro()).setMaxResults(filtro.getQuantidadeRegistros());
        em.close();
        return typed.getResultList();
    }
    
    
    private CriteriaQuery<Material> pesquisa (MaterialFilter filtro){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Material> query = em.getCriteriaBuilder().createQuery(Material.class);
        em.close();
        return query;
    }

    public int quantidadeFiltrados(MaterialFilter filtro) {
        em = JPAConnectionFactory.getEntityManager();
        TypedQuery<Material> typed = em.createQuery(pesquisa(filtro));
        em.close();
        return typed.getResultList().size();
    }
    
    public List<Especificacao> carregarEspecificacoes(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Especificacao> query = em.getCriteriaBuilder().createQuery(Especificacao.class);
        Root<Especificacao> root = query.from(Especificacao.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<Especificacao> typed = em.createQuery(query);
        List<Especificacao> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public List<Marca> carregarMarcas(){
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Marca> query = em.getCriteriaBuilder().createQuery(Marca.class);
        Root<Marca> root = query.from(Marca.class);
        Path<String> nome = root.<String>get("nome");
        OrderImpl ordem = new OrderImpl(nome, true);
        query.orderBy(ordem);
        TypedQuery<Marca> typed = em.createQuery(query);
        List<Marca> lista = typed.getResultList();
        em.close();
        return lista;
    }
    
    public Especificacao buscarEspecificacao(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        Especificacao especificacao = em.find(Especificacao.class, id);
        em.close();
        return especificacao;
    }
    
    public Marca buscarMarca(Integer id){
        em = JPAConnectionFactory.getEntityManager();
        Marca marca = em.find(Marca.class, id);
        em.close();
        return marca;
    }
}