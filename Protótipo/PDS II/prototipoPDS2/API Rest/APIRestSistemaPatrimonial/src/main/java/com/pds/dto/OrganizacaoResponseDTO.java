package com.pds.dto;

import java.util.Date;

import com.pds.model.Organizacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 
 * DTO de Organização a ser utilizado na resposta
 */
@AllArgsConstructor /** Cria um construtor com todos os atributos */
@Getter 			/** Cria os getters */
public class OrganizacaoResponseDTO {

	private Long id;
	private String nome;
	private Date dataAtualizacaoValor;
	private Double valorPatrimonial;
	private Boolean bloqueado;
	
	/** 
	 * Método para gerar DTO com base na Organização
	 * @param Organizacao
	 * @return OrganizacaoResponseDTO
	 */
	public static OrganizacaoResponseDTO generationDTO(Organizacao organizacao) {
        return new OrganizacaoResponseDTO(organizacao.getId(), organizacao.getNome(),organizacao.getDataAtualizacaoValor(), organizacao.getValorPatrimonial(), organizacao.getBloqueado());
    }
}