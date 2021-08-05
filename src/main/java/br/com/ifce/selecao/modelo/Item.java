package br.com.ifce.selecao.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ifce.selecao.enumeration.TipoItemEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_item")
@Entity
@SequenceGenerator(name = "SEQUENCE", initialValue = 1, allocationSize = 1)
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_id")
	private Long id;
	
	@Column(name = "ite_nome")
	private String nome;
	
	@Column(name = "ite_descricao")
	private String descricao;
	
	@Column(name = "ite_url_imagem")
	private String urlImagem;
	
	@Column(name = "ite_valor")
	private Double valor;
	
	@Column(name = "ite_is_disponivel")
	private Boolean disponivel;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ite_tipo")
	private TipoItemEnum tipo;
	
	

}
