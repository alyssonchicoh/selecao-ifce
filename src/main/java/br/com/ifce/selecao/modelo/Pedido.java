package br.com.ifce.selecao.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "tb_pedido")
@Entity
@SequenceGenerator(name = "SEQUENCE", initialValue = 1, allocationSize = 1)
public class Pedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido_id")
	private Long id;
	
	@Column(name = "ped_data_pedido")
	private Date dataPedido;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cod_fk")
	private Usuario usuario;
	
	@Column(name = "ped_valor_total")
	private Double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "item_cod_fk")
	private Item item;


	
}
