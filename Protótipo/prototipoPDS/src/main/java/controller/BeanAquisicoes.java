package controller;

import filter.AquisicaoFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.FormaAquisicao;
import model.Aquisicao;
import model.Usuario;
import org.primefaces.model.LazyDataModel;
import service.AquisicaoDAO;
import service.AquisicaoPaginator;

@ManagedBean
@ViewScoped
public class BeanAquisicoes implements Serializable{
    
    private LazyDataModel<Aquisicao> aquisicoes;
    private List<FormaAquisicao> formas = new ArrayList();
    private List<Usuario> usuarios = new ArrayList();
    private AquisicaoFilter filtro;
    private AquisicaoDAO dao;
    private FacesContext context;
    
    public BeanAquisicoes(){
        filtro = new AquisicaoFilter();
        aquisicoes = new AquisicaoPaginator(filtro);
        dao = new AquisicaoDAO();
        formas = dao.carregarFormas();
        usuarios = dao.carregarUsuarios();
    }
    
    public String verDetalhes(Aquisicao aquisicaoSelecionada){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
        context.getExternalContext().getSessionMap().put("aquisicaoSelecionada", aquisicaoSelecionada);
        return "/view/aquisicao?faces-redirect=true";
    }

    public AquisicaoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(AquisicaoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Aquisicao> getAquisicoes() {
        return aquisicoes;
    }

    public void setAquisicoes(LazyDataModel<Aquisicao> aquisicoes) {
        this.aquisicoes = aquisicoes;
    }

    public List<FormaAquisicao> getFormas() {
        return formas;
    }

    public void setFormas(List<FormaAquisicao> formas) {
        this.formas = formas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}