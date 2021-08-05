package br.com.ifce.selecao.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifce.selecao.dto.HistoricoDTO;
import br.com.ifce.selecao.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoService historicoService;
	
	@PostMapping("/salvar")
	public HistoricoDTO salvar(@RequestBody HistoricoDTO historicoDTO) {
		return new HistoricoDTO().toDTO(this.historicoService.salvar(historicoDTO.fromDTO()));
	}
	
	@GetMapping("/listar")
	public Page<HistoricoDTO> listarTodos(@RequestParam(required = false, defaultValue = "0") int page, 
										  @RequestParam(required = false, defaultValue = "10") int size){
		
		
		return historicoService.listarTodos(PageRequest.of(page, size));
	}
	
	@PutMapping("/atualizar")
	public HistoricoDTO atualizar(@RequestBody HistoricoDTO historicoDTO) {
		return new HistoricoDTO().toDTO(this.historicoService.salvar(historicoDTO.fromDTO()));
	}
	
	@DeleteMapping("/deletar")
	public void deletar(@RequestBody HistoricoDTO historicoDTO) {
		this.historicoService.deletar(historicoDTO.fromDTO());
	}

	
}
