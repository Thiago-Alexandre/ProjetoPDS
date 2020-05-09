package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.*;
import util.JPAUtil;

public class VerificadorDAO {
    
    public boolean usuarioEmUso(Usuario usuario) {
        Boolean emUso = false;
        String jpql = "select u from Usuario u where exists (select i from Inventario i where i.usuario = :pUsuario) "
                + "or exists (select a from Aquisicao a where a.usuario = :pUsuario) "
                + "or exists (select b from Baixa b where b.usuario = :pUsuario) "
                + "or exists (select m from Movimentacao m where m.usuario = :pUsuario)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
	query.setParameter("pUsuario", usuario);
	try {
            Usuario resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}
	em.close();
        System.out.println("Entity fechada");
	return emUso;
    }
    
    public boolean setorEmUso(Setor setor) {
        Boolean emUso = false;
        String jpql = "select s from Setor s where exists (select i from ItemInventario i where i.setor = :pSetor) "
                + "or exists (select p from Patrimonio p where p.setorAtual = :pSetor) "
                + "or exists (select m from Movimentacao m where m.setorNovo = :pSetor or m.setorAntigo = :pSetor) "
                + "or exists (select a from AlteracaoInventario a where a.setor = :pSetor)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Setor> query = em.createQuery(jpql, Setor.class);
	query.setParameter("pSetor", setor);
	try {
            Setor resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}
	em.close();
        System.out.println("Entity fechada");
	return emUso;
    }
    
    public boolean situacaoEmUso(Situacao situacao) {
        Boolean emUso = false;
        String jpql = "select s from Situacao s where exists (select a from AlteracaoInventario a where a.situacao = :pSituacao)"
                + " or exists (select i from ItemInventario i where i.situacao = :pSituacao)"
                + " or exists (select p from Patrimonio p where p.situacaoAtual = :pSituacao)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Situacao> query = em.createQuery(jpql, Situacao.class);
	query.setParameter("pSituacao", situacao);
        query.setMaxResults(1);
	try {
            Situacao resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            em.close();
            System.out.println("Erro! " + ex);
	}
	return emUso;
    }
    
    public Boolean filialEmUso(Filial filial) {
        Boolean emUso = false;
        String jpql = "select f from Filial f where exists (select s from Setor s where s.tipo = :pFilial)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Filial> query = em.createQuery(jpql, Filial.class);
	query.setParameter("pFilial", filial);
        query.setMaxResults(1);
	try {
            Filial resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean responsavelEmUso(Responsavel responsavel) {
        Boolean emUso = false;
        String jpql = "select r from Responsavel r where exists (select s from Setor s where s.tipo = :pResponsavel)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Responsavel> query = em.createQuery(jpql, Responsavel.class);
	query.setParameter("pResponsavel", responsavel);
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
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean fornecedorEmUso(Fornecedor fornecedor) {
        Boolean emUso = false;
        String jpql = "select f from Fornecedor f where exists (select m from Material m where m.tipo = :pFornecedor)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Fornecedor> query = em.createQuery(jpql, Fornecedor.class);
	query.setParameter("pFornecedor", fornecedor);
        query.setMaxResults(1);
	try {
            Fornecedor resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean especificacaoEmUso(Especificacao especificacao) {
        Boolean emUso = false;
        String jpql = "select e from Especificacao e where exists (select m from Material m where m.tipo = :pEspecificacao)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Especificacao> query = em.createQuery(jpql, Especificacao.class);
	query.setParameter("pEspecificacao", especificacao);
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
            System.out.println("Entity fechada");
        }
        return emUso;
    }

    public Boolean tipoUEmUso(TipoUsuario tipoUsuario) {
        Boolean emUso = false;
        String jpql = "select t from TipoUsuario t where exists (select u from Usuario u where u.tipo = :pTipo)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<TipoUsuario> query = em.createQuery(jpql, TipoUsuario.class);
	query.setParameter("pTipo", tipoUsuario);
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
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean tipoMaEmUso(TipoMaterial tipoMaterial) {
        Boolean emUso = false;
        String jpql = "select t from TipoMaterial t where exists (select m from Material m where m.tipo = :pTipo)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<TipoMaterial> query = em.createQuery(jpql, TipoMaterial.class);
	query.setParameter("pTipo", tipoMaterial);
        query.setMaxResults(1);
	try {
            TipoMaterial resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean tipoMoEmUso(TipoMovimentacao tipoMovimentacao) {
        Boolean emUso = false;
        String jpql = "select t from TipoMovimentacao t where exists (select m from Movimentacao m where m.tipo = :pTipo)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<TipoMovimentacao> query = em.createQuery(jpql, TipoMovimentacao.class);
	query.setParameter("pTipo", tipoMovimentacao);
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
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean tipoBEmUso(TipoBaixa tipoBaixa) {
        Boolean emUso = false;
        String jpql = "select t from TipoBaixa t where exists (select b from Baixa b where b.tipo = :pTipo)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<TipoBaixa> query = em.createQuery(jpql, TipoBaixa.class);
	query.setParameter("pTipo", tipoBaixa);
        query.setMaxResults(1);
	try {
            TipoBaixa resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return emUso;
    }
    
    public Boolean tipoAEmUso(TipoAquisicao tipoAquisicao) {
        Boolean emUso = false;
        String jpql = "select t from TipoAquisicao t where exists (select a from Aquisicao a where a.tipo = :pTipo)";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<TipoAquisicao> query = em.createQuery(jpql, TipoAquisicao.class);
	query.setParameter("pTipo", tipoAquisicao);
        query.setMaxResults(1);
	try {
            TipoAquisicao resultado =  query.getSingleResult();
            if (resultado != null) {
                emUso = true;
            }
	} catch (NoResultException ex) {
            System.out.println("Erro! " + ex);
	}finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return emUso;
    }
}