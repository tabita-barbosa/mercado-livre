package br.com.itau.ml.compra;

import br.com.itau.ml.produto.ProdutoDto;

public class CompraDto {
	
	private Long id;
	
	private ProdutoDto produto;
	
	private Long qtdProduto;
	
	private String tipoPagamento;
	
	private Status status;
	
	private Long pagamentoId;
	
	
	public CompraDto(Compra compra) {
		this.id = compra.getId();
		this.produto = new ProdutoDto(compra.getProduto());
		this.qtdProduto = compra.getQtdProduto();
		this.tipoPagamento = compra.getTipoPagamento();
		this.status = compra.getStatus();
		this.pagamentoId = compra.getPagamentoId();
	}

	public Long getId() {
		return id;
	}

	public ProdutoDto getProduto() {
		return produto;
	}

	public Long getQtdProduto() {
		return qtdProduto;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public Status getStatus() {
		return status;
	}

	public Long getPagamentoId() {
		return pagamentoId;
	}

}
