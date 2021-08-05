package br.com.ifce.selecao.dto;

import br.com.ifce.selecao.modelo.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO implements BaseDTO<Usuario>{
	
	private Long id;
	private String cpf;
	private String nome;
	private String sobrenome;
	private String senha;
	private String roles;

	@Override
	public UsuarioDTO toDTO(Usuario t) {
		this.id = t.getId();
		this.cpf = t.getCpf();
		this.nome = t.getNome();
		this.sobrenome = t.getSobrenome();
		this.senha = t.getSenha();
		this.roles = t.getRoles();
		return this;
	}
	
	
	@Override
	public Usuario fromDTO() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setCpf(this.cpf);
		usuario.setNome(this.nome);
		usuario.setSobrenome(this.sobrenome);
		usuario.setSenha(this.senha);
		usuario.setRoles(this.roles);
		
		return usuario;
	}
	
	public String getSenha() {
		return "[SEGURANCA] IMPOSSIVEL VISUALIZAR SENHA";
	}


	
}
