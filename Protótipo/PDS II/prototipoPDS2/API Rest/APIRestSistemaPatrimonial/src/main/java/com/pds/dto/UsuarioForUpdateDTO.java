package com.pds.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pds.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Usuario a ser utilizado para alterar dados
 * Usado para realizar a validação dos dados informados
 */
@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Data 					/** Cria getters e setters */
public class UsuarioForUpdateDTO {

	@NotNull(message = "ID é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long id;
	
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
	
	@NotNull(message = "Valor de Estagiário é obrigatório!")
	private Boolean estagiario;
	
	/** 
	 * Método para gerar objeto Filial após ser validado o DTO
	 * @return Filial
	 */
	public Usuario generationObject() {
		return new Usuario(id, login, senha, bloqueado, estagiario);
	}
}