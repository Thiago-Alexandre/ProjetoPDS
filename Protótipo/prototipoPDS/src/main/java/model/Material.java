package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Material")
public class Material implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="descricao")
    private String descricao;
    
    @Column(name="permanente")
    private Boolean permanente;
    
    @Column(name="valorAquisicao")
    private Double valorAquisicao;
    
    @Column(name="quantidadeAquisicao")
    private Double quantidadeAquisicao;
    
    @Column(name="quantidadeAtual")
    private Double quantidadeAtual;
    
    @Column(name="garantia")
    private Integer garantia;
    
    @Column(name="cargaPatrimonial")
    private Boolean cargaPatrimonial;
    
    @ManyToOne
    @JoinColumn(name="especificacao")
    private Especificacao especificacao;
    
    @ManyToOne
    @JoinColumn(name="marca")
    private Marca marca;
    
    @ManyToOne
    @JoinColumn(name="aquisicao")
    private Aquisicao aquisicao;
    
    @ManyToOne
    @JoinColumn(name="setorAtual")
    private Setor setorAtual;
    
    @ManyToOne
    @JoinColumn(name="responsavelAtual")
    private Responsavel responsavelAtual;
    
    @OneToOne
    @JoinColumn(name="patrimonio")
    private ItemPatrimonio patrimonio;

    public Material() {}

    public Material(Integer id, String descricao, Boolean permanente, Double valorAquisicao, Double quantidadeAquisicao, Double quantidadeAtual, Integer garantia, 
            Boolean cargaPatrimonial, Especificacao especificacao, Marca marca, Aquisicao aquisicao, Setor setorAtual, Responsavel responsavelAtual, ItemPatrimonio itemPatrimonio) {
        this.id = id;
        this.descricao = descricao;
        this.permanente = permanente;
        this.valorAquisicao = valorAquisicao;
        this.quantidadeAquisicao = quantidadeAquisicao;
        this.quantidadeAtual = quantidadeAtual;
        this.garantia = garantia;
        this.cargaPatrimonial = cargaPatrimonial;
        this.especificacao = especificacao;
        this.marca = marca;
        this.aquisicao = aquisicao;
        this.setorAtual = setorAtual;
        this.responsavelAtual = responsavelAtual;
        this.patrimonio = itemPatrimonio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPermanente() {
        return permanente;
    }

    public void setPermanente(Boolean permanente) {
        this.permanente = permanente;
    }

    public Double getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(Double valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public Double getQuantidadeAquisicao() {
        return quantidadeAquisicao;
    }

    public void setQuantidadeAquisicao(Double quantidadeAquisicao) {
        this.quantidadeAquisicao = quantidadeAquisicao;
    }

    public Double getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Double quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public Integer getGarantia() {
        return garantia;
    }

    public void setGarantia(Integer garantia) {
        this.garantia = garantia;
    }

    public Boolean getCargaPatrimonial() {
        return cargaPatrimonial;
    }

    public void setCargaPatrimonial(Boolean cargaPatrimonial) {
        this.cargaPatrimonial = cargaPatrimonial;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Aquisicao getAquisicao() {
        return aquisicao;
    }

    public void setAquisicao(Aquisicao aquisicao) {
        this.aquisicao = aquisicao;
    }

    public Setor getSetorAtual() {
        return setorAtual;
    }

    public void setSetorAtual(Setor setorAtual) {
        this.setorAtual = setorAtual;
    }

    public Responsavel getResponsavelAtual() {
        return responsavelAtual;
    }

    public void setResponsavelAtual(Responsavel responsavelAtual) {
        this.responsavelAtual = responsavelAtual;
    }

    public ItemPatrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(ItemPatrimonio itemPatrimonio) {
        this.patrimonio = itemPatrimonio;
    }
}