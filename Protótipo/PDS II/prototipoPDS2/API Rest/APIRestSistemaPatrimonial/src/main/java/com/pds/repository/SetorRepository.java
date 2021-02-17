package com.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pds.dto.SetorResponseDTO;
import com.pds.model.Setor;
import com.pds.model.Filial;

@Repository /** Interface de DAO utilizando os métodos prontos do JpaRepository */
public interface SetorRepository extends JpaRepository<Setor, Long>{

	/**	
	 * Método para pesquisar todos os setores de uma filial
	 * @param filial Filial
	 * @return List<SetorResponseDTO>
	 */
	public List<SetorResponseDTO> findByFilial(Filial filial);
}