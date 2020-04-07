package com.brunocandido.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.brunocandido.cursomc.domain.Cliente;
import com.brunocandido.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	
	//Para Tratar HTML
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
    
   //Para Tratar Dentro da Classe AbstractEmailService
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
