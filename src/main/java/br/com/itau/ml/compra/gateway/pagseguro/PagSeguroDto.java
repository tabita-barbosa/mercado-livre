package br.com.itau.ml.compra.gateway.pagseguro;

import br.com.itau.ml.compra.gateway.StatusPagamento;

public class PagSeguroDto {

	private Long pagamentoId;
	
	private Long compraId;
	
	private StatusPagamento statusPagamento;

	public PagSeguroDto(PagSeguro pagSeguro) {
		this.pagamentoId = pagSeguro.getId();
		this.compraId = pagSeguro.getCompraId();
		this.statusPagamento = pagSeguro.getStatusPagamento();
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
