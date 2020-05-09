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
import model.Responsavel;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanResponsavel implements Serializable{
    
    private Responsavel responsavel = new Responsavel();
    private List<Responsavel> responsaveis = new ArrayList();
    
    @PostConstruct
    public void init(){
        responsaveis = new PesquisaDAO().responsaveisPorNome("");
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
        if(responsavel.getId() == null) {
            new DAO<Responsavel>(Responsavel.class).adiciona(responsavel);
	} else {
            new DAO<Responsavel>(Responsavel.class).atualiza(responsavel);
	}
        removerErro();
        responsaveis = new PesquisaDAO().responsaveisPorNome("");
        responsavel = new Responsavel();
    }
    
    public void excluir(){
        if (responsavel.getId() != null) {
            if (!new VerificadorDAO().responsavelEmUso(responsavel)) {
                new DAO<Responsavel>(Responsavel.class).remove(responsavel);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        responsaveis = new PesquisaDAO().responsaveisPorNome("");
        responsavel = new Responsavel();
    }
    
    public void onRowSelect(SelectEvent event) {
    	responsavel = (Responsavel) event.getObject();
    }
    
    public void listar(){
        String nome  = responsavel.getNome();
        if (nome == null) {
            nome = "";
        }
        responsavel = new Responsavel();
        removerErro();
        responsaveis = new PesquisaDAO().responsaveisPorNome("");
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }
}