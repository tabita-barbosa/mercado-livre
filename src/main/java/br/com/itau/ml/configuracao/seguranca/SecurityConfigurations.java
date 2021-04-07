package br.com.itau.ml.configuracao.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/logar").permitAll()
		.antMatchers(HttpMethod.POST, "/cadastrar").permitAll()
		.antMatchers(HttpMethod.GET, "/paypal/**").permitAll()
		.antMatchers(HttpMethod.GET, "/pagseguro/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	// configuração para recusos estáticos -> js, css, imagens e afins
	@Override
	public void configure (WebSecurity we) throws Exception{
		
	}

}
