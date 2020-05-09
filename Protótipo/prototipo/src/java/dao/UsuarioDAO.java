package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Usuario;
import util.JPAUtil;

public class UsuarioDAO {
    
    public Usuario existe(Usuario usuario) {
        Usuario logado = null;
        String jpql = "select u from Usuario u where u.login = :pLogin and u.senha = :pSenha";
        EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
	query.setParameter("pLogin", usuario.getLogin());
	query.setParameter("pSenha", usuario.getSenha());
	try {
            logado =  query.getSingleResult();
	} catch (NoResultException ex) {
            System.out.println("Erro ao Logar! " + ex);
	} finally{
            em.close();
            System.out.println("Entity fechada");
        }
        return logado;
    }
}