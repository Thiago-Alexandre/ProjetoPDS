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
import model.Situacao;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanSituacao implements Serializable{
    
    private Situacao situacao = new Situacao();
    private List<Situacao> situacoes = new ArrayList();
    
    @PostConstruct
    public void init(){
        situacoes = new PesquisaDAO().situacaoPorNome("");
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
        if(situacao.getId() == null) {
            new DAO<Situacao>(Situacao.class).adiciona(situacao);
	} else {
            new DAO<Situacao>(Situacao.class).atualiza(situacao);
	}
        removerErro();
        situacoes = new PesquisaDAO().situacaoPorNome("");
        situacao = new Situacao();
    }
    
    public void excluir(){
        if (situacao.getId() != null) {
            if (!new VerificadorDAO().situacaoEmUso(situacao)) {
                new DAO<Situacao>(Situacao.class).remove(situacao);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        situacoes = new PesquisaDAO().situacaoPorNome("");
        situacao = new Situacao();
    }
    
    public void onRowSelect(SelectEvent event) {
    	situacao = (Situacao) event.getObject();
    }
    
    public void listar(){
        String nome  = situacao.getNome();
        if (nome == null) {
            nome = "";
        }
        situacao = new Situacao();
        removerErro();
        situacoes = new PesquisaDAO().situacaoPorNome("");
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public List<Situacao> getSituacoes() {
        return situacoes;
    }

    public void setSituacoes(List<Situacao> situacoes) {
        this.situacoes = situacoes;
    }
}