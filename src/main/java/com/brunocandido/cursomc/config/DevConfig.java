package com.brunocandido.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brunocandido.cursomc.services.DBService;

//criado para configurações especificas da aplicação que deseja executar
//como no exemplo atraves da anotação @Profile eu especifico que essas confifurações só serão rodadas dentro
// do profile test disponivel em src/main/resources/application-teste-properties 
@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
    
	//A anotação @Value ela vai pegar dentro da classe application.properties o valor que esta dentro dela
	// o valor que está dentro dela corresponde a criação do banco de dados toda vez que executar a rotina
	// logo caso o banco já tenha sido criado não é necessario a execuço pelo  instantiateDatabase() que é
	// responsavel pela criação da executação e criação do banco
	
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		//O comando create como no chamado abaixo caso ele seja create ele vai devolver falso, ou seja
		//nao vai executar nada
		if(!"create".equals(strategy)) {
			return false;
		} 
		dbService.instantiateTestDatabase();
		return true;
	}

}
