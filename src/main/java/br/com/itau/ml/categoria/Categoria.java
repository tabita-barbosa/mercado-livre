package br.com.itau.ml.categoria;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	public Categoria () {}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
	
	public void setCategoriaMae(Optional<Categoria> categoriaMae) {
		this.categoriaMae= categoriaMae.get();
	}
	
}
