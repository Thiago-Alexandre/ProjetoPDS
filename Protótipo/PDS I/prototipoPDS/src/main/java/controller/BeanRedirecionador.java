package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class BeanRedirecionador {
    
    private FacesContext context;

    public BeanRedirecionador() {
        context = FacesContext.getCurrentInstance();
    }
    
    public String navegar(String destino) {
        removerMensagem();
        limparSessao();
        return "/view/" + destino + "?faces-redirect=true";
    }
    
    public String deslogar() {
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        context.getExternalContext().getSessionMap().remove("mensagem");
        return "/view/login?faces-redirect=true";
    }
    
    public String novoUsuario() {
        removerMensagem();
        return "/view/novoUsuario?faces-redirect=true";
    }

    private void removerMensagem(){
        context.getExternalContext().getSessionMap().remove("mensagem");
    }
    
    private void limparSessao(){
        context.getExternalContext().getSessionMap().remove("aquisicaoSelecionada");
    }
}