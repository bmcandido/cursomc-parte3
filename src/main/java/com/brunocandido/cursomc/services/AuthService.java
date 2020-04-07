package com.brunocandido.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.Cliente;
import com.brunocandido.cursomc.exceptions.ObjectNotFoundException;
import com.brunocandido.cursomc.repositories.ClienteRepository;

//Classe para Recuperação de Senha

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	// Faz a injeção no banco de dados do novo password
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			// Caso nao exista o e-mail ele retorna mensagem
			throw new ObjectNotFoundException("E-mail não encontrado");

		}

		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));

		// Salva a Nova Senha
		clienteRepository.save(cliente);

		// envia email com password

		emailService.sendNewPasswordEmail(cliente, newPass);

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}

}
