package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Responsavel;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.ResponsavelDAO;

@ManagedBean
@ViewScoped
public class BeanResponsavel implements Serializable{
    
    private Responsavel responsavel = new Responsavel();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<Responsavel> responsaveis;
    private ResponsavelDAO dao;
   
    public BeanResponsavel() {
        responsaveis = new BasicoPaginator(Responsavel.class, filtro);
        dao = new ResponsavelDAO();
    }
    
    public void salvar(){
        if (responsavel.getId() == null) {
            dao.adiciona(responsavel);
            mostrarMensagem("Responsável registrado com sucesso!");
        }else{
            dao.atualiza(responsavel);
            mostrarMensagem("Responsável alterado com sucesso!");
        }
        responsavel = new Responsavel();
    }
    
    public void alterar(Responsavel responsavelSelecionado){
        responsavel = responsavelSelecionado;
        removerMensagem();
    }
    
    public void excluir(Responsavel responsavelSelecionado){
        responsavel = responsavelSelecionado;
        if (!dao.verifica(responsavel)) {
            dao.remove(responsavel);
            mostrarMensagem("Responsável removido com sucesso!");
        } else{
            mostrarMensagem("Erro! Responsável não poderá ser removido por estar cadastrado em um Material ou Setor!");
        }
        responsavel = new Responsavel();
    }
    
    public void limpar(){
        removerMensagem();
        responsavel = new Responsavel();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(LazyDataModel<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }
}