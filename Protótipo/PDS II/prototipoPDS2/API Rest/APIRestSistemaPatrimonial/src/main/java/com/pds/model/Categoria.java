package com.pds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 		/** Cria um construtor vazio */
@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Organizacao organizacao;
}