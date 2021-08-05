package br.com.ifce.selecao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.ifce.selecao.SelecaoApplicationTests;
import br.com.ifce.selecao.dto.ItemDTO;
import br.com.ifce.selecao.enumeration.TipoItemEnum;
import br.com.ifce.selecao.modelo.Item;

@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = {
		"classpath:/sql/limpar_base.sql",
		"classpath:/sql/insert_item.sql"
		}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemServiceTest extends SelecaoApplicationTests{

	@Autowired
	private ItemService itemService;
	
	@Test
	@Order(1)
	public void deveSalvarItem() {
		Item item = new Item();
		item.setNome("COCA COLA");
		item.setDescricao("COLCA COLA 1L");
		item.setTipo(TipoItemEnum.BEBIDA);
		item.setValor(5.0);
		item.setUrlImagem("www.google.com.br");
		itemService.salvar(item);
		
		Page<ItemDTO> dto = itemService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(2, dto.getContent().size());
	}
	
	@Test
	@Order(2)
	public void deveListarItens() {
		Page<ItemDTO> dto = itemService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(1, dto.getContent().size());
	}
	
	@Test
	@Order(3)
	public void deveAtualizarItem() {
		Page<ItemDTO> dto = itemService.listarTodos(PageRequest.of(0, 10));
		
		ItemDTO item = dto.getContent().get(0);
		item.setNome("NOME EDITADO");
		
		this.itemService.salvar(item.fromDTO());
		
		dto = itemService.listarTodos(PageRequest.of(0, 10));

		assertEquals("NOME EDITADO", dto.getContent().get(0).getNome());
		
		
	}
	
	@Test
	@Order(4)
	public void deveDeletarItem() {
		Page<ItemDTO> dto = itemService.listarTodos(PageRequest.of(0, 10));
		
		
		this.itemService.deletar(dto.getContent().get(0).fromDTO());
		
		dto = itemService.listarTodos(PageRequest.of(0, 10));

		assertEquals(0, dto.getContent().size());
		
		
	}
}
