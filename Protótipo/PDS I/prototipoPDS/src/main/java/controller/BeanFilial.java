package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Filial;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.FilialDAO;

@ManagedBean
@ViewScoped
public class BeanFilial implements Serializable{
    
    private Filial filial = new Filial();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<Filial> filiais;
    private FilialDAO dao;
   
    public BeanFilial() {
        filiais = new BasicoPaginator(Filial.class, filtro);
        dao = new FilialDAO();
    }
    
    public void salvar(){
        if (filial.getId() == null) {
            dao.adiciona(filial);
            mostrarMensagem("Filial registrada com sucesso!");
        }else{
            dao.atualiza(filial);
            mostrarMensagem("Filial alterada com sucesso!");
        }
        filial = new Filial();
    }
    
    public void alterar(Filial filialSelecionada){
        filial = filialSelecionada;
        removerMensagem();
    }
    
    public void excluir(Filial filialSelecionada){
        filial = filialSelecionada;
        if (!dao.verifica(filial)) {
            dao.remove(filial);
            mostrarMensagem("Filial removida com sucesso!");
        } else{
            mostrarMensagem("Erro! Filial não poderá ser removida por estar cadastrada em um Setor!");
        }
        filial = new Filial();
    }
    
    public void limpar(){
        removerMensagem();
        filial = new Filial();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(LazyDataModel<Filial> filiais) {
        this.filiais = filiais;
    }
}