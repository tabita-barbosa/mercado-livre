package br.com.itau.ml.produto.opiniao;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.ml.produto.Produto;
import br.com.itau.ml.produto.ProdutoRepository;
import br.com.itau.ml.usuarios.Usuario;

public class OpiniaoForm {

	@NotNull(message = "A nota deve ser entre 1 e 5.")
	@Min(1)
	@Max(5)
	private Long notas;
	
	@NotBlank(message = "O título é obrigatório.")
	private String titulo;
	
	@NotBlank(message = "A descrição é obrigatória.")
	@Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
	private String descricao;

	@NotBlank(message = "O nome do produto é obrigatório.")
	private String nomeProduto;

	public Long getNotas() {
		return notas;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public Opiniao converter(ProdutoRepository produtoRepository, Usuario usuario) {
		Optional<Produto> produto = produtoRepository.findByNome(nomeProduto);
		
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema");
		}
		return new Opiniao(notas, titulo, descricao, usuario, produto.get());
	}
	
	
}
