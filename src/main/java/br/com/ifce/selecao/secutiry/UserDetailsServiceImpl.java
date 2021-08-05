package br.com.ifce.selecao.secutiry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.ifce.selecao.modelo.Usuario;
import br.com.ifce.selecao.service.UsuarioService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioService usuarioService;

	@Autowired
	public UserDetailsServiceImpl(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Usuario user = this.usuarioService.buscarPorLogin(name);
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles());
		return new User(user.getNome() , user.getSenha(), Arrays.asList(authority));
	
	}

}