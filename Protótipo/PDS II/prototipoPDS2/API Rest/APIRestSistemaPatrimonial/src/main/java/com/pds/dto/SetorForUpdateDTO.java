package com.pds.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pds.model.Setor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de Setor a ser utilizado para alterar dados
 * Usado para realizar a validação dos dados informados
 */
@AllArgsConstructor 	/** Cria um construtor com todos os atributos */
@Data 					/** Cria getters e setters */
public class SetorForUpdateDTO {

	@NotNull(message = "ID é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long id;
	
	@NotNull(message = "Nome é obrigatório!")
	@NotBlank(message = "Nome não pode ser vazio!")
	@NotEmpty(message = "Nome não pode ser vazio!")
	@Size(max = 100, message = "Tamanho máximo do nome é de 100 caracteres!")
	private String nome;
	
	@NotNull(message = "ID do Responsável é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long responsavel;
	
	/** 
	 * Método para gerar objeto Setor após ser validado o DTO
	 * @return Setor
	 */
	public Setor generationObject() {
		return new Setor(id, nome);
	}
}