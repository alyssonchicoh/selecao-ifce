package br.com.ifce.selecao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.ifce.selecao.modelo.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	public Usuario findByNome(String nome);
	
	public Page<Usuario> findAll(Pageable pageable);

}
