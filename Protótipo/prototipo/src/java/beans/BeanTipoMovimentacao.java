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
import model.TipoMovimentacao;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanTipoMovimentacao implements Serializable{
    
    private TipoMovimentacao tipoMovimentacao = new TipoMovimentacao();
    private List<TipoMovimentacao> tipos = new ArrayList();
    
    @PostConstruct
    public void init(){
        tipos = new PesquisaDAO().tiposMovimentacaoPorNome("");
    }
    
    @PreDestroy
    public void finalizar(){
        removerErro();
    }
    
    public void salvar(){
        if(tipoMovimentacao.getId() == null) {
            new DAO<TipoMovimentacao>(TipoMovimentacao.class).adiciona(tipoMovimentacao);
	} else {
            new DAO<TipoMovimentacao>(TipoMovimentacao.class).atualiza(tipoMovimentacao);
	}
        removerErro();
        tipos = new PesquisaDAO().tiposMovimentacaoPorNome("");
        tipoMovimentacao = new TipoMovimentacao();
    }
    
    public void excluir(){
        if (tipoMovimentacao.getId() != null) {
            if (!new VerificadorDAO().tipoMoEmUso(tipoMovimentacao)) {
                new DAO<TipoMovimentacao>(TipoMovimentacao.class).remove(tipoMovimentacao);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        tipos = new PesquisaDAO().tiposMovimentacaoPorNome("");
        tipoMovimentacao = new TipoMovimentacao();
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
    	tipoMovimentacao = (TipoMovimentacao) event.getObject();
    }
    
    public void listar(){
        String nome  = tipoMovimentacao.getNome();
        if (nome == null) {
            nome = "";
        }
        tipoMovimentacao = new TipoMovimentacao();
        removerErro();
        tipos = new PesquisaDAO().tiposMovimentacaoPorNome(nome);
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public List<TipoMovimentacao> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoMovimentacao> tipos) {
        this.tipos = tipos;
    }
}