package com.brunocandido.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunocandido.cursomc.domain.ItemPedido;

@Repository


//1º Camada

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
