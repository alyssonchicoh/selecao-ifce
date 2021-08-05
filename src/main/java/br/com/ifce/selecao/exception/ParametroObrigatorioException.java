package br.com.ifce.selecao.exception;


import lombok.Getter;

@Getter
public class ParametroObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String parametro;

	public ParametroObrigatorioException(String parametro) {
		this(parametro, "Parâmetro obrigatório");
	}

	public ParametroObrigatorioException(String parametro, String message) {
		super(message);
		this.parametro = parametro;
	}

}
