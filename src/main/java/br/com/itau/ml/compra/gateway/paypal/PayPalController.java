package br.com.itau.ml.compra.gateway.paypal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.ml.compra.gateway.StatusPagamento;

@RestController
@RequestMapping("/paypal")
public class PayPalController {
	@Autowired
	private PayPalRepository payPalRepository;
	
	@GetMapping
	public ResponseEntity<?> payPal (@PathVariable Long returnId, @RequestParam(required = true) String redirectUrl){
		Optional<PayPal> buscaCompra = payPalRepository.findByCompraId(returnId);
		
		if(buscaCompra.isPresent() && buscaCompra.get().getStatusPagamento() ==  StatusPagamento.SUCESSO) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		PayPal pagar = new PayPal (returnId, StatusPagamento.SUCESSO);
		payPalRepository.save(pagar);
		
		return ResponseEntity.ok().body(new PayPalDto(pagar));
	}
	
}
