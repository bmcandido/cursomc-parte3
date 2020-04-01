package com.brunocandido.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.Pedido;
import com.brunocandido.cursomc.exceptions.ObjectNotFoundException;
import com.brunocandido.cursomc.repositories.PedidoRepository;

//2º Camada - Chama Repository

@Service
public class PedidoService {

	@Autowired // Anotação do Spring

	PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
