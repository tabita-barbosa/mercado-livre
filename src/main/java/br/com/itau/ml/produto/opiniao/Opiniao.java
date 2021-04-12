package br.com.itau.ml.produto.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.itau.ml.produto.Produto;
import br.com.itau.ml.usuarios.Usuario;

@Entity
@Table(name = "tb_opinioes")
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private Long notas;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@NotBlank
	@Column(nullable = false, length = 500)
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;

	public Opiniao(@NotNull Long notas, @NotBlank String titulo, @NotBlank String descricao, Usuario usuario, Produto produto) {
		this.notas = notas;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public Long getNotas() {
		return notas;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

}
