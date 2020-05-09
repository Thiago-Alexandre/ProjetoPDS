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
@Table(name="Patrimonio")
public class Patrimonio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="tombamento")
    private String tombamento;
    @Column(name="ativo")
    private boolean ativo;
    @Column(name="vidaUtil")
    private Integer vidaUtil;
    @ManyToOne
    @JoinColumn(name="setor")
    private Setor setorAtual;
    @ManyToOne
    @JoinColumn(name="situacao")
    private Situacao situacaoAtual;
    @OneToOne(mappedBy="patrimonio")
    private Material material;

    public Patrimonio() {}

    public Patrimonio(Integer id, String tombamento, boolean ativo, Integer vidaUtil, Setor setorAtual, Situacao situacaoAtual) {
        this.id = id;
        this.tombamento = tombamento;
        this.ativo = ativo;
        this.vidaUtil = vidaUtil;
        this.setorAtual = setorAtual;
        this.situacaoAtual = situacaoAtual;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Setor getSetorAtual() {
        return setorAtual;
    }

    public void setSetorAtual(Setor setorAtual) {
        this.setorAtual = setorAtual;
    }

    public Situacao getSituacaoAtual() {
        return situacaoAtual;
    }

    public void setSituacaoAtual(Situacao situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}