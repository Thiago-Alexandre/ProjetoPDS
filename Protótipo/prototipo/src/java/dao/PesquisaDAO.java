package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.*;
import util.JPAUtil;

public class PesquisaDAO {
    
    public List<TipoUsuario> tiposUsuarioPorNome(String nome) {
        String jpql = "select t from TipoUsuario t where t.nome like :pNome order by t.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<TipoUsuario> lista;
	TypedQuery<TipoUsuario> query = em.createQuery(jpql, TipoUsuario.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<TipoMaterial> tiposMaterialPorNome(String nome) {
        String jpql = "select t from TipoMaterial t where t.nome like :pNome order by t.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<TipoMaterial> lista;
	TypedQuery<TipoMaterial> query = em.createQuery(jpql, TipoMaterial.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<TipoBaixa> tiposBaixaPorNome(String nome) {
        String jpql = "select t from TipoBaixa t where t.nome like :pNome order by t.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<TipoBaixa> lista;
	TypedQuery<TipoBaixa> query = em.createQuery(jpql, TipoBaixa.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<TipoAquisicao> tiposAquisicaoPorNome(String nome) {
        String jpql = "select t from TipoAquisicao t where t.nome like :pNome order by t.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<TipoAquisicao> lista;
	TypedQuery<TipoAquisicao> query = em.createQuery(jpql, TipoAquisicao.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<TipoMovimentacao> tiposMovimentacaoPorNome(String nome) {
        String jpql = "select t from TipoMovimentacao t where t.nome like :pNome order by t.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<TipoMovimentacao> lista;
	TypedQuery<TipoMovimentacao> query = em.createQuery(jpql, TipoMovimentacao.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }

    public List<Situacao> situacaoPorNome(String nome) {
        String jpql = "select s from Situacao s where s.nome like :pNome order by s.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Situacao> lista;
	TypedQuery<Situacao> query = em.createQuery(jpql, Situacao.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Fornecedor> fornecedoresPorNome(String nome) {
        String jpql = "select f from Fornecedor f where f.nome like :pNome order by f.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Fornecedor> lista;
	TypedQuery<Fornecedor> query = em.createQuery(jpql, Fornecedor.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Filial> filiaisPorNome(String nome) {
        String jpql = "select f from Filial f where f.nome like :pNome order by f.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Filial> lista;
	TypedQuery<Filial> query = em.createQuery(jpql, Filial.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Setor> setoresPorNome(String nome) {
        String jpql = "select s from Setor s where s.nome like :pNome order by s.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Setor> lista;
	TypedQuery<Setor> query = em.createQuery(jpql, Setor.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Responsavel> responsaveisPorNome(String nome) {
        String jpql = "select r from Responsavel r where r.nome like :pNome order by r.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Responsavel> lista;
	TypedQuery<Responsavel> query = em.createQuery(jpql, Responsavel.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Usuario> usuariosPorNome(String nome) {
        String jpql = "select u from Usuario u where u.nome like :pNome order by u.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Usuario> lista;
	TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Especificacao>  especificacoesPorNome(String nome) {
        String jpql = "select e from Especificacao e where e.nome like :pNome order by e.nome";
        EntityManager em = JPAUtil.getEntityManager();
        List<Especificacao> lista;
	TypedQuery<Especificacao> query = em.createQuery(jpql, Especificacao.class);
	query.setParameter("pNome", "%"+nome+"%");
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Baixa>  baixasPorData() {
        String jpql = "select b from Baixa b order by b.data desc";
        EntityManager em = JPAUtil.getEntityManager();
        List<Baixa> lista;
	TypedQuery<Baixa> query = em.createQuery(jpql, Baixa.class);
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Aquisicao>  aquisicoesPorData() {
        String jpql = "select a from Aquisicao a order by a.data desc";
        EntityManager em = JPAUtil.getEntityManager();
        List<Aquisicao> lista;
	TypedQuery<Aquisicao> query = em.createQuery(jpql, Aquisicao.class);
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Movimentacao>  movimentacoesPorData() {
        String jpql = "select m from Movimentacao m order by m.data desc";
        EntityManager em = JPAUtil.getEntityManager();
        List<Movimentacao> lista;
	TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<Inventario>  inventariosPorData() {
        String jpql = "select i from Inventario i order by i.data desc";
        EntityManager em = JPAUtil.getEntityManager();
        List<Inventario> lista;
	TypedQuery<Inventario> query = em.createQuery(jpql, Inventario.class);
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<ItemInventario>  itensPorInventario(Inventario inventario) {
        String jpql = "select i from ItemInventario i where i.inventario = :pInventario";
        EntityManager em = JPAUtil.getEntityManager();
        List<ItemInventario> lista;
	TypedQuery<ItemInventario> query = em.createQuery(jpql, ItemInventario.class);
        query.setParameter("pInventario", inventario);
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
    
    public List<AlteracaoInventario>  alteracoesPorInventario(Inventario inventario) {
        String jpql = "select a from AlteracaoInventario a where a.inventario = :pInventario";
        EntityManager em = JPAUtil.getEntityManager();
        List<AlteracaoInventario> lista;
	TypedQuery<AlteracaoInventario> query = em.createQuery(jpql, AlteracaoInventario.class);
        query.setParameter("pInventario", inventario);
	try {
            lista =  query.getResultList();
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
            lista = null;
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return lista;
    }
}