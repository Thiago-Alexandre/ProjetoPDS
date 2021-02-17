package com.pds.dto;

import com.pds.model.Funcionario;
import com.pds.model.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;

/** DTO de Administrador a ser utilizado na resposta */
@Getter 							/** Cria os getters */
@NoArgsConstructor 					/** Cria um construtor vazio */
public class UsuarioResponseDTO {

	private Long id;
	private String nome;
	private String email;
	private String login;
	private Boolean bloqueado;
	private Boolean estagiario;
	
	/** 
	 * Método para gerar DTO com base no Usuario
	 * @param user Usuario
	 * @return UsuarioResponseDTO
	 */
	public static UsuarioResponseDTO generationDTO(Usuario user) {
        return new UsuarioResponseDTO(user.getId(), user.getLogin(), user.getBloqueado(), user.getEstagiario(), user.getFuncionario());
    }
	
	public UsuarioResponseDTO(Long id, String login, Boolean bloqueado, Boolean estagiario, Funcionario funcionario) {
		this.id = id;
		this.login = login;
		this.bloqueado = bloqueado;
		this.estagiario = estagiario;
		this.nome = funcionario.getNome();
		this.email = funcionario.getEmail();
	}
}