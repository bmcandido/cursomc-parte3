package com.brunocandido.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.brunocandido.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	//Busca da classe de propriedades do spring application.properties
	@Value("${default.sender}")
	
	//@Value("bmcandido14@gmail.com")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMEssageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected  SimpleMailMessage prepareSimpleMailMEssageFromPedido(Pedido obj) {
		SimpleMailMessage sm =new  SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Código :"+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
