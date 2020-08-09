package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.FormaBaixa;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.FormaBaixaDAO;

@ManagedBean
@ViewScoped
public class BeanFormaBaixa implements Serializable{
    
    private FormaBaixa formaBaixa = new FormaBaixa();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<FormaBaixa> formas;
    private FormaBaixaDAO dao;
   
    public BeanFormaBaixa() {
        formas = new BasicoPaginator(FormaBaixa.class, filtro);
        dao = new FormaBaixaDAO();
    }
    
    public void salvar(){
        if (formaBaixa.getId() == null) {
            dao.adiciona(formaBaixa);
            mostrarMensagem("Forma de Baixa registrada com sucesso!");
        }else{
            dao.atualiza(formaBaixa);
            mostrarMensagem("Forma de Baixa alterada com sucesso!");
        }
        formaBaixa = new FormaBaixa();
    }
    
    public void alterar(FormaBaixa formaBaixaSelecionada){
        formaBaixa = formaBaixaSelecionada;
        removerMensagem();
    }
    
    public void excluir(FormaBaixa formaBaixaSelecionada){
        formaBaixa = formaBaixaSelecionada;
        if (!dao.verifica(formaBaixa)) {
            dao.remove(formaBaixa);
            mostrarMensagem("Forma de Baixa removida com sucesso!");
        } else{
            mostrarMensagem("Erro! Forma de Baixa não poderá ser removida por estar cadastrada em uma Baixa!");
        }
        formaBaixa = new FormaBaixa();
    }
    
    public void limpar(){
        removerMensagem();
        formaBaixa = new FormaBaixa();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public FormaBaixa getFormaBaixa() {
        return formaBaixa;
    }

    public void setFormaBaixa(FormaBaixa formaBaixa) {
        this.formaBaixa = formaBaixa;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<FormaBaixa> getFormas() {
        return formas;
    }

    public void setFormas(LazyDataModel<FormaBaixa> formas) {
        this.formas = formas;
    }
}