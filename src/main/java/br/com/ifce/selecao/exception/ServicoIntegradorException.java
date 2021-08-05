package br.com.ifce.selecao.exception;


import lombok.Getter;

@Getter
public class ServicoIntegradorException extends RuntimeException{

    private static final long serialVersionUID = 5647152466432292032L;

    private String erro;

    public ServicoIntegradorException(String erro) {
        super(erro);
        this.erro = erro;
    }
}
