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

import com.pds.dto.FilialForSaveDTO;
import com.pds.dto.FilialForUpdateDTO;
import com.pds.dto.FilialResponseDTO;
import com.pds.model.Filial;
import com.pds.service.FilialService;

@RequestMapping("/SistemaPatrimonial")
@RestController
public class FilialController {

	private final FilialService filialService;

	@Autowired		/** Injeção de dependências*/
    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }
	
	@PostMapping(value="/filial")
    public ResponseEntity<FilialResponseDTO> salvar(@RequestBody @Valid FilialForSaveDTO dto) {
        Filial filial = filialService.salvar(dto.generationObject(), dto.getOrganizacao());
        return new ResponseEntity<>(FilialResponseDTO.generationDTO(filial), HttpStatus.CREATED);
    }
	
	@PutMapping(value="/filial")
	public ResponseEntity<FilialResponseDTO> alterar(@RequestBody @Valid FilialForUpdateDTO dto) {
		Filial filial = filialService.alterar(dto.generationObject());
		if(filial.getId() != null) {
        	return new ResponseEntity<>(FilialResponseDTO.generationDTO(filial), HttpStatus.OK);
        }
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/filial/{id}")
	public ResponseEntity<FilialResponseDTO> pesquisar(@PathVariable Long id) {
		Filial filial = filialService.pesquisar(id);
        if(filial.getId() != null) {
        	return new ResponseEntity<>(FilialResponseDTO.generationDTO(filial), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/filiais/{id_organizacao}")
	public ResponseEntity<List<FilialResponseDTO>> listar(@PathVariable Long id_organizacao) {
		List<FilialResponseDTO> filiais = filialService.listar(id_organizacao);
		if(filiais.isEmpty()) {
			return new ResponseEntity<>(filiais, HttpStatus.NOT_FOUND);
	    }
		return new ResponseEntity<>(filiais, HttpStatus.OK);
	}
}