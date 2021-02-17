package com.pds.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pds.model.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Data 					/** Cria getters e setters */

/**
 * DTO de Funcionario a ser utilizado para alterar dados
 * Usado para realizar a validação dos dados informados
 */
public class FuncionarioForUpdateDTO {

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
	
	/** 
	 * Método para gerar objeto Funcionario após ser validado o DTO
	 * @return Funcionario
	 */
	public Funcionario generationObject() {
		return new Funcionario(id, nome, email);
	}
}