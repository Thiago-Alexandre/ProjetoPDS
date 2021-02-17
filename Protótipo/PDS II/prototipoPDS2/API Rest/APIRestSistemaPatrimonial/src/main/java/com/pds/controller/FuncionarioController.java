package com.pds.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds.dto.FuncionarioForSaveDTO;
import com.pds.dto.FuncionarioForUpdateDTO;
import com.pds.dto.FuncionarioResponseDTO;
import com.pds.model.Funcionario;
import com.pds.service.FuncionarioService;

@RequestMapping("/SistemaPatrimonial")
@RestController
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	@Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
	
	@PostMapping(value="/funcionario")
    public ResponseEntity<FuncionarioResponseDTO> salvar(@RequestBody @Valid FuncionarioForSaveDTO dto) {
        Funcionario funcionario = funcionarioService.salvar(dto.generationObject(), dto.getOrganizacao());
        return new ResponseEntity<>(FuncionarioResponseDTO.generationDTO(funcionario), HttpStatus.CREATED);
    }
	
	@PutMapping(value="/funcionario")
	public ResponseEntity<FuncionarioResponseDTO> alterar(@RequestBody @Valid FuncionarioForUpdateDTO dto) {
		Funcionario funcionario = funcionarioService.alterar(dto.generationObject());
		if(funcionario.getId() != null) {
        	return new ResponseEntity<>(FuncionarioResponseDTO.generationDTO(funcionario), HttpStatus.OK);
        }
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/funcionario/{id}")
	public ResponseEntity<FuncionarioResponseDTO> pesquisar(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.pesquisar(id);
        if(funcionario.getId() != null) {
        	return new ResponseEntity<>(FuncionarioResponseDTO.generationDTO(funcionario), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/funcionarios/{id_organizacao}")
	public ResponseEntity<List<FuncionarioResponseDTO>> listar(@PathVariable Long id_organizacao) {
		List<FuncionarioResponseDTO> funcionarios = funcionarioService.listar(id_organizacao);
		if(funcionarios.isEmpty()) {
            return new ResponseEntity<>(funcionarios, HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(funcionarios, HttpStatus.OK);
	}
}