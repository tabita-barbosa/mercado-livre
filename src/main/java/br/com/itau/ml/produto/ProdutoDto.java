package br.com.itau.ml.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.itau.ml.produto.caracteristica.Caracteristica;

public class ProdutoDto {

	private String nome;
	private BigDecimal valor;
	private Long quantidade;
	@JsonIgnoreProperties(value = "produto")
	private List<Caracteristica> caracteristicas;
	private String descricao;
	private String categoria;
	private LocalDateTime cadastradoEm = LocalDateTime.now();
	
	
	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.caracteristicas = produto.getCaracteristicas();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria().getNome();
		this.cadastradoEm = produto.getCadastradoEm();
	}


	public String getNome() {
		return nome;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public Long getQuantidade() {
		return quantidade;
	}


	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}


	public String getDescricao() {
		return descricao;
	}


	public String getCategoria() {
		return categoria;
	}


	public LocalDateTime getCadastradoEm() {
		return cadastradoEm;
	}
	
	public static List<ProdutoDto> converter(List<Produto> produtos){
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
}
