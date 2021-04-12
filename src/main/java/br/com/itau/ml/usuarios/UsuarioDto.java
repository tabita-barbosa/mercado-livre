package br.com.itau.ml.usuarios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {
	
	private Long id;
	private String login;
	private LocalDateTime cadastroEm;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.cadastroEm = usuario.getCadastroEm();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public LocalDateTime getCadastroEm() {
		return cadastroEm;
	}
	
	public static List<UsuarioDto> converter(List<Usuario> usuarios){
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
}
