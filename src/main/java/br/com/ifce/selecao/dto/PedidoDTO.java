package br.com.ifce.selecao.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifce.selecao.modelo.Item;
import br.com.ifce.selecao.modelo.Pedido;
import br.com.ifce.selecao.modelo.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO implements BaseDTO<Pedido>{
	
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")	
	private String dataPedido;
	
	@NotNull(message = "Campo USUARIO ID obrigatorio")
	private Long idUsuario;
	
	private Double valorTotal;
	
	@NotNull(message = "Campo ITEM ID obrigatorio")
	private Long idItem;
	
	@Override
	public PedidoDTO toDTO(Pedido t) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		this.id = t.getId();
		this.dataPedido = dataFormat.format(t.getDataPedido());
		this.valorTotal = t.getValorTotal();
		this.idItem = t.getItem().getId();
		this.idUsuario = t.getUsuario().getId();
		
		return this;
	}

	@Override
	public Pedido fromDTO() {
		try {
			Usuario usuario = new Usuario();
			usuario.setId(this.idUsuario);
			
			Item item = new Item();
			item.setId(this.idItem);
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Pedido pedido = new Pedido();
			pedido.setId(this.id);
			pedido.setDataPedido(dataFormat.parse(this.dataPedido));
			pedido.setUsuario(usuario);
			pedido.setValorTotal(this.valorTotal);
			pedido.setItem(item);
			 
			return pedido;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
}
