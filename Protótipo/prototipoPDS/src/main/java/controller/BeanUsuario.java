package controller;

import filter.UsuarioFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.TipoUsuario;
import model.Usuario;
import org.primefaces.model.LazyDataModel;
import service.UsuarioDAO;
import service.UsuarioPaginator;

@ManagedBean
@ViewScoped
public class BeanUsuario implements Serializable{
    
    private Usuario usuario;
    private Integer tipo;
    private String senha;
    private Boolean acesso;
    private LazyDataModel<Usuario> usuarios;
    private List<TipoUsuario> tipos = new ArrayList();
    private UsuarioFilter filtro;
    private UsuarioDAO dao;
    
    public BeanUsuario(){
        usuario = new Usuario();
        filtro = new UsuarioFilter();
        filtro.setAcesso(true);
        usuarios = new UsuarioPaginator(filtro);
        dao = new UsuarioDAO();
        tipos = dao.carregarTipos();
        senha = "";
        acesso = true;
    }
    
    public String cadastrar(){
        usuario.setTipo(dao.buscarTipo(tipo));
        dao.adiciona(usuario);
        mostrarMensagem("Usuário registrado com sucesso!");
        return "/view/usuarios?faces-redirect=true";
    }
    
    public String cancelar(){
        return "/view/usuarios?faces-redirect=true";
    }
    
    public void salvar(){
        usuario.setTipo(dao.buscarTipo(tipo));
        usuario.setAcesso(acesso);
        if (!senha.isEmpty()) {
            usuario.setSenha(senha);
        }
        dao.atualiza(usuario);
        mostrarMensagem("Usuário alterado com sucesso!");
        usuario = new Usuario();
    }
    
    public void alterar(Usuario usuarioSelecionado){
        tipo = usuarioSelecionado.getTipo().getId();
        acesso = usuarioSelecionado.getAcesso();
        usuario.setId(usuarioSelecionado.getId());
        usuario.setNome(usuarioSelecionado.getNome());
        usuario.setLogin(usuarioSelecionado.getLogin());
        usuario.setSenha(usuarioSelecionado.getSenha());
        removerMensagem();
    }
    
    public void limpar(){
        removerMensagem();
        usuario = new Usuario();
        acesso = true;
        senha = "";
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<TipoUsuario> getTipos() {
        return tipos;
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(UsuarioFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(LazyDataModel<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(Boolean acesso) {
        this.acesso = acesso;
    }
}