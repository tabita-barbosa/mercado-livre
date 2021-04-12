package br.com.itau.ml.compra.gateway.paypal;

import br.com.itau.ml.compra.PagamentoForm;
import br.com.itau.ml.compra.gateway.StatusPagamento;

public class PayPalForm implements PagamentoForm{
	
	private Long pagamentoId;
	
	private Long compraId;
	
	private StatusPagamento statusPagamento;

	public Long getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(Long pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

	public Long getCompraId() {
		return compraId;
	}

	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
	@Override
	public Integer getStatusOrdinal() {
		return statusPagamento.ordinal();
	}
	
	@Override
	public String getStatusString() {
		return null;
	}

}
