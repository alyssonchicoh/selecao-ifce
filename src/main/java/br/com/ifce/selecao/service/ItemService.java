package br.com.ifce.selecao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ifce.selecao.dto.HistoricoDTO;
import br.com.ifce.selecao.dto.ItemDTO;
import br.com.ifce.selecao.modelo.Item;
import br.com.ifce.selecao.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item salvar(Item Item) {
		return this.itemRepository.save(Item);
	}
	
	public Page<ItemDTO> listarTodos(Pageable pageable){
		Page<ItemDTO> page = itemRepository.findAll(pageable)
				.map(obejto -> {
					ItemDTO dto = new ItemDTO().toDTO(obejto);
					
					return dto;
				});
		
		return page;
	}
	
	public void deletar(Item Item) {
		this.itemRepository.delete(Item);
	}
	
	public Item buscarPorID(Long id) {
		Optional<Item> Item = this.itemRepository.findById(id);
		
		if(Item.isPresent()) {
			return this.itemRepository.findById(id).get();
		}else {
			return null;
		}
	}
}
