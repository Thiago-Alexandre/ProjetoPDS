package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Especificacao;
import model.Marca;
import model.Material;
import service.MaterialDAO;

@ManagedBean
@ViewScoped
public class BeanMaterial implements Serializable{
    
    private Material material;
    private List<Especificacao> especificacoes = new ArrayList();
    private List<Marca> marcas = new ArrayList();
    private MaterialDAO dao;
    private Integer especificacao;
    private Integer marca;
    private FacesContext context;
    private Boolean permanente;
    private String tombamento;
    private Integer vida;
    private Integer valorResidual;
    
    public BeanMaterial(){
        context = FacesContext.getCurrentInstance();
        dao = new MaterialDAO();
        material = (Material) context.getExternalContext().getSessionMap().get("materialSelecionado");
        if (material == null) {
            material = new Material();
            especificacoes = dao.carregarEspecificacoes();
            marcas = dao.carregarMarcas();
            permanente = false;
        } else{
            especificacoes.add(material.getEspecificacao());
            marcas.add(material.getMarca());
            permanente = material.getPermanente();
        }
    }
    
    public String cadastrar(){
        if (permanente) {
            if (tombamento != null && !tombamento.isEmpty()) {
                mostrarMensagem("Material registrado com sucesso!");
                return "/view/index?faces-redirect=true";
            }
        } else{
            mostrarMensagem("Material registrado com sucesso!");
            return "/view/index?faces-redirect=true";
        }
        return "/view/novoMaterial?faces-redirect=true";
    }
    
    public String cancelar(){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("materialSelecionado");
        return "/view/aquisicoes?faces-redirect=true";
    }
    
    private void mostrarMensagem(String mensagem){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Boolean getPermanente() {
        return permanente;
    }

    public void setPermanente(Boolean permanente) {
        this.permanente = permanente;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(Integer valorResidual) {
        this.valorResidual = valorResidual;
    }

    public String getTombamento() {
        return tombamento;
    }

    public void setTombamento(String tombamento) {
        this.tombamento = tombamento;
    }

    public List<Especificacao> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(List<Especificacao> especificacoes) {
        this.especificacoes = especificacoes;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public Integer getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Integer especificacao) {
        this.especificacao = especificacao;
    }

    public Integer getMarca() {
        return marca;
    }

    public void setMarca(Integer marca) {
        this.marca = marca;
    }
}