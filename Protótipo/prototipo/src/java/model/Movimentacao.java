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
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="Movimentacao")
public class Movimentacao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="datahora")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data;
    @Column(name="detalhes")
    private String detalhes;
    @Column(name="quantidade")
    private Double quantidade;
    @ManyToOne
    @JoinColumn(name="tipo")
    private TipoMovimentacao tipo;
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="material")
    private Material material;
    @ManyToOne
    @JoinColumn(name="setorAntigo")
    private Setor setorAntigo;
    @ManyToOne
    @JoinColumn(name="setorNovo")
    private Setor setorNovo;

    public Movimentacao() {}

    public Movimentacao(Integer id, Date data, String detalhes, Double quantidade, TipoMovimentacao tipo, Usuario usuario, Material material, Setor setorAntigo, Setor setorNovo) {
        this.id = id;
        this.data = data;
        this.detalhes = detalhes;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.usuario = usuario;
        this.material = material;
        this.setorAntigo = setorAntigo;
        this.setorNovo = setorNovo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Setor getSetorAntigo() {
        return setorAntigo;
    }

    public void setSetorAntigo(Setor setorAntigo) {
        this.setorAntigo = setorAntigo;
    }

    public Setor getSetorNovo() {
        return setorNovo;
    }

    public void setSetorNovo(Setor setorNovo) {
        this.setorNovo = setorNovo;
    }
}