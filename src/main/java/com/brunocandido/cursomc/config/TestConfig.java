package com.brunocandido.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brunocandido.cursomc.services.DBService;
import com.brunocandido.cursomc.services.EmailService;
import com.brunocandido.cursomc.services.MockEmailService;

//criado para configurações especificas da aplicação que deseja executar
//como no exemplo atraves da anotação @Profile eu especifico que essas confifurações só serão rodadas dentro
// do profile test disponivel em src/main/resources/application-teste-properties 
@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
