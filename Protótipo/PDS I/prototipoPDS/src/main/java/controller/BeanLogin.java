package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;
import service.LoginDAO;

@ManagedBean
@ViewScoped
public class BeanLogin implements Serializable{
    
    private Usuario usuario = new Usuario();
    
    public String efetuarLogin(){
        FacesContext context = FacesContext.getCurrentInstance();
        int idUsuario = new LoginDAO().pesquisa(usuario.getLogin(), usuario.getSenha());
        if(idUsuario > 0) {
            usuario = (Usuario) new LoginDAO().buscaPorId(idUsuario);
            context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
            context.getExternalContext().getSessionMap().put("mensagem", "Bem vindo " + usuario.getNome());
            return "/view/index?faces-redirect=true";
        }
        context.getExternalContext().getSessionMap().put("mensagem", "Erro ao logar: Login ou Senha incorretos!");
        return "/view/login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}