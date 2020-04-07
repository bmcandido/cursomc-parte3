package com.brunocandido.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.brunocandido.cursomc.security.JWTAuthenticationFilter;
import com.brunocandido.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	public UserDetailsService userDetailsService;

	@Autowired
	public JWTUtil jwtUtil;

	// Diz para as configurações @Configuration quais endereços estão liberados sem
	// autentificação
	private static final String[] PUBLIC_MATCHERS = {

			"/h2-console/**" };

	private static final String[] PUBLIC_MATCHERS_GET = {

			"/produtos/**", "/categorias/**", "/clientes/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	// Configurações basicas para liberar o acesso pelo cors() citado acima, tem que
	// haver isso pois as requisições
	// serão geradas por APP
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	// Configurações de Senha do Cliente

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
