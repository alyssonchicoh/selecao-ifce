package br.com.ifce.selecao.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifce.selecao.dto.HistoricoDTO;
import br.com.ifce.selecao.modelo.Historico;
import br.com.ifce.selecao.repository.HistoricoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository historicoRepository;
	
	public Historico salvar(Historico historico) {
		return this.historicoRepository.save(historico);
	}
	
	public Page<HistoricoDTO> listarTodos(Pageable pageable){
		Page<HistoricoDTO> page = historicoRepository.findAll(pageable)
				.map(obejto -> {
					HistoricoDTO dto = new HistoricoDTO().toDTO(obejto);
					
					return dto;
				});
		
		return page;
	}
	
	public void deletar(Historico historico) {
		this.historicoRepository.delete(historico);
	}
	
	public Historico buscarPorID(Long id) {
		Optional<Historico> historico = this.historicoRepository.findById(id);
		
		if(historico.isPresent()) {
			return this.historicoRepository.findById(id).get();
		}else {
			return null;
		}
	}
}
