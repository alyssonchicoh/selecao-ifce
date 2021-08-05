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
import br.com.ifce.selecao.dto.HistoricoDTO;

import br.com.ifce.selecao.modelo.Historico;
import br.com.ifce.selecao.modelo.Pedido;
import br.com.ifce.selecao.modelo.Usuario;

@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = {
		"classpath:/sql/limpar_base.sql",
		"classpath:/sql/insert_usuario.sql",
		"classpath:/sql/insert_item.sql",
		"classpath:/sql/insert_pedido.sql",
		"classpath:/sql/insert_historico.sql",
		}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class HistoricoServiceTest extends SelecaoApplicationTests{
	
	@Autowired
	private HistoricoService historicoService;

	@Test
	@Order(1)
	public void deveSalvarHistorico() {
		Pedido pedido = new Pedido();
		pedido.setId(1l);
		
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		
		Historico historico = new Historico();
		historico.setDataHistorico(new Date());
		historico.setPedido(pedido);
		historico.setUsuario(usuario);
		
		this.historicoService.salvar(historico);
		
		Page<HistoricoDTO> dto = historicoService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(2, dto.getContent().size());
	}
	
	@Test
	@Order(2)
	public void deveListarItens() {
		Page<HistoricoDTO> dto = historicoService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(1, dto.getContent().size());
	}
	
	@Test
	@Order(3)
	public void deveAtualizarItem() {
		Page<HistoricoDTO> dto = historicoService.listarTodos(PageRequest.of(0, 10));
		
		HistoricoDTO historico = dto.getContent().get(0);
		historico.setUsuarioId(2l);
		
		this.historicoService.salvar(historico.fromDTO());
		
		dto = historicoService.listarTodos(PageRequest.of(0, 10));

		assertEquals(2l, dto.getContent().get(0).getUsuarioId());
		
		
	}
	
	@Test
	@Order(4)
	public void deveDeletarItem() {
		Page<HistoricoDTO> dto = historicoService.listarTodos(PageRequest.of(0, 10));
		
		
		this.historicoService.deletar(dto.getContent().get(0).fromDTO());
		
		dto = historicoService.listarTodos(PageRequest.of(0, 10));

		assertEquals(0, dto.getContent().size());
		
		
	}
}
