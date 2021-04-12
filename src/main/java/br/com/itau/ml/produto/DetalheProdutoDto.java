package br.com.itau.ml.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import br.com.itau.ml.produto.caracteristica.DetalheCaracteristicaDto;
import br.com.itau.ml.produto.opiniao.CalculoOpiniao;

public class DetalheProdutoDto {

	private String descricao;
	private String nome;
	private BigDecimal valor;
	private List<DetalheCaracteristicaDto> caracteristicas;
	private List<String> linksImagens;
	private List<String> perguntas;
	private List<Map<String, String>> opinioes;
	private Double mediaNotas;
	private Long total;
	
	public DetalheProdutoDto(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristicaDto::new);
		this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
		
		CalculoOpiniao calculoOpiniao = produto.getCalculoOpiniao();
		
		this.opinioes = calculoOpiniao.mapOpinioes(opiniao -> {
			return Map.of("titulo:", opiniao.getTitulo(), "descrição:", opiniao.getDescricao());
		});
		
		this.mediaNotas = calculoOpiniao.media();
		this.total = calculoOpiniao.total();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public List<DetalheCaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

	public List<String> getLinksImagens() {
		return linksImagens;
	}

	public List<String> getPerguntas() {
		return perguntas;
	}

	public List<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Long getTotal() {
		return total;
	}
	
}
