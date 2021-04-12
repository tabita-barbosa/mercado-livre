package br.com.itau.ml.compra.gateway.paypal;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.itau.ml.compra.gateway.StatusPagamento;

@Entity
@Table(name = "pag_paypal")
public class PayPal {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long compraId;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusPagamento statusPagamento;
	
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public PayPal() {}
	
	public PayPal(Long compraId, StatusPagamento statusPagamento) {
		this.compraId = compraId;
		this.statusPagamento = statusPagamento;
	}

	public Long getId() {
		return id;
	}

	public Long getCompraId() {
		return compraId;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}
