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

import br.com.ifce.selecao.dto.ItemDTO;
import br.com.ifce.selecao.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PostMapping("/salvar")
	public ItemDTO salvar(@RequestBody ItemDTO itemDTO) {
		return new ItemDTO().toDTO(this.itemService.salvar(itemDTO.fromDTO()));
	}
	
	@GetMapping("/listar")
	public Page<ItemDTO> listarTodos(@RequestParam(required = false, defaultValue = "0") int page, 
									 @RequestParam(required = false, defaultValue = "10") int size){
		
		return this.itemService.listarTodos(PageRequest.of(page, size));
	}
	
	@PutMapping("/atualizar")
	public ItemDTO atualizar(@RequestBody ItemDTO itemDTO) {
		return new ItemDTO().toDTO(this.itemService.salvar(itemDTO.fromDTO()));
	}
	
	@DeleteMapping("/deletar")
	public void deletar(@RequestBody ItemDTO itemDTO) {
		this.itemService.deletar(itemDTO.fromDTO());
	}
}
