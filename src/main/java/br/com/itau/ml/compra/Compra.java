package br.com.itau.ml.compra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Status;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_compra")
public class Compra {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Produto produto;
	
	@Positive
	@NotNull
	private Long qtdProduto;
	
	@ManyToOne
	private Usuario usuario;
	
	@NotBlank
	private String tipoPagamento;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Long pagamentoId;
	
	public Compra() {}

	public Compra(Produto produto, @Positive @NotNull Long qtdProduto, Usuario usuario, @NotBlank String tipoPagamento,
			Status status) {
		this.produto = produto;
		this.qtdProduto = qtdProduto;
		this.usuario = usuario;
		this.tipoPagamento = tipoPagamento;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(Long pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getQtdProduto() {
		return qtdProduto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}
	
}
