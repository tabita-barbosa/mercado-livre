package br.com.itau.ml.produto.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {
	
	@Autowired
	private Mailer mailer;
	
	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...<html>", "Nova pergunta...", 
				pergunta.getUsuario().getLogin(),
				"novapergunta@nossomercadolivre.com", 
				pergunta.getProduto().getUsuario().getLogin());
	}
	
}
