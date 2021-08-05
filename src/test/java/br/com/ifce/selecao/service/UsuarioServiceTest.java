package br.com.ifce.selecao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
import br.com.ifce.selecao.dto.UsuarioDTO;
import br.com.ifce.selecao.modelo.Usuario;

@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = {
		"classpath:/sql/limpar_base.sql",
		"classpath:/sql/insert_usuario.sql",
		}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UsuarioServiceTest extends SelecaoApplicationTests{

	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@Order(1)
	public void deveSalvarItem() {
		Usuario usuario = new Usuario();
		usuario.setCpf("8793737383");  
		usuario.setNome("Alysson");
		usuario.setRoles("ROLE_ADMIN");
		usuario.setSenha("123456");
		usuario.setSobrenome("teste");
		
		
		usuarioService.salvar(usuario);
		
		Page<UsuarioDTO> dto = usuarioService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(3, dto.getContent().size());
	}
	
	@Test
	@Order(2)
	public void deveListarItens() {
		Page<UsuarioDTO> dto = usuarioService.listarTodos(PageRequest.of(0, 10));
		
		assertEquals(2, dto.getContent().size());
	}
	
	
	
	
	@Test
	@Order(3)
	public void deveDeletarItem() {
		Page<UsuarioDTO> dto = usuarioService.listarTodos(PageRequest.of(0, 10));
		
		
		this.usuarioService.deletar(dto.getContent().get(0).fromDTO());
		
		dto = usuarioService.listarTodos(PageRequest.of(0, 10));

		assertEquals(1, dto.getContent().size());
		
		
	}
	
	@Test
	@Order(4)
	public void deveAtualizarItem() {
		Page<UsuarioDTO> dto = usuarioService.listarTodos(PageRequest.of(0, 10));
		
		this.usuarioService.deletar(dto.getContent().get(0).fromDTO());
		
		dto = usuarioService.listarTodos(PageRequest.of(0, 10));
		
		 dto = usuarioService.listarTodos(PageRequest.of(0, 10));
		
		UsuarioDTO item = dto.getContent().get(0);
		item.setNome("NOME MODIFICADO");
		
		this.usuarioService.salvar(item.fromDTO());
		
		dto = usuarioService.listarTodos(PageRequest.of(0, 10));

		assertEquals("NOME MODIFICADO", dto.getContent().get(0).getNome());
		
	}
}
