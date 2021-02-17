package com.pds.dto;

import com.pds.model.Administrador;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** DTO de Administrador a ser utilizado na resposta */
@AllArgsConstructor /** Cria um construtor com todos os atributos */
@Getter /** Cria os getters */
public class AdministradorResponseDTO {

	private Long id;
	private String nome;
	private String email;
	private String login;
	private Boolean bloqueado;
	
	/** 
	 * Método para gerar DTO com base no Administrador
	 * @param admin Administrador
	 * @return AdministradorResponseDTO
	 */
	public static AdministradorResponseDTO generationDTO(Administrador admin) {
        return new AdministradorResponseDTO(admin.getId(), admin.getNome(), admin.getEmail(), admin.getLogin(), admin.getBloqueado());
    }
}