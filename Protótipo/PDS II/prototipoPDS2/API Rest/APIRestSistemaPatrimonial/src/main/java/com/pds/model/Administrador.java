package com.pds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 					/** Cria um construtor vazio */
@AllArgsConstructor 				/** Cria um construtor com todos os atributos */
@Data 								/** Cria getters e setters */
@Entity 							/** Mapeamento da Entidade */
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String nome;
	
	@Column(nullable=false, length=150)
	private String email;
	
	@Column(nullable=false, length=50)
	private String login;
	
	@Column(nullable=false, length=50)
	private String senha;
	
	@Column(nullable=false)
	private Boolean bloqueado;
	
	/** 
	 * Construtor utilizado para salvar um novo administrador
	 * @param nome String
	 * @param email String
	 * @param login String
	 * @param senha String
	 * @param bloqueado Boolean
	 */
	public Administrador(String nome, String email, String login, String senha, Boolean bloqueado) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.bloqueado = bloqueado;
	}
}