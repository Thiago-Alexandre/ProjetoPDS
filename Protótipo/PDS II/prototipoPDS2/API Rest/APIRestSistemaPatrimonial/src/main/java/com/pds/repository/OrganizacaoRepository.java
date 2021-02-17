package com.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.dto.OrganizacaoResponseDTO;
import com.pds.model.Organizacao;

@Repository /** Interface de DAO utilizando os métodos prontos do JpaRepository */
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long>{
	
	/**	
	 * Método para pesquisar todas as organizações com acesso bloqueado ou não
	 * @param bloqueado Boolean
	 * @return List<OrganizacaoResponseDTO>
	 */
	public List<OrganizacaoResponseDTO> findByBloqueado(Boolean bloqueado);
}