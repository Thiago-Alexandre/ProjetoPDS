package beans;

import dao.DAO;
import dao.UsuarioDAO;
import dao.VerificadorDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanUsuario implements Serializable{
    
    private Usuario usuario = new Usuario();
    
    /*
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().get("editarPerfil") != null) {
            usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
        } else{
            usuario = new Usuario();
        }
    }*/
    
    @PreDestroy
    public void finalizar(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().get("editarPerfil") != null) {
            context.getExternalContext().getSessionMap().remove("editarPerfil");
        }
    }
    
    public void salvar(){
        if(usuario.getId() == null) {
            new DAO<Usuario>(Usuario.class).adiciona(usuario);
	} else {
            new DAO<Usuario>(Usuario.class).atualiza(usuario);
	}
        usuario = new Usuario();
    }
    
    public void excluir(){
        if (!new VerificadorDAO().usuarioEmUso(usuario)) {
            new DAO<Usuario>(Usuario.class).remove(usuario);
        }
    }
    
    public void onRowSelect(SelectEvent event) {
    	usuario = (Usuario) event.getObject();
    }
    
    public List<Usuario> listar(){
        return new DAO<Usuario>(Usuario.class).lista();
    }
    
    public String efetuarLogin(){
        System.out.println(usuario.getLogin());
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario existe = new UsuarioDAO().existe(usuario);
        if(existe != null) {
            usuario = existe;
            context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
            if (context.getExternalContext().getSessionMap().get("erro") != null) {
                context.getExternalContext().getSessionMap().remove("erro");
            }
            return "index?faces-redirect=true";
        }
        context.getExternalContext().getSessionMap().put("erro", true);
        return "login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}