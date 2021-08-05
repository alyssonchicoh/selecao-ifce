package br.com.ifce.selecao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ifce.selecao.modelo.Historico;
import br.com.ifce.selecao.modelo.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	public Page<Item> findAll(Pageable pageable);


}
