package com.brunocandido.cursomc.resoucers.exceptions;

import java.util.ArrayList;
import java.util.List;

//Classe Criada para devolver somente um erro por exemplo : ao usuario inserir um dado em um campo que não aceita nulo
// Complemento da Classe StandardError

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
