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
import model.TipoMaterial;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanTipoMaterial implements Serializable{
    
    private TipoMaterial tipoMaterial = new TipoMaterial();
    private List<TipoMaterial> tipos = new ArrayList();
    
    @PostConstruct
    public void init(){
        tipos = new PesquisaDAO().tiposMaterialPorNome("");
    }
    
    @PreDestroy
    public void finalizar(){
        removerErro();
    }
    
    public void salvar(){
        if(tipoMaterial.getId() == null) {
            new DAO<TipoMaterial>(TipoMaterial.class).adiciona(tipoMaterial);
	} else {
            new DAO<TipoMaterial>(TipoMaterial.class).atualiza(tipoMaterial);
	}
        removerErro();
        tipos = new PesquisaDAO().tiposMaterialPorNome("");
        tipoMaterial = new TipoMaterial();
    }
    
    public void excluir(){
        if (tipoMaterial.getId() != null) {
            if (!new VerificadorDAO().tipoMaEmUso(tipoMaterial)) {
                new DAO<TipoMaterial>(TipoMaterial.class).remove(tipoMaterial);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        tipos = new PesquisaDAO().tiposMaterialPorNome("");
        tipoMaterial = new TipoMaterial();
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
    	tipoMaterial = (TipoMaterial) event.getObject();
    }
    
    public void listar(){
        String nome  = tipoMaterial.getNome();
        if (nome == null) {
            nome = "";
        }
        tipoMaterial = new TipoMaterial();
        removerErro();
        tipos = new PesquisaDAO().tiposMaterialPorNome(nome);
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public List<TipoMaterial> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoMaterial> tipos) {
        this.tipos = tipos;
    }
}