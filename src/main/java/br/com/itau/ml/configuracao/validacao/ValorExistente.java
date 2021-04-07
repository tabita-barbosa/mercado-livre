package br.com.itau.ml.configuracao.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ValorExistenteValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorExistente {
	
	String message() default "{}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default{ };
	String fieldName();
	Class<?> domainClass();

}
