package br.com.itau.ml.compra;

public interface PagamentoForm {
	
	public Long getCompraId();
	public Long getPagamentoId();
	public Integer getStatusOrdinal();
	public String getStatusString();

}
