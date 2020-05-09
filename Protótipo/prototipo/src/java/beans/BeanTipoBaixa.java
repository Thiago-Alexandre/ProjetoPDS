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
import model.TipoBaixa;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanTipoBaixa implements Serializable{
    
    private TipoBaixa tipoBaixa = new TipoBaixa();
    private List<TipoBaixa> tipos = new ArrayList();
    
    @PostConstruct
    public void init(){
        tipos = new PesquisaDAO().tiposBaixaPorNome("");
    }
    
    @PreDestroy
    public void finalizar(){
        removerErro();
    }
    
    public void salvar(){
        if(tipoBaixa.getId() == null) {
            new DAO<TipoBaixa>(TipoBaixa.class).adiciona(tipoBaixa);
	} else {
            new DAO<TipoBaixa>(TipoBaixa.class).atualiza(tipoBaixa);
	}
        removerErro();
        tipos = new PesquisaDAO().tiposBaixaPorNome("");
        tipoBaixa = new TipoBaixa();
    }
    
    public void excluir(){
        if (tipoBaixa.getId() != null) {
            if (!new VerificadorDAO().tipoBEmUso(tipoBaixa)) {
                new DAO<TipoBaixa>(TipoBaixa.class).remove(tipoBaixa);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        tipos = new PesquisaDAO().tiposBaixaPorNome("");
        tipoBaixa = new TipoBaixa();
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
    	tipoBaixa = (TipoBaixa) event.getObject();
    }
    
    public void listar(){
        String nome  = tipoBaixa.getNome();
        if (nome == null) {
            nome = "";
        }
        tipoBaixa = new TipoBaixa();
        removerErro();
        tipos = new PesquisaDAO().tiposBaixaPorNome(nome);
    }

    public TipoBaixa getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(TipoBaixa tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

    public List<TipoBaixa> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoBaixa> tipos) {
        this.tipos = tipos;
    }
}