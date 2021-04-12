package br.com.itau.ml.produto.pergunta;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.ml.produto.Produto;
import br.com.itau.ml.produto.ProdutoRepository;
import br.com.itau.ml.usuarios.Usuario;

public class PerguntaForm {

	@NotBlank(message = "O título é obrigatório.")
	private String titulo;
	
	@NotBlank(message = "O nome do produto é obrigatório.")
	private String nomeProduto;

	public String getTitulo() {
		return titulo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public Pergunta converter(ProdutoRepository produtoRepository, Usuario interessado) {
		Optional<Produto> produto = produtoRepository.findByNome(nomeProduto);
		if(produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema.");
		}
		return new Pergunta(titulo, interessado, produto.get());
	}
	
	
}
