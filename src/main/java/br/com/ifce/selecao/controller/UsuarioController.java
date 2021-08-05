package br.com.ifce.selecao.controller;


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

import br.com.ifce.selecao.dto.UsuarioDTO;
import br.com.ifce.selecao.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/salvar")
	public UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO) {
		return new UsuarioDTO().toDTO(this.usuarioService.salvar(usuarioDTO.fromDTO()));
	}
	
	@GetMapping("/listar")
	public Page<UsuarioDTO> listarTodos(@RequestParam(required = false, defaultValue = "0") int page, 
			   							@RequestParam(required = false, defaultValue = "10") int size){
		
		
		return this.usuarioService.listarTodos(PageRequest.of(page, size));
	}
	
	@PutMapping("/atualizar")
	public UsuarioDTO atualizar(@RequestBody UsuarioDTO usuarioDTO) {
		return new UsuarioDTO().toDTO(this.usuarioService.salvar(usuarioDTO.fromDTO()));
	}
	
	@DeleteMapping("/deletar")
	public void deletar(@RequestBody UsuarioDTO usuarioDTO) {
		this.usuarioService.deletar(usuarioDTO.fromDTO());
	}
}
