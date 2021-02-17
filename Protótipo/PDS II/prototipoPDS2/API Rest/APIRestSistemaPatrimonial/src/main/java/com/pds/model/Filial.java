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
@Data 					/** Cria getters e setters */
@Entity 				/** Mapeamento da Entidade */
public class Filial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Organizacao organizacao;
	
	/** 
	 * Construtor utilizado para salvar uma nova filial
	 * @param nome String
	 * @param organizacao Organizacao
	 */
	public Filial(String nome) {
		this.nome = nome;
	}
	
	/** 
	 * Construtor utilizado para alterar uma filial
	 * Neste caso, não é possível alterar a organização de uma filial
	 * @param id Long
	 * @param nome String
	 */
	public Filial(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}