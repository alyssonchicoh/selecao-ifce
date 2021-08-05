package br.com.ifce.selecao.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_historico")
@Entity
@SequenceGenerator(name = "SEQUENCE", initialValue = 1, allocationSize = 1)
public class Historico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cod_fk")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "pedido_cod_fk")
	private Pedido pedido;
	
	@Column(name = "his_data_historico")
	private Date dataHistorico;
}
