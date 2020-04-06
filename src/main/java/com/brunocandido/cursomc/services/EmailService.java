package com.brunocandido.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.brunocandido.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
