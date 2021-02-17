package com.pds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.dto.OrganizacaoResponseDTO;
import com.pds.model.Organizacao;
import com.pds.repository.OrganizacaoRepository;

@Service	/** Classe fornecedora dos serviços de acesso ao repository. */
public class OrganizacaoService {

	private final OrganizacaoRepository organizacaoRepository;

	@Autowired		/** Injeção de dependências*/
    public OrganizacaoService(OrganizacaoRepository organizacaoRepository) {
        this.organizacaoRepository = organizacaoRepository;
    }

	/**
	 * Método para salvar uma nova organização.
	 * Dados validados e em formato correspondente.
	 * @param organizacao Organizacao
	 * @return Organizacao
	 */
    public Organizacao salvar(Organizacao organizacao) {
        return organizacaoRepository.save(organizacao);
    }
    
    /**
     * Método para alterar uma organização.
     * Dados validados e em formato correspondente.
     * Verifica se a Organização que será alterada está salva no banco.
     * Se estiver, recupera os dados de Valor Patrimonial e Data de Atualização do valor salvos.
     * Esses dados não poderão ser alterados pelo cliente, pois serão alterados pelo sistema.
     * Salva as alterações e retorna os dados salvos.
     * Se não estiver, retorna um objeto Organizacao vazio.
     * @param organizacao Organizacao
     * @return Organizacao
     */
    public Organizacao alterar(Organizacao organizacao) {
    	Optional<Organizacao> organizacaoSaved = organizacaoRepository.findById(organizacao.getId());
        if(organizacaoSaved.isPresent()) {
        	organizacao.setDataAtualizacaoValor(organizacaoSaved.get().getDataAtualizacaoValor());
        	organizacao.setValorPatrimonial(organizacaoSaved.get().getValorPatrimonial());
        	return organizacaoRepository.save(organizacao);
        }
        return new Organizacao();
    }
    
    /**
     * Método para pesquisar uma organização.
     * Verifica se a Organização está salva no banco.
     * Se estiver, retorna os dados salvos.
     * Se não estiver, retorna um objeto Organizacao vazio.
     * @param id Long
     * @return Organizacao
     */
    public Organizacao pesquisar(Long id) {
    	Optional<Organizacao> organizacao = organizacaoRepository.findById(id);
        if(organizacao.isPresent()) {
        	return organizacao.get();
        }
        return new Organizacao();
    }
    
    /**
     * Método para pesquisar organizações com determinado acesso.
     * Verifica se existem Organizações com determinado acesso.
     * Se sim, retorna uma lista com os dados salvos.
     * Se não, retorna uma lista vazia.
     * @param bloqueado Boolean
     * @return List<OrganizacaoResponseDTO>
     */
    public List<OrganizacaoResponseDTO> listar(Boolean bloqueado) {
    	return organizacaoRepository.findByBloqueado(bloqueado);
    }
}