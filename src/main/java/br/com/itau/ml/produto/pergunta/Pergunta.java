package br.com.itau.ml.produto.pergunta;

import java.time.LocalDateTime;

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
@Table(name = "tb_perguntas")
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	private LocalDateTime criadoEm = LocalDateTime.now();
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	public Pergunta() {
		
	}

	public Pergunta(String titulo, Usuario usuario, Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
	// @Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.titulo);
	}

}
