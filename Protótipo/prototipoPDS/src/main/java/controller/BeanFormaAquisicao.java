package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.FormaAquisicao;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.FormaAquisicaoDAO;

@ManagedBean
@ViewScoped
public class BeanFormaAquisicao implements Serializable{
    
    private FormaAquisicao formaAquisicao = new FormaAquisicao();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<FormaAquisicao> formas;
    private FormaAquisicaoDAO dao;
   
    public BeanFormaAquisicao() {
        formas = new BasicoPaginator(FormaAquisicao.class, filtro);
        dao = new FormaAquisicaoDAO();
    }
    
    public void salvar(){
        if (formaAquisicao.getId() == null) {
            dao.adiciona(formaAquisicao);
            mostrarMensagem("Forma de Aquisição registrada com sucesso!");
        }else{
            dao.atualiza(formaAquisicao);
            mostrarMensagem("Forma de Aquisição alterada com sucesso!");
        }
        formaAquisicao = new FormaAquisicao();
    }
    
    public void alterar(FormaAquisicao formaAquisicaoSelecionada){
        formaAquisicao = formaAquisicaoSelecionada;
        removerMensagem();
    }
    
    public void excluir(FormaAquisicao formaAquisicaoSelecionada){
        formaAquisicao = formaAquisicaoSelecionada;
        if (!dao.verifica(formaAquisicao)) {
            dao.remove(formaAquisicao);
            mostrarMensagem("Forma de Aquisição removida com sucesso!");
        } else{
            mostrarMensagem("Erro! Forma de Aquisição não poderá ser removida por estar cadastrada em uma Aquisição!");
        }
        formaAquisicao = new FormaAquisicao();
    }
    
    public void limpar(){
        removerMensagem();
        formaAquisicao = new FormaAquisicao();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public FormaAquisicao getFormaAquisicao() {
        return formaAquisicao;
    }

    public void setFormaAquisicao(FormaAquisicao formaAquisicao) {
        this.formaAquisicao = formaAquisicao;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<FormaAquisicao> getFormas() {
        return formas;
    }

    public void setFormas(LazyDataModel<FormaAquisicao> formas) {
        this.formas = formas;
    }
}