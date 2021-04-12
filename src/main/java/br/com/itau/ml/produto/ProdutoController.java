package br.com.itau.ml.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.ml.categoria.CategoriaRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private Uploader uploader;
	
	@Autowired 
	private Emails emails;
	
	@PostMapping
	public void cadastroProduto(@RequestBody @Valid ProdutoForm produtoForm, @AuthenticationsPrincipal Usuario usuario) {
		Produto produto = produtoForm.converter(categoriaReposiroty, usuario);
		produtoRepository.save(produto);
	}
	
	
	
}
