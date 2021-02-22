package com.pds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 		/** Cria um construtor vazio */
@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class Manutencao{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=255)
	private String descricao;
	
	@Column(nullable=false, length=100)
	private String empresa;
	
	@Column(nullable=false)
	private Double valor;
	
	/** Configura o Date para ser compatível com o banco de dados */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private ItemPermanente item;
	
	@Column(nullable=false)
	private Boolean agregaValorPatrimonial;
	
}