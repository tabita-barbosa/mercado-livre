package br.com.itau.ml.compra;

import java.net.URI;
import java.text.MessageFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.ml.compra.gateway.pagseguro.PagSeguroForm;
import br.com.itau.ml.compra.gateway.paypal.PayPalForm;
import br.com.itau.ml.configuracao.validacao.ValorExistente;
import br.com.itau.ml.produto.Produto;
import br.com.itau.ml.produto.ProdutoRepository;
import br.com.itau.ml.usuarios.Usuario;

@RestController
@RequestMapping("/compra")
@Validated
public class CompraController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CompraRepository compraRepository;

	@PostMapping("/{id}")
	public ResponseEntity<?> AdicionarCompra(
			@PathVariable @ValorExistente(domainClass = Produto.class, fieldName = "id", message = "O nome da categoria precisa ser único") Long id,
			@RequestBody @Valid CompraForm compraForm, @AuthenticationPrincipal Usuario usuario) {

		Produto produto = produtoRepository.findById(id).get();

		if (compraForm.getQtdProduto() > produto.getQuantidade()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe estoque deste produto");
		}
		if (compraForm.getTipoPagamento().compareToIgnoreCase("paypal") != 0
				&& compraForm.getTipoPagamento().compareToIgnoreCase("pagSefuro") != 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Método de pagamento não suportado");
		}

		produto.abateEstoque(compraForm.getQtdProduto());
		produtoRepository.save(produto);
		Compra compra = compraForm.converter(produto, usuario);
		compraRepository.save(compra);
		PagamentoForm pgtoForm = requisicaoPagamento(compra, compraForm);
		compra.setPagamentoId(pgtoForm.getPagamentoId());
		compra.setStatus(Status.SUCESSO);
		compraRepository.save(compra);

		return ResponseEntity.ok().body(new CompraDto(compra));
	}

	public PagamentoForm requisicaoPagamento(Compra novaCompra, CompraForm compraForm) {
		
		PagamentoForm pgto = null;
		String params = UrlDeRequisicao(compraForm.getTipoPagamento(), novaCompra.getId(), "www");
		URI uriApi = URI.create("http://localhost:8080/" + params);
		RequestEntity<?> api = RequestEntity.method(HttpMethod.GET, uriApi).accept(MediaType.APPLICATION_JSON).build();
		
		if (compraForm.getTipoPagamento().compareToIgnoreCase("paypal") == 0) {
			ResponseEntity<PayPalForm> pagamentoPayPal = new RestTemplate().exchange(api, PayPalForm.class);
			pgto = pagamentoPayPal.getBody();
			System.out.println(pgto.getStatusString());
		}
		
		if (compraForm.getTipoPagamento().compareToIgnoreCase("pagseguro") == 0) {
			ResponseEntity<PagSeguroForm> pagamentoPagSeguro = new RestTemplate().exchange(api, PagSeguroForm.class);
			pgto = pagamentoPagSeguro.getBody();
			System.out.println(pgto.getStatusString());
			
		}
		
		return pgto;
	}

	public String UrlDeRequisicao(String metodoPagamento, Long id, String redirect) {
		System.out.println(metodoPagamento + id + redirect);
//		if (metodoPagamento.compareToIgnoreCase("paypal") == 0 ) {
//			return MessageFormat.format("{0}?returnId={1}&redirectUrl={2}", metodoPagamento.toLowerCase(), id, redirect);
//		}
		if (metodoPagamento.compareToIgnoreCase("pagseguro") == 0) {
			return MessageFormat.format("{0}?returnId={1}&redirectUrl={2}", metodoPagamento.toLowerCase(), id,
					redirect);
		}

		return MessageFormat.format("{0}/{1}?redirectUrl={2}", metodoPagamento.toLowerCase(), id, redirect);
	}
}
