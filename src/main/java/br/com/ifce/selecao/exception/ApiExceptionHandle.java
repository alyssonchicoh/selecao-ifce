package br.com.ifce.selecao.exception;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ifce.selecao.configure.MessageHelper;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandle extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageHelper messageHelper;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e){
        String messagemUsuario = e.getReason();
        String messageDesenvolvedor = e.getLocalizedMessage();
        HttpStatus status = e.getStatus();

        Erro erro = new Erro(messagemUsuario, messageDesenvolvedor, status.value());

        return new ResponseEntity(erro, status);
    }

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		Erro error = new Erro(ex.getMessage(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request){
        String messagemUsuario = messageHelper.getMensagem("erro.servidor");
    	String messageDesenvolvedor = ex.getMessage();
        Erro erro = new Erro(messagemUsuario, messageDesenvolvedor, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String messagemUsuario = messageHelper.getMensagem("erro.servidor");
    	String messageDesenvolvedor = ex.getMessage();
        Erro erro = new Erro(messagemUsuario, messageDesenvolvedor, status.value());
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = new ArrayList<>();
		String mensagemUsuario = String.format("Parâmetro %s é obrigatório.", ex.getParameterName());
		erros.add(new Erro(mensagemUsuario, ex.getMessage(), status.value()));
		return handleExceptionInternal(ex, erros, headers, status, request);
	}

	@ExceptionHandler(ParametroObrigatorioException.class)
	public ResponseEntity<Object> handleParametroInvalidoException(ParametroObrigatorioException ex, WebRequest request) {
		String mensagem = messageHelper.getMensagem(ex.getParametro());
		Erro error = new Erro(mensagem, mensagem, HttpStatus.BAD_REQUEST.value());
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String messagemUsuario = "";
        if(ex instanceof HttpMessageNotReadableException) {
            messagemUsuario = "Corpo da requisicao nao informado";

        }else {
            messagemUsuario = messageHelper.getMensagem("erro.servidor");
        }

        String messageDesenvolvedor = ex.getMessage();
        Erro erro = new Erro(messagemUsuario, messageDesenvolvedor, status.value());
        return super.handleExceptionInternal(ex, erro, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        StringBuilder messagemUsuario = new StringBuilder();
        messagemUsuario.append("Campos Obrigatorios nao informados");

        for (String detail : details) {
            messagemUsuario.append(detail);
        }
        String messageDesenvolvedor = ex.getMessage();
        Erro erro = new Erro(messagemUsuario.toString(), messageDesenvolvedor, status.value());

        return new ResponseEntity(erro, HttpStatus.BAD_REQUEST);
    }

	@Getter
	public static class Erro {

		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		private Integer statusHttp;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime dateTime;

		@JsonCreator
		public Erro(@JsonProperty("mensagemUsuario") String mensagemUsuario) {
			this(mensagemUsuario, null, null);
		}

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor, Integer statusHttp) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
			this.statusHttp = statusHttp;
			this.dateTime = LocalDateTime.now();
		}

	}
}
