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
@Table(name="Baixa")
public class Baixa implements Serializable {
    
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
    @ManyToOne
    @JoinColumn(name="tipo")
    private TipoBaixa tipo;
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name="material")
    private Material material;

    public Baixa() {}

    public Baixa(Integer id, Date data, String detalhes, TipoBaixa tipo, Usuario usuario, Material material) {
        this.id = id;
        this.data = data;
        this.detalhes = detalhes;
        this.tipo = tipo;
        this.usuario = usuario;
        this.material = material;
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

    public TipoBaixa getTipo() {
        return tipo;
    }

    public void setTipo(TipoBaixa tipo) {
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
}