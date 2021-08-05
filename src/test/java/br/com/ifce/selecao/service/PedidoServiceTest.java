package br.com.ifce.selecao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.ifce.selecao.SelecaoApplicationTests;
import br.com.ifce.selecao.dto.PedidoDTO;

import br.com.ifce.selecao.modelo.Item;
import br.com.ifce.selecao.modelo.Pedido;
import br.com.ifce.selecao.modelo.Usuario;

@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = {
		"classpath:/sql/limpar_base.sql",
		"classpath:/sql/insert_usuario.sql",
		"classpath:/sql/insert_item.sql",
		"classpath:/sql/insert_pedido.sql",
		
		}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PedidoServiceTest  extends SelecaoApplicationTests{
	
	@Autowired
	private PedidoService pedidoService;
	
	@Test
	@Order(1)
	public void deveSalvarItem() {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		Item item = new Item();
		item.setId(1l);
		
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		pedido.setItem(item);
		pedido.setValorTotal(50.0);
		pedido.setDataPedido(new Date());
		
		pedidoService.salvar(pedido);
		
		Page<PedidoDTO> dto = pedidoService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(2, dto.getContent().size());
	}
	
	@Test
	@Order(2)
	public void deveListarItens() {
		Page<PedidoDTO> dto = pedidoService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(1, dto.getContent().size());
	}
	
	@Test
	@Order(3)
	public void deveAtualizarItem() {
		Page<PedidoDTO> dto = pedidoService.listarTodos(PageRequest.of(0, 10));
		
		PedidoDTO item = dto.getContent().get(0);
		item.setValorTotal(40.0);
		
		this.pedidoService.salvar(item.fromDTO());
		
		dto = pedidoService.listarTodos(PageRequest.of(0, 10));

		assertEquals(40.0, dto.getContent().get(0).getValorTotal());
		
		
	}
	
	@Test
	@Order(4)
	public void deveDeletarItem() {
		Page<PedidoDTO> dto = pedidoService.listarTodos(PageRequest.of(0, 10));
		
		
		this.pedidoService.deletar(dto.getContent().get(0).fromDTO());
		
		dto = pedidoService.listarTodos(PageRequest.of(0, 10));

		assertEquals(0, dto.getContent().size());
		
		
	}
}
