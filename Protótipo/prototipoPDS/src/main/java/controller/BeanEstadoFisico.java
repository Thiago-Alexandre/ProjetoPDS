package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.EstadoFisico;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.EstadoFisicoDAO;

@ManagedBean
@ViewScoped
public class BeanEstadoFisico implements Serializable{
    
    private EstadoFisico estadoFisico = new EstadoFisico();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<EstadoFisico> estados;
    private EstadoFisicoDAO dao;
   
    public BeanEstadoFisico() {
        estados = new BasicoPaginator(EstadoFisico.class, filtro);
        dao = new EstadoFisicoDAO();
    }
    
    public void salvar(){
        if (estadoFisico.getId() == null) {
            dao.adiciona(estadoFisico);
            mostrarMensagem("Estado Físico registrado com sucesso!");
        }else{
            dao.atualiza(estadoFisico);
            mostrarMensagem("Estado Físico alterado com sucesso!");
        }
        estadoFisico = new EstadoFisico();
    }
    
    public void alterar(EstadoFisico estadoFisicoSelecionado){
        estadoFisico = estadoFisicoSelecionado;
        removerMensagem();
    }
    
    public void excluir(EstadoFisico estadoFisicoSelecionado){
        estadoFisico = estadoFisicoSelecionado;
        if (!dao.verifica(estadoFisico)) {
            dao.remove(estadoFisico);
            mostrarMensagem("Estado Físico removido com sucesso!");
        } else{
            mostrarMensagem("Erro! Estado Físico não poderá ser removido por estar cadastrado em um Material!");
        }
        estadoFisico = new EstadoFisico();
    }
    
    public void limpar(){
        removerMensagem();
        estadoFisico = new EstadoFisico();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public EstadoFisico getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(EstadoFisico estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<EstadoFisico> getEstados() {
        return estados;
    }

    public void setEstados(LazyDataModel<EstadoFisico> estados) {
        this.estados = estados;
    }
}