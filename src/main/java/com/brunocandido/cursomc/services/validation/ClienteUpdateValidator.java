package com.brunocandido.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.brunocandido.cursomc.domain.Cliente;
import com.brunocandido.cursomc.dto.ClienteDTO;
import com.brunocandido.cursomc.repositories.ClienteRepository;
import com.brunocandido.cursomc.resoucers.exceptions.FieldMessage;

//Criando uma  anotação
//ClienteInsert e ClienteNewDTO sap classes que fazem parte da anotação/
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	// Este Autowired busca o endereço dentro do JSON
	@Autowired
	private HttpServletRequest request;

	@Autowired
	ClienteRepository repo;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		// Map é um pacote do java util que traz uma estrutura buscando a chave e o ID
		// Exemplo categoria/1 a chave é a categoria e o 1 é o id
		// o HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE Pega um Mapping de
		// variaveis de URI

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
