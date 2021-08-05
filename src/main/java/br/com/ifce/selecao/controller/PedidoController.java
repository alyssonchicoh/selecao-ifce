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
import br.com.ifce.selecao.dto.PedidoDTO;
import br.com.ifce.selecao.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/salvar")
	public PedidoDTO salvar(@RequestBody PedidoDTO pedidoDTO) {
		return new PedidoDTO().toDTO(this.pedidoService.salvar(pedidoDTO.fromDTO()));
	}
	
	@GetMapping("/listar")
	public Page<PedidoDTO>  listarTodos(@RequestParam(required = false, defaultValue = "0") int page, 
			 						   @RequestParam(required = false, defaultValue = "10") int size){
		
		return this.pedidoService.listarTodos(PageRequest.of(page, size));
	}
	
	@PutMapping("/atualizar")
	public PedidoDTO atualizar(@RequestBody PedidoDTO pedidoDTO) {
		return new PedidoDTO().toDTO(this.pedidoService.salvar(pedidoDTO.fromDTO()));
	}
	
	@DeleteMapping("/deletar")
	public void deletar(@RequestBody PedidoDTO pedidoDTO) {
		 this.pedidoService.deletar(pedidoDTO.fromDTO());
	}
}
