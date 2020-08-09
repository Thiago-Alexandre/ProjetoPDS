package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="ItemPatrimonio")
public class ItemPatrimonio implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="tombamento")
    private String tombamento;
    
    @Column(name="ativo")
    private Boolean ativo;
    
    @Column(name="vidaUtil")
    private Integer vidaUtil;
    
    @Column(name="valorResidual")
    private Double valorResidual;
    
    @ManyToOne
    @JoinColumn(name="estado")
    private EstadoFisico estado;
    
    @ManyToOne
    @JoinColumn(name="baixa")
    private Baixa baixa;
    
    @OneToOne(mappedBy="patrimonio")
    private Material material;

    public ItemPatrimonio() {}

    public ItemPatrimonio(Integer id, String tombamento, Boolean ativo, Integer vidaUtil, Double valorResidual, EstadoFisico estado, Baixa baixa, Material material) {
        this.id = id;
        this.tombamento = tombamento;
        this.ativo = ativo;
        this.vidaUtil = vidaUtil;
        this.valorResidual = valorResidual;
        this.estado = estado;
        this.baixa = baixa;
        this.material = material;
    }
    
    public Double calcularDepreciacao(){
        Double depreciacao = null;
        Integer taxa = 100/vidaUtil;
        /*
            Pegar a quantidade de anos/meses ou armazenar o valor de depreciação
        */
        depreciacao = taxa * (material.getValorAquisicao() - valorResidual);
        return depreciacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTombamento() {
        return tombamento;
    }

    public void setTombamento(String tombamento) {
        this.tombamento = tombamento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Double getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(Double valorResidual) {
        this.valorResidual = valorResidual;
    }

    public EstadoFisico getEstado() {
        return estado;
    }

    public void setEstado(EstadoFisico estado) {
        this.estado = estado;
    }

    public Baixa getBaixa() {
        return baixa;
    }

    public void setBaixa(Baixa baixa) {
        this.baixa = baixa;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}