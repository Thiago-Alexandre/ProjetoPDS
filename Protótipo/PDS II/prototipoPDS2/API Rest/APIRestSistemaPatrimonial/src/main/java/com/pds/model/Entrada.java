package com.pds.model;

import java.util.ArrayList;
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
import javax.persistence.Temporal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor 		/** Cria um construtor vazio */
@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Entity
public abstract class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String fornecedor;
	
	/** Configura o Date para ser compatível com o banco de dados */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataEntrada;
	
	@OneToMany(mappedBy = "entrada")
	private List<Material> itens = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Usuario usuario;
	
	public abstract Double calcularCusto();
}