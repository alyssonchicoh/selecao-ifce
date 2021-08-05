package br.com.ifce.selecao.enumeration;

import lombok.Getter;

public enum TipoItemEnum {

	COMIDA("COMIDA"),
	BEBIDA("BEBIDA"),
	SOBREMESA("SOBREMESA");
	
	@Getter
	private String descricao;
	
	private TipoItemEnum(String descricao) {
		this.descricao = descricao;
	}
}
