package br.com.itau.ml.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.itau.ml.categoria.Categoria;
import br.com.itau.ml.categoria.CategoriaRepository;
import br.com.itau.ml.produto.caracteristica.Caracteristica;
import br.com.itau.ml.usuarios.Usuario;

public class ProdutoForm {
	
	@NotBlank(message = "O nome é obrigatório.")
	private String nome;
	
	@NotNull(message = "O valor é obrigatório.")
	@Positive(message = "O valor deve ser maior que zero.")
	private BigDecimal valor;
	
	@NotNull(message = "A quantidade é obrigatória.")
	@PositiveOrZero(message = "A quantidade deve ser igual ou maior que zero.")
	private Long quantidade;
	
	@Size(min = 3, message = "O produto possui deve ter pelo menos três características.")
	@Valid
	private List<Caracteristica> caracteristicas;
	
	@NotBlank(message = "A descrição é obrigatória.")
	@Size(max = 1000, message = "A descrição tem máximo de 1000 caracteres.")
	private String descricao;
	
	@NotNull(message = "A categoria é obrigatória.")
	private String nomeCategoria;

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}
	
	public Produto converter(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(nomeCategoria);
		
		if(categoria.isEmpty()) {
			throw new IllegalStateException("A categoria é obrigatória e deve estar cadastrada");
		}
		return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria.get(), usuario);
	}
	
}
