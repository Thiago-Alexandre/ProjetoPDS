package com.pds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pds.dto.AdministradorResponseDTO;
import com.pds.model.Administrador;

@Repository /** Interface de DAO utilizando os métodos prontos do JpaRepository */
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	/**	
	 * Método para pesquisar todos os administradores com acesso bloqueado ou não
	 * @param bloqueado Boolean
	 * @return List<AdministradorResponseDTO>
	 */
	public List<AdministradorResponseDTO> findByBloqueado(Boolean bloqueado);
	
	/**
	 * Método para pesquisar um administrador filtrando pelo login e senha.
	 * @param login String
	 * @param senha String
	 * @return Optional<Administrador>
	 */
	@Query("select a from Administrador a where a.login = :login and a.senha = :senha")
	public Optional<Administrador> findByLoginSenha(
			@Param("login") String login, 
			@Param("senha") String senha);
}