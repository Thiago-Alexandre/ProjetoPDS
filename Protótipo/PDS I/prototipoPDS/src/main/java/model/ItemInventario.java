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
@Table(name="ItemInventario")
public class ItemInventario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="ativo")
    private Boolean ativo;
    
    @ManyToOne
    @JoinColumn(name="setor")
    private Setor setor;
    
    @ManyToOne
    @JoinColumn(name="responsavel")
    private Responsavel responsavel;
    
    @ManyToOne
    @JoinColumn(name="estado")
    private EstadoFisico estado;
    
    @ManyToOne
    @JoinColumn(name="material")
    private Material material;
    
    @ManyToOne
    @JoinColumn(name="inventario")
    private PreInventario inventario;

    public ItemInventario() {}

    public ItemInventario(Integer id, Boolean ativo, Setor setor, Responsavel responsavel, EstadoFisico estado, Material material, PreInventario inventario) {
        this.id = id;
        this.ativo = ativo;
        this.setor = setor;
        this.responsavel = responsavel;
        this.estado = estado;
        this.material = material;
        this.inventario = inventario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public EstadoFisico getEstado() {
        return estado;
    }

    public void setEstado(EstadoFisico estado) {
        this.estado = estado;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public PreInventario getInventario() {
        return inventario;
    }

    public void setInventario(PreInventario inventario) {
        this.inventario = inventario;
    }
}