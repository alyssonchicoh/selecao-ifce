package br.com.ifce.selecao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ifce.selecao.modelo.Item;
import br.com.ifce.selecao.modelo.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{

	public Page<Pedido> findAll(Pageable pageable);

}
