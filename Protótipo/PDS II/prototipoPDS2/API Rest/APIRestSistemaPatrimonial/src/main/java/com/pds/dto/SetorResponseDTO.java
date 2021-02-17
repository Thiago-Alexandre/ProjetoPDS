package com.pds.dto;

import com.pds.model.Filial;
import com.pds.model.Funcionario;
import com.pds.model.Setor;

import lombok.Getter;

/** DTO de Filial a ser utilizado na resposta */
@Getter /** Cria os getters */
public class SetorResponseDTO {

	private Long id;
	private String nome;
	private Long filial;
	private Long responsavel;
	
	public SetorResponseDTO (Long id, String nome, Filial filial, Funcionario responsavel) {
        this.id = id;
        this.nome = nome;
        this.filial = filial.getId();
        this.responsavel = responsavel.getId();
    }
	
	/** 
	 * Método para gerar DTO com base no Setor
	 * @param setor Setor
	 * @return SetorResponseDTO
	 */
	public static SetorResponseDTO generationDTO(Setor setor) {
        return new SetorResponseDTO(setor.getId(), setor.getNome(), setor.getFilial(), setor.getResponsavel());
    }
}