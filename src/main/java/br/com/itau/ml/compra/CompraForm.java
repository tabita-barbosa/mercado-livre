package br.com.itau.ml.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.itau.ml.produto.Produto;
import br.com.itau.ml.usuarios.Usuario;

public class CompraForm {
	
	@NotNull
	@Positive
	private Long qtdProduto;
	
	@NotBlank
	private String tipoPagamento;

	public Long getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(Long qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public Compra converter(Produto produto, Usuario usuario) {
		return new Compra (produto, qtdProduto, usuario, tipoPagamento, Status.INICIADO);
	}

}
