package com.pds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.dto.SetorResponseDTO;
import com.pds.model.Setor;
import com.pds.model.Filial;
import com.pds.model.Funcionario;
import com.pds.repository.SetorRepository;

@Service		/** Classe fornecedora dos serviços de acesso ao repository. */
public class SetorService {

	private final SetorRepository setorRepository;
	private final FilialService filialService;
	private final FuncionarioService funcionarioService;

	@Autowired		/** Injeção de dependências*/
    public SetorService(SetorRepository setorRepository, FilialService filialService, FuncionarioService funcionarioService) {
        this.setorRepository = setorRepository;
        this.filialService = filialService;
        this.funcionarioService = funcionarioService;
    }

	/**
	 * Método para salvar um novo Setor.
	 * Dados validados e em formato correspondente.
	 * Verifica se a Filial que será vinculada ao novo Setor está salva no banco.
	 * Se estiver, os dados da filial são vinculados no novo Setor.
	 * O Setor é salvo e retorna os dados salvos.
	 * Se não estiver, retorna um objeto Setor vazio.
	 * @param setor Setor
	 * @param idFilial Long
	 * @return Setor
	 */
    public Setor salvar(Setor setor, Long idFilial, Long idFuncionario) {
    	Filial filialPesquisada = filialService.pesquisar(idFilial);
    	Funcionario funcionarioPesquisado = funcionarioService.pesquisar(idFuncionario);
		if(filialPesquisada.getId() != null && funcionarioPesquisado.getId() != null) {
			setor.setFilial(filialPesquisada);
			setor.setResponsavel(funcionarioPesquisado);
			return setorRepository.save(setor);
		}
        return new Setor();
    }
    
    /**
     * Método para alterar os dados de um Setor
     * Dados validados e em formato correspondente.
     * Verifica se o Setor que será alterado está salvo no banco.
     * Se estiver, recupera os dados da Filial vinculada.
     * A Filial vinculada ao Setor não poderá ser alterada pelo cliente.
     * Salva as alterações e retorna os dados salvos.
     * Se não estiver, retorna um objeto Setor vazio.
     * @param setor Setor
     * @return Setor
     */
    public Setor alterar(Setor setor, Long idFuncionario) {
    	Optional<Setor> setorSaved = setorRepository.findById(setor.getId());
    	Funcionario funcionarioPesquisado = funcionarioService.pesquisar(idFuncionario);
        if(setorSaved.isPresent()) {
        	setor.setFilial(setorSaved.get().getFilial());
        	setor.setResponsavel(funcionarioPesquisado);
        	return setorRepository.save(setor);
        }
        return new Setor();
    }
    
    /**
     * Método para pesquisar um Setor.
     * Verifica se o Setor está salvo no banco.
     * Se estiver, retorna os dados salvos.
     * Se não estiver, retorna um objeto Setor vazio.
     * @param id Long
     * @return Setor
     */
    public Setor pesquisar(Long id) {
    	Optional<Setor> setor = setorRepository.findById(id);
        if(setor.isPresent()) {
        	return setor.get();
        }
        return new Setor();
    }
    
    /**
     * Método para pesquisar setores de determinada filial.
     * Verifica se existem setores com determinada Filial vinculada.
     * Se sim, retorna uma lista com os dados salvos.
     * Se não, retorna uma lista vazia.
     * @param filial Filial
     * @return List<SetorResponseDTO>
     */
    public List<SetorResponseDTO> listar(Long filial) {
    	Filial filialPesquisada = filialService.pesquisar(filial);
    	if(filialPesquisada.getId() != null) {
			List<SetorResponseDTO> setores = setorRepository.findByFilial(filialPesquisada);
			if(!setores.isEmpty()) {
	            return setores;
	        }
		}
		return new ArrayList<SetorResponseDTO>() ;
    }
}