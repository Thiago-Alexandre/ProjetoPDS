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
import model.Filial;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanFilial implements Serializable{
    
    private Filial filial = new Filial();
    private List<Filial> filiais = new ArrayList();
    
    @PostConstruct
    public void init(){
        filiais = new PesquisaDAO().filiaisPorNome("");
    }
    
    @PreDestroy
    public void finalizar(){
        removerErro();
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
    
    public void salvar(){
        if(filial.getId() == null) {
            new DAO<Filial>(Filial.class).adiciona(filial);
	} else {
            new DAO<Filial>(Filial.class).atualiza(filial);
	}
        removerErro();
        filiais = new PesquisaDAO().filiaisPorNome("");
        filial = new Filial();
    }
    
    public void excluir(){
        if (filial.getId() != null) {
            if (!new VerificadorDAO().filialEmUso(filial)) {
                new DAO<Filial>(Filial.class).remove(filial);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        filiais = new PesquisaDAO().filiaisPorNome("");
        filial = new Filial();
    }
    
    public void onRowSelect(SelectEvent event) {
    	filial = (Filial) event.getObject();
    }
    
    public void listar(){
        String nome  = filial.getNome();
        if (nome == null) {
            nome = "";
        }
        filial = new Filial();
        removerErro();
        filiais = new PesquisaDAO().filiaisPorNome("");
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }
}