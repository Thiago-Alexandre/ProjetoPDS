package com.pds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.dto.FuncionarioResponseDTO;
import com.pds.model.Funcionario;
import com.pds.model.Organizacao;
import com.pds.repository.FuncionarioRepository;

@Service		/** Classe fornecedora dos serviços de acesso ao repository. */
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;
	private final OrganizacaoService organizacaoService;

	@Autowired		/** Injeção de dependências*/
    public FuncionarioService(FuncionarioRepository funcionarioRepository, OrganizacaoService organizacaoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.organizacaoService = organizacaoService;
    }

	/**
	 * Método para salvar um novo funcionário.
	 * Dados validados e em formato correspondente.
	 * Verifica se a Organização que será vinculada ao novo funcionário está salva no banco.
	 * Se estiver, os dados da organização são vinculados no novo funcionario.
	 * O funcionario é salvo e retorna os dados salvos.
	 * Se não estiver, retorna um objeto Funcionario vazio.
	 * @param funcionario Funcionario
	 * @param idOrganizacao Long
	 * @return Funcionario
	 */
    public Funcionario salvar(Funcionario funcionario, Long idOrganizacao) {
    	Organizacao organizacaoPesquisada = organizacaoService.pesquisar(idOrganizacao);
		if(organizacaoPesquisada.getId() != null) {
			funcionario.setOrganizacao(organizacaoPesquisada);
			return funcionarioRepository.save(funcionario);
		}
        return new Funcionario();
    }
    
    /**
     * Método para alterar os dados de um funcionario.
     * Dados validados e em formato correspondente.
     * Verifica se o funcionário que será alterado está salvo no banco.
     * Se estiver, recupera os dados da Organização vinculada.
     * A Organização vinculada ao Funcionario não poderá ser alterada pelo cliente.
     * Salva as alterações e retorna os dados salvos.
     * Se não estiver, retorna um objeto Funcionario vazio.
     * @param funcionario Funcionario
     * @return Funcionario
     */
    public Funcionario alterar(Funcionario funcionario) {
    	Optional<Funcionario> funcionarioSaved = funcionarioRepository.findById(funcionario.getId());
        if(funcionarioSaved.isPresent()) {
        	funcionario.setOrganizacao(funcionarioSaved.get().getOrganizacao());
        	return funcionarioRepository.save(funcionario);
        }
        return new Funcionario();
    }
    
    /**
     * Método para pesquisar um funcionario.
     * Verifica se o Funcionario está salvo no banco.
     * Se estiver, retorna os dados salvos.
     * Se não estiver, retorna um objeto Funcionario vazio.
     * @param id Long
     * @return Funcionario
     */
    public Funcionario pesquisar(Long id) {
    	Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if(funcionario.isPresent()) {
        	return funcionario.get();
        }
        return new Funcionario();
    }
    
    /**
     * Método para pesquisar funcionarios de determinada Organização.
     * Verifica se existem Funcionarios com determinada Organização vinculada.
     * Se sim, retorna uma lista com os dados salvos.
     * Se não, retorna uma lista vazia.
     * @param organizacao Long
     * @return List<FuncionarioResponseDTO>
     */
    public List<FuncionarioResponseDTO> listar(Long organizacao) {
    	Organizacao organizacaoPesquisada = organizacaoService.pesquisar(organizacao);
    	if(organizacaoPesquisada.getId() != null) {
			List<FuncionarioResponseDTO> filiais = funcionarioRepository.findByOrganizacao(organizacaoPesquisada);
			if(!filiais.isEmpty()) {
	            return filiais;
	        }
		}
		return new ArrayList<FuncionarioResponseDTO>() ;
    }
}