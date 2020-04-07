package com.brunocandido.cursomc.resoucers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brunocandido.cursomc.dto.EmailDTO;
import com.brunocandido.cursomc.security.JWTUtil;
import com.brunocandido.cursomc.security.UserSpringSecurity;
import com.brunocandido.cursomc.services.AuthService;
import com.brunocandido.cursomc.services.UserService;

//Classe para fazer um refresh no token quando ele estiver quanse expirando

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	private JWTUtil jwtUtil;

	@Autowired
	private AuthService autoService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	//Endpoint para o Forgot Password
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forGot(@Valid @RequestBody EmailDTO objDTO) {
		autoService.sendNewPassword(objDTO.getEmail());

		return ResponseEntity.noContent().build();
	}

}
