package com.pds.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pds.model.Administrador;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Usuario a ser utilizado para alterar dados
 * Usado para realizar a validação dos dados informados
 */
@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Data 					/** Cria getters e setters */
public class AdministradorForUpdateDTO {
	
	@NotNull(message = "ID é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long id;
	
	@NotBlank(message = "Nome não pode ser vazio!")
	@NotEmpty(message = "Nome não pode ser vazio!")
	@Size(max = 100, message = "Tamanho máximo do nome é de 100 caracteres!")
	private String nome;
	
	@NotNull(message = "E-mail é obrigatório!")
	@NotBlank(message = "E-mail não pode ser vazio!")
	@NotEmpty(message = "E-mail não pode ser vazio!")
	@Size(max = 150, message = "Tamanho máximo do e-mail é de 150 caracteres!")
	@Email(message="E-mail inválido!")
	private String email;
	
	@NotNull(message = "Login é obrigatório!")
	@NotBlank(message = "Login não pode ser vazio!")
	@NotEmpty(message = "Login não pode ser vazio!")
	@Size(max = 50, message = "Tamanho máximo do login é de 50 caracteres!")
	private String login;
	
	@NotNull(message = "Senha é obrigatório!")
	@NotBlank(message = "Senha não pode ser vazio!")
	@NotEmpty(message = "Senha não pode ser vazio!")
	@Size(max = 50, message = "Tamanho máximo da senha é de 50 caracteres!")
	private String senha;
	
	@NotNull(message = "Valor de Acesso é obrigatório!")
	private Boolean bloqueado;
	
	/** 
	 * Método para gerar objeto Administrador após ser validado o DTO
	 * @return Administrador
	 */
	public Administrador generationObject() {
		return new Administrador(id, nome, email, login, senha, bloqueado);
	}
}