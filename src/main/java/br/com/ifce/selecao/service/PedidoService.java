package br.com.ifce.selecao.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ifce.selecao.dto.PedidoDTO;
import br.com.ifce.selecao.modelo.Pedido;
import br.com.ifce.selecao.repository.PedidoRepository;

@Service
public class PedidoService {


	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido salvar(Pedido Pedido) {
		return this.pedidoRepository.save(Pedido);
	}
	
	public Page<PedidoDTO>listarTodos(Pageable pageable){
		Page<PedidoDTO> page = pedidoRepository.findAll(pageable)
				.map(obejto -> {
					PedidoDTO dto = new PedidoDTO().toDTO(obejto);
					
					return dto;
				});
		
		return page;
	}
	
	public void deletar(Pedido Pedido) {
		this.pedidoRepository.delete(Pedido);
	}
	
	public Pedido buscarPorID(Long id) {
		Optional<Pedido> Pedido = this.pedidoRepository.findById(id);
		
		if(Pedido.isPresent()) {
			return this.pedidoRepository.findById(id).get();
		}else {
			return null;
		}
	}
}
