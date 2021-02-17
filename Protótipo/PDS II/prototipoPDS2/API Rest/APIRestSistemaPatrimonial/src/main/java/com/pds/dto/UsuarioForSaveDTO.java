package com.pds.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pds.model.Usuario;

import lombok.Data;

/** 
 * DTO de Usuario a ser utilizado para salvar novo Usuario
 * Com isso, impede-se que dados indesejáveis sejam informados na hora de salvar no banco
 * Usado também para realizar a validação dos dados informados
 */
@Data /** Cria getters e setters */
public class UsuarioForSaveDTO {
	
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
	
	@NotNull(message = "Valor de Acesso é obrigatório!")
	private Boolean bloqueado;
	
	@NotNull(message = "Valor de Estagiário é obrigatório!")
	private Boolean estagiario;
	
	@NotNull(message = "ID do Funcionário é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long funcionario;
	
	/** 
	 * Método para gerar objeto Usuario após ser validado o DTO
	 * @return Usuario
	 */
	public Usuario generationObject() {
		return new Usuario(login, senha, bloqueado, estagiario, null);
	}
}