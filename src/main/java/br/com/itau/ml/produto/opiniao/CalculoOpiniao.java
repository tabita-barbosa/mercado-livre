package br.com.itau.ml.produto.opiniao;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculoOpiniao {
	
	private List<Opiniao> opinioes;
	
	public CalculoOpiniao(List<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	
	public <T> List<T> mapOpinioes(Function<Opiniao, T> funcaoMap){
		return this.opinioes.stream().map(funcaoMap).collect(Collectors.toList());
	}
	
	public Double media() {
		List<Long> notas = mapOpinioes(opiniao -> opiniao.getNotas());
		OptionalDouble average = notas.stream().mapToLong(nota -> nota). average();
		
		if(average.isPresent()) {
			return average.getAsDouble();
		}
		return 0.0;
	}

	public Long total() {
		return (long) this.opinioes.size();
	}
	
}
