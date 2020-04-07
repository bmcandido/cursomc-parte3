package com.brunocandido.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.brunocandido.cursomc.security.UserSpringSecurity;

//Metodo que Retorna o Usuario Logado

public class UserService {

	public static UserSpringSecurity authenticated() {

		// Retorna o Usuario que está logado no sistema
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
