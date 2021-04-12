package br.com.itau.ml.usuarios.cadastro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name =  "tb_perfis")
public class Perfil implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String getAuthority() {
		return nome;
	}
	
}
