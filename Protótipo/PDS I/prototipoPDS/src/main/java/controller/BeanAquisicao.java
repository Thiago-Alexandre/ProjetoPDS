package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.FormaAquisicao;
import model.Aquisicao;
import model.Material;
import model.Usuario;
import service.AquisicaoDAO;

@ManagedBean
@ViewScoped
public class BeanAquisicao implements Serializable{
    
    private Aquisicao aquisicao;
    private List<FormaAquisicao> formas = new ArrayList();
    private AquisicaoDAO dao;
    private FacesContext context;
    
    public BeanAquisicao(){
        context = FacesContext.getCurrentInstance();
        dao = new AquisicaoDAO();
        aquisicao = (Aquisicao) context.getExternalContext().getSessionMap().get("aquisicaoSelecionada");
        if (aquisicao == null) {
            aquisicao = new Aquisicao();
            aquisicao.setUsuario((Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado"));
            formas = dao.carregarFormas();
        } else{
            formas.add(aquisicao.getForma());
        }
    }
    
    public String cadastrar(){
        if (aquisicao.getId() != null) {
            return "/view/aquisicoes?faces-redirect=true";
        }
        mostrarMensagem("Aquisição registrada com sucesso!");
        return "/view/aquisicoes?faces-redirect=true";
    }
    
    public String cancelar(){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("aquisicaoSelecionada");
        return "/view/aquisicoes?faces-redirect=true";
    }
    
    public String verMaterial(Material materialSelecionado){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("materialSelecionado", materialSelecionado);
        return "/view/material?faces-redirect=true";
    }
    
    public String adicionarMaterial(){
        return "/view/novoMaterial?faces-redirect=true";
    }
    
    public void removerMaterial(Material materialSelecionado){
        if (aquisicao.getId() != null) {
            aquisicao.getMateriais().remove(materialSelecionado);
            mostrarMensagem("Material removido com sucesso!");
        }
    }
    
    private void mostrarMensagem(String mensagem){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("mensagem", mensagem);
    }
    
    private void removerMensagem(){
        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("mensagem");
    }

    public Aquisicao getAquisicao() {
        return aquisicao;
    }

    public void setAquisicao(Aquisicao aquisicao) {
        this.aquisicao = aquisicao;
    }

    public List<FormaAquisicao> getFormas() {
        return formas;
    }

    public void setFormas(List<FormaAquisicao> formas) {
        this.formas = formas;
    }
}