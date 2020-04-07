package com.brunocandido.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.Cliente;
import com.brunocandido.cursomc.repositories.ClienteRepository;
import com.brunocandido.cursomc.security.UserSpringSecurity;

//Classe para validação de usuario
// 2 camada vinculada a classe UserSpringSecurity

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}
