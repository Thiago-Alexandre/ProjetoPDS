package com.pds.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/** 
 * DTO de Usuario a ser utilizado para realizar login
 * Usado para realizar a validação dos dados informados
 */
@Data /** Cria getters e setters */
public class UsuarioLoginDTO {

	@NotNull(message = "ID da Organização é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long organizacao;
	
	@NotNull(message = "Login é obrigatório!")
	@NotBlank(message = "Login não pode ser vazio!")
	@NotEmpty(message = "Login não pode ser vazio!")
	@Size(max = 50, message = "Tamanho máximo do login é de 50 caracteres!")
	private String login;
	
	@NotNull(message = "Senha é obrigatório!")
	@NotBlank(message = "Senha não pode ser vazio!")
	@NotEmpty(message = "Senha não pode ser vazio!")
	@Size(max = 100, message = "Tamanho máximo da senha é de 50 caracteres!")
	private String senha;
	
}