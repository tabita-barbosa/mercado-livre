package br.com.itau.ml.compra;

public class CompraDto {
	
	private Long id;
	
	private ProdutoDto produto;
	
	private Long qtdProduto;
	
	private String tipoPagamento;
	
	private Status status;
	
	private Long pagamentoId;
	
	public CompraDto(Long id, ProdutoDto produto, Long qtdProduto, String tipoPagamento, Status status,
			Long pagamentoId) {
		super();
		this.id = id;
		this.produto = produto;
		this.qtdProduto = qtdProduto;
		this.tipoPagamento = tipoPagamento;
		this.status = status;
		this.pagamentoId = pagamentoId;
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
