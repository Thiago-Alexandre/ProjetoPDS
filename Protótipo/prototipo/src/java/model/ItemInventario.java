package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Item")
public class ItemInventario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="ativo")
    private boolean ativo;
    @ManyToOne
    @JoinColumn(name="setor")
    private Setor setor;
    @ManyToOne
    @JoinColumn(name="situacao")
    private Situacao situacao;
    @ManyToOne
    @JoinColumn(name="material")
    private Material material;
    @ManyToOne
    @JoinColumn(name="inventario")
    private Inventario inventario;

    public ItemInventario() {}

    public ItemInventario(Integer id, boolean ativo, Setor setor, Situacao situacao, Material material, Inventario inventario) {
        this.id = id;
        this.ativo = ativo;
        this.setor = setor;
        this.situacao = situacao;
        this.material = material;
        this.inventario = inventario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}