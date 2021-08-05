package br.com.ifce.selecao.dto;

import br.com.ifce.selecao.enumeration.TipoItemEnum;
import br.com.ifce.selecao.modelo.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO implements BaseDTO<Item> {
	
	private Long id;
	private String nome;
	private String urlImagem;
	private Double valor;
	private Boolean disponivel;
	private String tipo;
	
	@Override
	public ItemDTO toDTO(Item t) {
		this.id = t.getId();
		this.nome = t.getNome();
		this.urlImagem = t.getUrlImagem();
		this.valor = t.getValor();
		this.disponivel = t.getDisponivel();
		this.tipo = t.getTipo().getDescricao();
		return this;
	}

	@Override
	public Item fromDTO() {
		Item item = new Item();
		item.setId(this.id);
		item.setNome(this.nome);
		item.setUrlImagem(this.urlImagem);
		item.setValor(this.valor);
		item.setDisponivel(this.disponivel);
		if(tipo != null) {
			item.setTipo(TipoItemEnum.valueOf(tipo));
		}
		return item;
	}

	
}
