package com.brunocandido.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunocandido.cursomc.domain.Cliente;

@Repository


//1º Camada

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
