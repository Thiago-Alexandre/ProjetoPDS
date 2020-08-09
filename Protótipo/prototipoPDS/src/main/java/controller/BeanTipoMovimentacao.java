package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.TipoMovimentacao;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.TipoMovimentacaoDAO;

@ManagedBean
@ViewScoped
public class BeanTipoMovimentacao implements Serializable{
    
    private TipoMovimentacao tipoMovimentacao = new TipoMovimentacao();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<TipoMovimentacao> tipos;
    private TipoMovimentacaoDAO dao;
   
    public BeanTipoMovimentacao() {
        tipos = new BasicoPaginator(TipoMovimentacao.class, filtro);
        dao = new TipoMovimentacaoDAO();
    }
    
    public void salvar(){
        if (tipoMovimentacao.getId() == null) {
            dao.adiciona(tipoMovimentacao);
            mostrarMensagem("Tipo de Movimentação registrado com sucesso!");
        }else{
            dao.atualiza(tipoMovimentacao);
            mostrarMensagem("Tipo de Movimentação alterado com sucesso!");
        }
        tipoMovimentacao = new TipoMovimentacao();
    }
    
    public void alterar(TipoMovimentacao tipoMovimentacaoSelecionado){
        tipoMovimentacao = tipoMovimentacaoSelecionado;
        removerMensagem();
    }
    
    public void excluir(TipoMovimentacao tipoMovimentacaoSelecionado){
        tipoMovimentacao = tipoMovimentacaoSelecionado;
        if (!dao.verifica(tipoMovimentacao)) {
            dao.remove(tipoMovimentacao);
            mostrarMensagem("Tipo de Movimentação removido com sucesso!");
        } else{
            mostrarMensagem("Erro! Tipo de Movimentação não poderá ser removido por estar cadastrado em uma Movimentação!");
        }
        tipoMovimentacao = new TipoMovimentacao();
    }
    
    public void limpar(){
        removerMensagem();
        tipoMovimentacao = new TipoMovimentacao();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<TipoMovimentacao> getTipos() {
        return tipos;
    }

    public void setTipos(LazyDataModel<TipoMovimentacao> tipos) {
        this.tipos = tipos;
    }
}