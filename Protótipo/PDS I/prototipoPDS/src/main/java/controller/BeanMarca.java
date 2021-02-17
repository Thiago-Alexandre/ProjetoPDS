package controller;

import filter.BasicoFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Marca;
import org.primefaces.model.LazyDataModel;
import service.BasicoPaginator;
import service.MarcaDAO;

@ManagedBean
@ViewScoped
public class BeanMarca implements Serializable{
    
    private Marca marca = new Marca();
    private BasicoFilter filtro = new BasicoFilter();
    private LazyDataModel<Marca> marcas;
    private MarcaDAO dao;
   
    public BeanMarca() {
        marcas = new BasicoPaginator(Marca.class, filtro);
        dao = new MarcaDAO();
    }
    
    public void salvar(){
        if (marca.getId() == null) {
            dao.adiciona(marca);
            mostrarMensagem("Marca registrada com sucesso!");
        }else{
            dao.atualiza(marca);
            mostrarMensagem("Marca alterada com sucesso!");
        }
        marca = new Marca();
    }
    
    public void alterar(Marca marcaSelecionada){
        marca = marcaSelecionada;
        removerMensagem();
    }
    
    public void excluir(Marca marcaSelecionada){
        marca = marcaSelecionada;
        if (!dao.verifica(marca)) {
            dao.remove(marca);
            mostrarMensagem("Marca removida com sucesso!");
        } else{
            mostrarMensagem("Erro! Marca não poderá ser removida por estar cadastrada em um Material!");
        }
        marca = new Marca();
    }
    
    public void limpar(){
        removerMensagem();
        marca = new Marca();
    }
    
    private void mostrarMensagem(String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public BasicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(BasicoFilter filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(LazyDataModel<Marca> marcas) {
        this.marcas = marcas;
    }
}