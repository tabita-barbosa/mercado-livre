package br.com.itau.ml.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.itau.ml.compra.Compra;
import br.com.itau.ml.usuarios.cadastro.Perfil;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String login;
	
	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String senha;
	
	@NotNull
	@Column(nullable = false)
	private LocalDateTime cadastroEm = LocalDateTime.now();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Compra> compras = new ArrayList<Compra>();
	
	public Usuario() {}

	public Usuario(@NotBlank String login, @NotNull @NotBlank String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getCadastroEm() {
		return cadastroEm;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return this.perfis;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@Override
	public String getUsername() {
		return this.login;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
















