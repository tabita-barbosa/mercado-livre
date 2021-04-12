package br.com.itau.ml.compra.gateway.pagseguro;

import br.com.itau.ml.compra.PagamentoForm;

public class PagSeguroForm implements PagamentoForm{
	
	private Long pagamentoId;
	
	private Long compraId;
	
	private String statusPagamento;

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

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
	@Override
	public Integer getStatusOrdinal() {
		return null;
	}
	
	@Override
	public String getStatusString() {
		return statusPagamento;
	}

}
