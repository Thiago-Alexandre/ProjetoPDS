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
import model.TipoAquisicao;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanTipoAquisicao implements Serializable{
    
    private TipoAquisicao tipoAquisicao = new TipoAquisicao();
    private List<TipoAquisicao> tipos = new ArrayList();
    
    @PostConstruct
    public void init(){
        tipos = new PesquisaDAO().tiposAquisicaoPorNome("");
    }
    
    @PreDestroy
    public void finalizar(){
        removerErro();
    }
    
    public void salvar(){
        if(tipoAquisicao.getId() == null) {
            new DAO<TipoAquisicao>(TipoAquisicao.class).adiciona(tipoAquisicao);
	} else {
            new DAO<TipoAquisicao>(TipoAquisicao.class).atualiza(tipoAquisicao);
	}
        removerErro();
        tipos = new PesquisaDAO().tiposAquisicaoPorNome("");
        tipoAquisicao = new TipoAquisicao();
    }
    
    public void excluir(){
        if (tipoAquisicao.getId() != null) {
            if (!new VerificadorDAO().tipoAEmUso(tipoAquisicao)) {
                new DAO<TipoAquisicao>(TipoAquisicao.class).remove(tipoAquisicao);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        tipos = new PesquisaDAO().tiposAquisicaoPorNome("");
        tipoAquisicao = new TipoAquisicao();
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
    	tipoAquisicao = (TipoAquisicao) event.getObject();
    }
    
    public void listar(){
        String nome  = tipoAquisicao.getNome();
        if (nome == null) {
            nome = "";
        }
        tipoAquisicao = new TipoAquisicao();
        removerErro();
        tipos = new PesquisaDAO().tiposAquisicaoPorNome(nome);
    }

    public TipoAquisicao getTipoAquisicao() {
        return tipoAquisicao;
    }

    public void setTipoAquisicao(TipoAquisicao tipoAquisicao) {
        this.tipoAquisicao = tipoAquisicao;
    }

    public List<TipoAquisicao> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoAquisicao> tipos) {
        this.tipos = tipos;
    }
}