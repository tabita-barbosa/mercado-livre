package br.com.itau.ml.compra.gateway.pagseguro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.ml.compra.gateway.StatusPagamento;

@RestController
@RequestMapping("/pagseguro")
public class PagSeguroController {

	@Autowired
	private PagSeguroRepository pagSeguroRepository;
	
	@GetMapping
	public ResponseEntity<?> pagSeguro (@RequestParam(required = true) Long returnId, @RequestParam(required = true) String redirectUrl){
		Optional<PagSeguro> buscaCompra = pagSeguroRepository.findByCompraId(returnId);
		
		if(buscaCompra.isPresent() && buscaCompra.get().getStatusPagamento() ==  StatusPagamento.SUCESSO) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		PagSeguro pagar = new PagSeguro (returnId, StatusPagamento.SUCESSO);
		pagSeguroRepository.save(pagar);
		
		return ResponseEntity.ok().body(new PagSeguroDto(pagar));
	}
	
}
