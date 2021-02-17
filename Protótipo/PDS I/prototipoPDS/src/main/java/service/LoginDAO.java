package service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Usuario;
import util.JPAConnectionFactory;

public class LoginDAO extends DAO{
    
    private EntityManager em;

    public LoginDAO() {
        super(Usuario.class);
    }
    
    public Integer pesquisa (String login, String senha){
        Usuario usuario = null;
        em = JPAConnectionFactory.getEntityManager();
        CriteriaQuery<Usuario> query = em.getCriteriaBuilder().createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);
        Path<String> loginPath = root.<String>get("login");
        Path<String> senhaPath = root.<String>get("senha");
        Predicate verificaLogin = em.getCriteriaBuilder().equal(loginPath, login);
        Predicate verificaSenha = em.getCriteriaBuilder().equal(senhaPath, senha);
        query.where(verificaLogin, verificaSenha);
        TypedQuery<Usuario> typed = em.createQuery(query);
        try{
            usuario = typed.getSingleResult();
        } catch(NoResultException erro){
            System.out.println(erro);
        } finally{
            em.close();
        }
        if (usuario != null && usuario.getAcesso()) {
            return usuario.getId();
        }
        return -1;
    }
}