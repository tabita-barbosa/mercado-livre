package br.com.itau.ml.produto;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.ml.categoria.CategoriaRepository;
import br.com.itau.ml.produto.imagem.ImagemForm;
import br.com.itau.ml.produto.imagem.Uploader;
import br.com.itau.ml.produto.opiniao.Opiniao;
import br.com.itau.ml.produto.opiniao.OpiniaoForm;
import br.com.itau.ml.produto.opiniao.OpiniaoRepository;
import br.com.itau.ml.produto.pergunta.Emails;
import br.com.itau.ml.produto.pergunta.Pergunta;
import br.com.itau.ml.produto.pergunta.PerguntaForm;
import br.com.itau.ml.produto.pergunta.PerguntaRepository;
import br.com.itau.ml.usuarios.Usuario;

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
	public void cadastrarProduto(@RequestBody @Valid ProdutoForm produtoForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = produtoForm.converter(categoriaRepository, usuario);
		produtoRepository.save(produto);
	}
	
	@PostMapping("/{id}/imagens")
	public void adicionarImagensAoProduto(@PathVariable Long id, @Valid ImagemForm imagensForm, @AuthenticationPrincipal Usuario usuario) {
		List<String> links = uploader.enviar(imagensForm.getImagens());
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema!");
		}
		if (produto.get().getUsuario().getId() != usuario.getId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "A imagem só pode ser cadastrada pelo seu usuário original!");
		}
		produto.get().associaImagens(links);
		produtoRepository.save(produto.get());	
	}
	
	@PostMapping("/{id}/opiniao")
	public void cadastrarOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoForm opiniaoForm, @AuthenticationPrincipal Usuario cliente) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema!");
		}
		Opiniao opiniao = opiniaoForm.converter(produtoRepository, cliente);
		opiniaoRepository.save(opiniao);
	}
	
	@PostMapping("/{id}/pergunta")
	public void fazerPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaForm perguntaForm, @AuthenticationPrincipal Usuario interessado) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema!");
		}
		Pergunta pergunta = perguntaForm.converter(produtoRepository, interessado);
		perguntaRepository.save(pergunta);
		emails.novaPergunta(pergunta);
	}
	
	@GetMapping
	public List<ProdutoDto> GetAll(){
		List<Produto> produtos = produtoRepository.findAll();
		return ProdutoDto.converter(produtos);
	}
	
	@GetMapping("/{id}")
	public DetalheProdutoDto codigoPaginaDetalhe (@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto não existe no sistema!");
		}
		return new DetalheProdutoDto(produto.get());
	}
}










