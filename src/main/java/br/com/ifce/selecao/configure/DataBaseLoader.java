package br.com.ifce.selecao.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.ifce.selecao.modelo.Usuario;
import br.com.ifce.selecao.service.UsuarioService;

@Component
public class DataBaseLoader implements CommandLineRunner {

	private final UsuarioService usuarioService;

	@Autowired
	public DataBaseLoader(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public void run(String... strings) throws Exception {
		
		if(usuarioService.buscarPorLogin("ifce") == null){
			Usuario usuarioIFCE = new Usuario();
			usuarioIFCE.setNome("ifce");
			usuarioIFCE.setSenha("123456");
			usuarioIFCE.setRoles("ROLE_ADMIN");
			
			usuarioService.salvar(usuarioIFCE);
		}
		
		if(usuarioService.buscarPorLogin("alysson") == null){
			Usuario usuarioAlysson = new Usuario();
			usuarioAlysson.setNome("alysson");
			usuarioAlysson.setSenha("123");
			usuarioAlysson.setRoles("ROLE_ADMIN");
			
			usuarioService.salvar(usuarioAlysson);
		}
		
		
		
	}
}