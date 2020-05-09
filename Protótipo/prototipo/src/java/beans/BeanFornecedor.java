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
import model.Fornecedor;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class BeanFornecedor implements Serializable{
    
    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> fornecedores = new ArrayList();
    
    @PostConstruct
    public void init(){
        fornecedores = new PesquisaDAO().fornecedoresPorNome("");
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
        if(fornecedor.getId() == null) {
            new DAO<Fornecedor>(Fornecedor.class).adiciona(fornecedor);
	} else {
            new DAO<Fornecedor>(Fornecedor.class).atualiza(fornecedor);
	}
        removerErro();
        fornecedores = new PesquisaDAO().fornecedoresPorNome("");
        fornecedor = new Fornecedor();
    }
    
    public void excluir(){
        if (fornecedor.getId() != null) {
            if (!new VerificadorDAO().fornecedorEmUso(fornecedor)) {
                new DAO<Fornecedor>(Fornecedor.class).remove(fornecedor);
                removerErro();
            } else{
                adicionarErro();
            }   
        }
        fornecedores = new PesquisaDAO().fornecedoresPorNome("");
        fornecedor = new Fornecedor();
    }
    
    public void onRowSelect(SelectEvent event) {
    	fornecedor = (Fornecedor) event.getObject();
    }
    
    public void listar(){
        String nome  = fornecedor.getNome();
        if (nome == null) {
            nome = "";
        }
        fornecedor = new Fornecedor();
        removerErro();
        fornecedores = new PesquisaDAO().fornecedoresPorNome("");
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}