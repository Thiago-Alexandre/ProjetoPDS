package controller;

import filter.SetorFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Filial;
import model.Responsavel;
import model.Setor;
import org.primefaces.model.LazyDataModel;
import service.SetorDAO;
import service.SetorPaginator;

@ManagedBean
@ViewScoped
public class BeanSetor implements Serializable{
    
    private Setor setor;
    private Integer filial;
    private Integer responsavel;
    private LazyDataModel<Setor> setores;
    private List<Filial> filiais = new ArrayList();
    private List<Responsavel> responsaveis = new ArrayList();
    private SetorFilter filtro;
    private SetorDAO dao;
    
    public BeanSetor(){
        setor = new Setor();
        filtro = new SetorFilter();
        setores = new SetorPaginator(filtro);
        dao = new SetorDAO();
        filiais = dao.carregarFiliais();
        responsaveis = dao.carregarResponsaveis();
    }
    
    public void salvar(){
        if (setor.getId() == null) {
            setor.setFilial(dao.buscarFilial(filial));
            setor.setResponsavel(dao.buscarResponsavel(responsavel));
            dao.adiciona(setor);
            mostrarMensagem("Setor registrado com sucesso!");
        }else{
            dao.atualiza(setor);
            mostrarMensagem("Setor alterado com sucesso!");
        }
        setor = new Setor();
    }
    
    public void alterar(Setor setorSelecionado){
        setor = setorSelecionado;
        removerMensagem();
    }
    
    public void excluir(Setor setorSelecionado){
        setor = setorSelecionado;
        if (!dao.verifica(setor)) {
            dao.remove(setor);
            mostrarMensagem("Setor removido com sucesso!");
        } else{
            mostrarMensagem("Erro! Setor não poderá ser removido por estar vinculado a outro cadastro!");
        }
        setor = new Setor();
    }
    
    public void limpar(){
        removerMensagem();
        setor = new Setor();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public SetorFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(SetorFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Setor> getSetores() {
        return setores;
    }

    public void setSetores(LazyDataModel<Setor> setors) {
        this.setores = setors;
    }

    public Integer getFilial() {
        return filial;
    }

    public void setFilial(Integer filial) {
        this.filial = filial;
    }

    public Integer getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Integer responsavel) {
        this.responsavel = responsavel;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }
}