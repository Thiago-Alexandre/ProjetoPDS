package com.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.dto.FilialResponseDTO;
import com.pds.model.Filial;
import com.pds.model.Organizacao;

@Repository /** Interface de DAO utilizando os métodos prontos do JpaRepository */
public interface FilialRepository extends JpaRepository<Filial, Long>{

	/**	
	 * Método para pesquisar todas as filiais de uma organização
	 * @param organizacao Organizacao
	 * @return List<FilialResponseDTO>
	 */
	public List<FilialResponseDTO> findByOrganizacao(Organizacao organizacao);
}