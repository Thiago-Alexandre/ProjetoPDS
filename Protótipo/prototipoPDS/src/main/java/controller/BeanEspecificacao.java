package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Especificacao;
import org.primefaces.model.LazyDataModel;
import service.EspecificacaoDAO;
import service.BasicoPaginator;

@ManagedBean
@ViewScoped
public class BeanEspecificacao implements Serializable{
    
    private Especificacao especificacao = new Especificacao();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<Especificacao> especificacoes;
    private EspecificacaoDAO dao;
   
    public BeanEspecificacao() {
        especificacoes = new BasicoPaginator(Especificacao.class, filtro);
        dao = new EspecificacaoDAO();
    }
    
    public void salvar(){
        if (especificacao.getId() == null) {
            dao.adiciona(especificacao);
            mostrarMensagem("Especificação registrada com sucesso!");
        }else{
            dao.atualiza(especificacao);
            mostrarMensagem("Especificação alterada com sucesso!");
        }
        especificacao = new Especificacao();
    }
    
    public void alterar(Especificacao especificacaoSelecionada){
        especificacao = especificacaoSelecionada;
        removerMensagem();
    }
    
    public void excluir(Especificacao especificacaoSelecionada){
        especificacao = especificacaoSelecionada;
        if (!dao.verifica(especificacao)) {
            dao.remove(especificacao);
            mostrarMensagem("Especificação removida com sucesso!");
        } else{
            mostrarMensagem("Erro! Especificação não poderá ser removida por estar cadastrada em um Material!");
        }
        especificacao = new Especificacao();
    }
    
    public void limpar(){
        removerMensagem();
        especificacao = new Especificacao();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao marca) {
        this.especificacao = marca;
    }

    public LazyDataModel<Especificacao> getEspecificacoes() {
        return especificacoes;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }
}