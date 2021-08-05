package br.com.ifce.selecao.dto;

public interface BaseDTO<T> {

	public Object toDTO(T t);
	
	public T fromDTO();
	
}
