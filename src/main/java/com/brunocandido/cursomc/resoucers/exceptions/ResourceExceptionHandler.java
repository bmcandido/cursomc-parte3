package com.brunocandido.cursomc.resoucers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brunocandido.cursomc.exceptions.DataIntegrityException;
import com.brunocandido.cursomc.exceptions.ObjectNotFoundException;

//Esta Classe de Excessão serve para retornar do Jason um erro caso o usuario digite uma pagina não existente por exemplo
//Esta classe é o elo de ligação entre a tratativa de erro ObjectNotFoundException e a WEB

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) // Tratador de excessoes dentro do objeto ObjectNotFoudException
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class) // Tratador de excessoes dentro do objeto DataintegrityExeption
	public ResponseEntity<StandardError> dataIntegrit(DataIntegrityException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // Tratador de excessoes dentro do objeto
																// DataintegrityExeption
	public ResponseEntity<StandardError> methodArgExcep(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validaçao",
				System.currentTimeMillis());
		//e.getBindingResult().getFieldError() acessa a lista de erros dentro do MethodArgumentNotValidException
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
