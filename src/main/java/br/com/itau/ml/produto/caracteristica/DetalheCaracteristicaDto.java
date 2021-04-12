package br.com.itau.ml.produto.caracteristica;

public class DetalheCaracteristicaDto {
	
	private String nome;
	private String descricao;
	
	public DetalheCaracteristicaDto(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
