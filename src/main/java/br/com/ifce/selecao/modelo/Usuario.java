package br.com.ifce.selecao.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_usuario")
@Entity
@SequenceGenerator(name = "SEQUENCE", initialValue = 1, allocationSize = 1)
public class Usuario implements Serializable{
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_id")
	private Long id;
	
	@Column(name = "usu_cpf")
	private String cpf;
	
	@Column(name = "usu_nome")
	private String nome;
	
	@Column(name = "usu_sobrenome")
	private String sobrenome;
	
	@Column(name = "usu_senha")
	private String senha;
	
	@Column(name = "usu_roles")
	private String roles;
	
	public void setSenha(String senha) {
		this.senha = PASSWORD_ENCODER.encode(senha);
	}
	
	
}
