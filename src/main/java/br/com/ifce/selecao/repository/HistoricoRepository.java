package br.com.ifce.selecao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ifce.selecao.modelo.Historico;

@Repository
public interface HistoricoRepository extends CrudRepository<Historico, Long> {
	
	public Page<Historico> findAll(Pageable pageable);

}