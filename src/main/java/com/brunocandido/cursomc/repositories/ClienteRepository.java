package com.brunocandido.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brunocandido.cursomc.domain.Cliente;

@Repository


//1º Camada

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly =true) //Anotação para melhorar a performace
	Cliente findByEmail(String email);
	//O Sptring data entende de forma automatica quando vc cria o validador da seguinte forma
	// findBy + campo que deseja validar, consequentemente colocando a excessao dentro da classe
	// ClienteInsertValidator
}
