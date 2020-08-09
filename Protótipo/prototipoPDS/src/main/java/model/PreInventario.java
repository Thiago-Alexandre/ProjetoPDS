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
@Table(name="PreInventario")
public class PreInventario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="datahora")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data;
    
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy="inventario")
    private List<ItemInventario> itens;

    public PreInventario() {}

    public PreInventario(Integer id, Date data, Usuario usuario, List<ItemInventario> itens) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.itens = itens;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemInventario> getItens() {
        return itens;
    }

    public void setItens(List<ItemInventario> itens) {
        this.itens = itens;
    }
}