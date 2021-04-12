package br.com.itau.ml.compra.gateway.pagseguro;

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
@Table(name = "pag_pagseguro")
public class PagSeguro {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long compraId;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;
	
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public PagSeguro(Long compraId, StatusPagamento statusPagamento) {
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
