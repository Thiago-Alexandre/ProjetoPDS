package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="Material")
public class Material implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="descricao")
    private String descricao;
    @Column(name="valorAquisicao")
    private Double valorAquisicao;
    @Column(name="quantidadeAquisicao")
    private Double quantidadeAquisicao;
    @Column(name="prazoGarantia")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date prazoGarantia;
    @ManyToOne
    @JoinColumn(name="tipo")
    private TipoMaterial tipo;
    @ManyToOne
    @JoinColumn(name="especificacao")
    private Especificacao especificacao;
    @ManyToOne
    @JoinColumn(name="fornecedor")
    private Fornecedor fornecedor;
    @ManyToOne
    @JoinColumn(name="aquisicao")
    private Aquisicao aquisicao;
    @OneToOne
    @JoinColumn(name="patrimonio")
    private Patrimonio patrimonio;

    public Material() {}

    public Material(Integer id, String descricao, Double valorAquisicao, Double quantidadeAquisicao, Date prazoGarantia, TipoMaterial tipo, Especificacao especificacao, Fornecedor fornecedor, Aquisicao aquisicao) {
        this.id = id;
        this.descricao = descricao;
        this.valorAquisicao = valorAquisicao;
        this.quantidadeAquisicao = quantidadeAquisicao;
        this.prazoGarantia = prazoGarantia;
        this.tipo = tipo;
        this.especificacao = especificacao;
        this.fornecedor = fornecedor;
        this.aquisicao = aquisicao;
        this.patrimonio = null;
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

    public Date getPrazoGarantia() {
        return prazoGarantia;
    }

    public void setPrazoGarantia(Date prazoGarantia) {
        this.prazoGarantia = prazoGarantia;
    }

    public TipoMaterial getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaterial tipo) {
        this.tipo = tipo;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Aquisicao getAquisicao() {
        return aquisicao;
    }

    public void setAquisicao(Aquisicao aquisicao) {
        this.aquisicao = aquisicao;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }
}