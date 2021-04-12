package br.com.itau.ml.usuarios;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.itau.ml.configuracao.validacao.ValorUnico;

public class UsuarioForm {
	
	@NotNull(message = "O login não pode ser nulo.")
	@NotBlank(message = "O login não pode ser vazio.")
	@Email(message = "O login deve ser através do email.")
	@ValorUnico(domainClass = Usuario.class, fieldName = "login", message="Só pode existir um usuário por email.")
	private String login;
	
	@NotNull(message = "A senha não pode ser nula.")
	@NotBlank(message = "A senha não pode ser vazia.")
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario converter() {
		String hashSenha = new BCryptPasswordEncoder().encode(senha);
		return new Usuario(login,hashSenha);
	}
}
