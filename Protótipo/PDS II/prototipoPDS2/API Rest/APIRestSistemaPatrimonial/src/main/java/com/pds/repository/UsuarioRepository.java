package com.pds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pds.model.Organizacao;
import com.pds.model.Usuario;

@Repository /** Interface de DAO utilizando os métodos prontos do JpaRepository */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	/**	
	 * Método para pesquisar todos os usuarios de uma organização 
	 * filtrando por estar ou não bloqueado e ser ou não um estagiário.
	 * @param organizacao Organizacao
	 * @param bloqueado Boolean
	 * @param estagiario Boolean
	 * @return List<Usuario>
	 */
	@Query("select u from Usuario u " + 
				"inner join fetch u.funcionario f where f.organizacao = :organizacao and " + 
				"u.bloqueado = :bloqueado and u.estagiario = :estagiario")
	public List<Usuario> findByOrganizacao(
			@Param("organizacao") Organizacao organizacao, 
			@Param("bloqueado") Boolean bloqueado, 
			@Param("estagiario") Boolean estagiario);
	
	/**
	 * Método para pesquisar um usuário de uma organização 
	 * filtrando pelo login e senha.
	 * @param organizacao Organizacao 
	 * @param login String
	 * @param senha String
	 * @return Optional<Usuario>
	 */
	@Query("select u from Usuario u " +
			"inner join fetch u.funcionario f where f.organizacao = :organizacao and " +
			"u.login = :login and u.senha = :senha")
	public Optional<Usuario> findByLoginSenha(
			@Param("organizacao") Organizacao organizacao, 
			@Param("login") String login, 
			@Param("senha") String senha);
}