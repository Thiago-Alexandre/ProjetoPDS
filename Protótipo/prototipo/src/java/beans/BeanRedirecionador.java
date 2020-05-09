package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class BeanRedirecionador {
    
    public String deslogar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "login?faces-redirect=true";
    }
    
    public String irHome() {
        return "index?faces-redirect=true";
    }
    
    public String novaAquisicao() {
        return "cadastroAquisicao?faces-redirect=true";
    }
    
    public String verAquisicoes() {
        return "aquisicoes?faces-redirect=true";
    }
    
    public String novaBaixa() {
        return "cadastroBaixa?faces-redirect=true";
    }
    
    public String verBaixas() {
        return "baixas?faces-redirect=true";
    }
    
    public String novaMovimentacao() {
        return "cadastroMovimentacao?faces-redirect=true";
    }
    
    public String verMovimentacoes() {
        return "movimentacoes?faces-redirect=true";
    }
    
    public String novoInventario() {
        return "cadastroInventario?faces-redirect=true";
    }
    
    public String verInventarios() {
        return "inventarios?faces-redirect=true";
    }
    
    public String verTiposUsuario() {
        return "tiposUsuarios?faces-redirect=true";
    }
    
    public String verTiposMaterial() {
        return "tiposMateriais?faces-redirect=true";
    }
    
    public String verTiposAquisicao() {
        return "tiposAquisicao?faces-redirect=true";
    }
    
    public String verTiposBaixa() {
        return "tiposBaixa?faces-redirect=true";
    }
    
    public String verTiposMovimentacao() {
        return "tiposMovimentacao?faces-redirect=true";
    }
    
    public String verFornecedores() {
        return "fornecedores?faces-redirect=true";
    }
    
    public String verFiliais() {
        return "filiais?faces-redirect=true";
    }
    
    public String verSetores() {
        return "setores?faces-redirect=true";
    }
    
    public String verResponsaveis() {
        return "responsaveis?faces-redirect=true";
    }
    
    public String verSituacoes() {
        return "situacoes?faces-redirect=true";
    }
    
    public String verEspecificacoes() {
        return "especificacoes?faces-redirect=true";
    }
    
    public String novoMaterial() {
        return "cadastroMaterial?faces-redirect=true";
    }
    
    public String verMateriais() {
        return "materiais?faces-redirect=true";
    }
    
    public String verUsuarios() {
        return "usuarios?faces-redirect=true";
    }
    
    public String verPerfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("editarPerfil", true);
        return "perfil?faces-redirect=true";
    }
}