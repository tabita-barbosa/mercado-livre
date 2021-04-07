package br.com.itau.ml.configuracao.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ValorExistenteValidator implements ConstraintValidator<ValorExistente, Object>{
	
	private String atributoAPesquisar;
	private Class<?> classeEntidade;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(ValorExistente params) {
		atributoAPesquisar = params.fieldName();
		classeEntidade = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object valorAPesquisar, ConstraintValidatorContext context) {
		Query query = manager
				.createQuery("select 1 from " + classeEntidade.getName() + "where " + atributoAPesquisar + "=: value");
		query.setParameter("value", valorAPesquisar);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Foi encontrado mais de um " + classeEntidade + "com atributo " + atributoAPesquisar + " = " + valorAPesquisar);
		
		if(list.isEmpty() == true) {
			return false;
		}
		return true;
	}

}
