package com.pds.dto;

import com.pds.model.Filial;
import com.pds.model.Organizacao;

import lombok.Getter;

/** DTO de Filial a ser utilizado na resposta */
@Getter /** Cria os getters */
public class FilialResponseDTO {

	private Long id;
	private String nome;
	private Long organizacao;
	
	public FilialResponseDTO(Long id, String nome, Organizacao organizacao) {
		this.id = id;
		this.nome = nome;
		this.organizacao = organizacao.getId();
	}
	
	/** 
	 * Método para gerar DTO com base na Filial
	 * @param filial Filial
	 * @return FilialResponseDTO
	 */
	public static FilialResponseDTO generationDTO(Filial filial) {
        return new FilialResponseDTO(filial.getId(), filial.getNome(), filial.getOrganizacao());
    }
}