package br.com.ifce.selecao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.ifce.selecao.dto.UsuarioDTO;
import br.com.ifce.selecao.modelo.Usuario;
import br.com.ifce.selecao.repository.UsuarioRepository;

@Service
public class UsuarioService {


	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvar(Usuario Usuario) {
		return this.usuarioRepository.save(Usuario);
	}
	
	public Page<UsuarioDTO> listarTodos(Pageable pageable){
		Page<UsuarioDTO> page = usuarioRepository.findAll(pageable)
				.map(obejto -> {
					UsuarioDTO dto = new UsuarioDTO().toDTO(obejto);
					
					return dto;
				});
		
		return page;
	}
	
	public void deletar(Usuario Usuario) {
		this.usuarioRepository.delete(Usuario);
	}
	
	public Usuario buscarPorID(Long id) {
		Optional<Usuario> Usuario = this.usuarioRepository.findById(id);
		
		if(Usuario.isPresent()) {
			return this.usuarioRepository.findById(id).get();
		}else {
			return null;
		}
	}
	
	public Usuario buscarPorLogin(String login) {
		return this.usuarioRepository.findByNome(login);
	}
}
