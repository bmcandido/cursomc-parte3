package com.brunocandido.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brunocandido.cursomc.domain.Cliente;
import com.brunocandido.cursomc.dto.ClienteNewDTO;
import com.brunocandido.cursomc.enuns.TipoCliente;
import com.brunocandido.cursomc.repositories.ClienteRepository;
import com.brunocandido.cursomc.resoucers.exceptions.FieldMessage;
import com.brunocandido.cursomc.services.validation.util.BR;

//Criando uma  anotação
//ClienteInsert e ClienteNewDTO sap classes que fazem parte da anotação/
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCgc())) {
			list.add(new FieldMessage("cpfCgc", "CPF inválido"));
		}

		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(objDto.getCpfCgc())) {
			list.add(new FieldMessage("cpfCgc", "CNPJ inválido"));
		}
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
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
