package beans;

import dao.DAO;
import dao.PesquisaDAO;
import dao.VerificadorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.TipoUsuario;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanTipoUsuario implements Serializable{
    
    private TipoUsuario tipoUsuario = new TipoUsuario();
    private List<TipoUsuario> tipos = new ArrayList();
    
    @PostConstruct
    public void init(){
        tipos = new PesquisaDAO().tiposUsuarioPorNome("");
    }
    
    @PreDestroy
    public void finalizar(){
        removerErro();
    }
    
    public void salvar(){
        if(tipoUsuario.getId() == null) {
            new DAO<TipoUsuario>(TipoUsuario.class).adiciona(tipoUsuario);
	} else {
            new DAO<TipoUsuario>(TipoUsuario.class).atualiza(tipoUsuario);
	}
        removerErro();
        tipos = new PesquisaDAO().tiposUsuarioPorNome("");
        tipoUsuario = new TipoUsuario();
    }
    
    public void excluir(){
        if (tipoUsuario.getId() != null) {
            if (!new VerificadorDAO().tipoUEmUso(tipoUsuario)) {
                new DAO<TipoUsuario>(TipoUsuario.class).remove(tipoUsuario);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        tipos = new PesquisaDAO().tiposUsuarioPorNome("");
        tipoUsuario = new TipoUsuario();
    }
    
    private void removerErro(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().get("exclusaoNaoPermitida") != null) {
            context.getExternalContext().getSessionMap().remove("exclusaoNaoPermitida");
        }
    }
    
    private void adicionarErro(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("exclusaoNaoPermitida", true);
    }
    
    public void onRowSelect(SelectEvent event) {
        tipoUsuario = (TipoUsuario) event.getObject();
    }
    
    public void listar(){
        String nome  = tipoUsuario.getNome();
        if (nome == null) {
            nome = "";
        }
        tipoUsuario = new TipoUsuario();
        removerErro();
        tipos = new PesquisaDAO().tiposUsuarioPorNome(nome);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public List<TipoUsuario> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoUsuario> tipos) {
        this.tipos = tipos;
    }
    
    /*
    //
    private LazyDataModel<TipoUsuario> tiposLazyDataModel;
    
    public LazyDataModel<TipoUsuario> getModelosLazyDataModel() {
        if (tiposLazyDataModel == null) {
            String nome  = tipoUsuario.getNome();
            if (nome == null) {
                nome = "";
            }
            String jpql = "select t from TipoUsuario t where t.nome like '%" + nome + "%'";
            String count = "select count(t.id) from TipoUsuario t";
            tiposLazyDataModel = new QueryDataModel<TipoUsuario>(jpql, count);
        }
        return tiposLazyDataModel;
    }
    */
}