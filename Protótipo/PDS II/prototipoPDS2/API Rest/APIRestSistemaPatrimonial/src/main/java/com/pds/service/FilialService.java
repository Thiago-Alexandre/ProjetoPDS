package com.pds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.dto.FilialResponseDTO;
import com.pds.model.Filial;
import com.pds.model.Organizacao;
import com.pds.repository.FilialRepository;

@Service		/** Classe fornecedora dos serviços de acesso ao repository. */
public class FilialService {

	private final FilialRepository filialRepository;
	private final OrganizacaoService organizacaoService;

	@Autowired		/** Injeção de dependências*/
    public FilialService(FilialRepository filialRepository, OrganizacaoService organizacaoService) {
        this.filialRepository = filialRepository;
        this.organizacaoService = organizacaoService;
    }

	/**
	 * Método para salvar uma nova filial.
	 * Dados validados e em formato correspondente.
	 * Verifica se a Organização que será vinculada a nova filial está salva no banco;
	 * Se estiver, os dados da organização são vinculados na nova filial.
	 * A filial é salva e retorna os dados salvos.
	 * Se não estiver, retorna um objeto Filial vazio.
	 * @param filial Filial
	 * @param idOrganizacao Long
	 * @return Filial
	 */
    public Filial salvar(Filial filial, Long idOrganizacao) {
    	Organizacao organizacaoPesquisada = organizacaoService.pesquisar(idOrganizacao);
		if(organizacaoPesquisada.getId() != null) {
			filial.setOrganizacao(organizacaoPesquisada);
			return filialRepository.save(filial);
		}
        return new Filial();
    }
    
    /**
     * Método para alterar os dados de uma filial.
     * Dados validados e em formato correspondente.
     * Verifica se a filial que será alterada está salva no banco.
     * Se estiver, recupera os dados da Organização vinculada.
     * A Organização vinculada à Filial não poderá ser alterada pelo cliente.
     * Salva as alterações e retorna os dados salvos.
     * Se não estiver, retorna um objeto Filial vazio.
     * @param filial Filial
     * @return Filial
     */
    public Filial alterar(Filial filial) {
    	Optional<Filial> filialSaved = filialRepository.findById(filial.getId());
        if(filialSaved.isPresent()) {
        	filial.setOrganizacao(filialSaved.get().getOrganizacao());
        	return filialRepository.save(filial);
        }
        return new Filial();
    }
    
    /**
     * Método para pesquisar uma filial.
     * Verifica se a Filial está salva no banco.
     * Se estiver, retorna os dados salvos;
     * Se não estiver, retorna um objeto Filial vazio.
     * @param id Long
     * @return Filial
     */
    public Filial pesquisar(Long id) {
    	Optional<Filial> filial = filialRepository.findById(id);
        if(filial.isPresent()) {
        	return filial.get();
        }
        return new Filial();
    }
    
    /**
     * Método para pesquisar filiais de determinada Organização.
     * Verifica se existem Filiais com determinada Organização vinculada.
     * Se sim, retorna uma lista com os dados salvos.
     * Se não, retorna uma lista vazia.
     * @param organizacao Long
     * @return List<FilialResponseDTO>
     */
    public List<FilialResponseDTO> listar(Long organizacao) {
    	Organizacao organizacaoPesquisada = organizacaoService.pesquisar(organizacao);
    	if(organizacaoPesquisada.getId() != null) {
			List<FilialResponseDTO> filiais = filialRepository.findByOrganizacao(organizacaoPesquisada);
			if(!filiais.isEmpty()) {
	            return filiais;
	        }
		}
		return new ArrayList<FilialResponseDTO>() ;
    }
}