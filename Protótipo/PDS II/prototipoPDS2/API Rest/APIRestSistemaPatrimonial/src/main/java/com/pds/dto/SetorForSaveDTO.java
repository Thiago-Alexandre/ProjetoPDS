package com.pds.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pds.model.Setor;

import lombok.Data;

/** 
 * DTO de Filial a ser utilizado para salvar nova Filial
 * Com isso, impede-se que dados indesejáveis sejam informados na hora de salvar no banco
 * Usado também para realizar a validação dos dados informados
 */
@Data /** Cria getters e setters */
public class SetorForSaveDTO {

	@NotNull(message = "Nome é obrigatório!")
	@NotBlank(message = "Nome não pode ser vazio!")
	@NotEmpty(message = "Nome não pode ser vazio!")
	@Size(max = 100, message = "Tamanho máximo do nome é de 100 caracteres!")
	private String nome;
	
	@NotNull(message = "ID da Filial é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long filial;
	
	@NotNull(message = "ID do Responsável é obrigatório!")
	@Min(value = 1, message = "Não é permitido números negativos ou 0!")
	private Long responsavel;
	
	/** 
	 * Método para gerar objeto Setor após ser validado o DTO
	 * @return Setor
	 */
	public Setor generationObject() {
		return new Setor(nome);
	}
}