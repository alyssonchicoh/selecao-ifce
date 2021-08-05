package br.com.ifce.selecao.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifce.selecao.modelo.Historico;
import br.com.ifce.selecao.modelo.Pedido;
import br.com.ifce.selecao.modelo.Usuario;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class HistoricoDTO implements BaseDTO<Historico> {
	
	private Long id;
	
	@NotNull(message = "Campo USUARIO ID obrigatorio")
	private Long usuarioId;
	
	@NotNull(message = "Campo PEDIDO ID obrigatorio")
	private Long pedidoId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")	
	private String dataHistorico;
	
	
	@Override
	public HistoricoDTO toDTO(Historico t) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		this.id = t.getId();
		this.usuarioId = t.getUsuario().getId();
		this.pedidoId = t.getPedido().getId();
		this.dataHistorico = dataFormat.format(t.getDataHistorico());
		return this;
	}

	@Override
	public  Historico fromDTO() {
		try {
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Pedido pedido = new Pedido();
			pedido.setId(pedidoId);
			
			Usuario usuario = new Usuario();
			usuario.setId(usuarioId);
			Historico historico = new Historico();
			historico.setId(this.id);
			
			historico.setPedido(pedido);
			historico.setUsuario(usuario);
			historico.setDataHistorico(dataFormat.parse(dataHistorico));
			return historico;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;

		}
	}

	
}
