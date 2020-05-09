package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="Aquisicao")
public class Aquisicao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="datahora")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data;
    @Column(name="valorTotal")
    private Double valorTotal;
    @Column(name="detalhes")
    private String detalhes;
    @ManyToOne
    @JoinColumn(name="tipo")
    private TipoAquisicao tipo;
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuario;
    @OneToMany(mappedBy="aquisicao")
    private List<Material> materiais;

    public Aquisicao() {}

    public Aquisicao(Integer id, Date data, Double valorTotal, String detalhes, TipoAquisicao tipo, Usuario usuario, List<Material> materiais) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.detalhes = detalhes;
        this.tipo = tipo;
        this.usuario = usuario;
        this.materiais = materiais;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public TipoAquisicao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAquisicao tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }
}