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
@Table(name="Baixa")
public class Baixa implements Serializable {
    
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
    @JoinColumn(name="formaBaixa")
    private FormaBaixa forma;
    
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy="baixa")
    private List<ItemPatrimonio> itensPatrimonio;

    public Baixa() {}

    public Baixa(Integer id, Date data, String detalhes, FormaBaixa forma, Usuario usuario, List<ItemPatrimonio> itensPatrimonio) {
        this.id = id;
        this.data = data;
        this.detalhes = detalhes;
        this.forma = forma;
        this.usuario = usuario;
        this.itensPatrimonio = itensPatrimonio;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public FormaBaixa getForma() {
        return forma;
    }

    public void setForma(FormaBaixa forma) {
        this.forma = forma;
    }

    public List<ItemPatrimonio> getItensPatrimonio() {
        return itensPatrimonio;
    }

    public void setItensPatrimonio(List<ItemPatrimonio> itensPatrimonio) {
        this.itensPatrimonio = itensPatrimonio;
    }
}