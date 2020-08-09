package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;
import service.DAO;

@ManagedBean
@ViewScoped
public class BeanPerfil implements Serializable{
    
    private Usuario usuario = new Usuario();
    private String nome;
    private String login;
    private String senha;
    private DAO dao;
    
    public BeanPerfil(){
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
        context.getExternalContext().getSessionMap().remove("mensagem");
        nome = usuario.getNome();
        login = usuario.getLogin();
        senha = "";
        dao = new DAO(Usuario.class);
    }
    
    public void alterarPerfil(){
        usuario.setNome(nome);
        usuario.setLogin(login);
        dao.atualiza(usuario);
        atualizarUsuarioLogado("Perfil alterado com sucesso!");
    }
    
    public void alterarSenha(){
        usuario.setSenha(senha);
        dao.atualiza(usuario);
        atualizarUsuarioLogado("Senha alterada com sucesso!");
        senha = "";
    }
    
    private void atualizarUsuarioLogado(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}