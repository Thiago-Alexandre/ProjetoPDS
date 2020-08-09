package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.TipoUsuario;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.TipoUsuarioDAO;

@ManagedBean
@ViewScoped
public class BeanTipoUsuario implements Serializable{
    
    private TipoUsuario tipoUsuario = new TipoUsuario();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<TipoUsuario> tipos;
    private TipoUsuarioDAO dao;
   
    public BeanTipoUsuario() {
        tipos = new BasicoPaginator(TipoUsuario.class, filtro);
        dao = new TipoUsuarioDAO();
    }
    
    public void salvar(){
        if (tipoUsuario.getId() == null) {
            dao.adiciona(tipoUsuario);
            mostrarMensagem("Tipo de Usuário registrado com sucesso!");
        }else{
            dao.atualiza(tipoUsuario);
            mostrarMensagem("Tipo de Usuário alterado com sucesso!");
        }
        tipoUsuario = new TipoUsuario();
    }
    
    public void alterar(TipoUsuario tipoUsuarioSelecionado){
        tipoUsuario = tipoUsuarioSelecionado;
        removerMensagem();
    }
    
    public void excluir(TipoUsuario tipoUsuarioSelecionado){
        tipoUsuario = tipoUsuarioSelecionado;
        if (!dao.verifica(tipoUsuario)) {
            dao.remove(tipoUsuario);
            mostrarMensagem("Tipo de Usuário removido com sucesso!");
        } else{
            mostrarMensagem("Erro! Tipo de Usuário não poderá ser removido por estar cadastrado em uma Usuário!");
        }
        tipoUsuario = new TipoUsuario();
    }
    
    public void limpar(){
        removerMensagem();
        tipoUsuario = new TipoUsuario();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<TipoUsuario> getTipos() {
        return tipos;
    }

    public void setTipos(LazyDataModel<TipoUsuario> tipos) {
        this.tipos = tipos;
    }
}