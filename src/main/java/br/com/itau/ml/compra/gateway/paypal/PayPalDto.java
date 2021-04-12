package br.com.itau.ml.compra.gateway.paypal;

import br.com.itau.ml.compra.gateway.StatusPagamento;

public class PayPalDto {
	
	private Long pagamentoId;
	
	private Long compraId;
	
	private StatusPagamento statusPagamento;
	
	public PayPalDto(PayPal payPal){
		this.pagamentoId = payPal.getId();
		this.compraId = payPal.getCompraId();
		this.statusPagamento = payPal.getStatusPagamento();
	}

	public Long getPagamentoId() {
		return pagamentoId;
	}

	public Long getCompraId() {
		return compraId;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}
	
}
