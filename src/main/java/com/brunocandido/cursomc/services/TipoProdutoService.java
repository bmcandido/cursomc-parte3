package com.brunocandido.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.Categoria;
import com.brunocandido.cursomc.domain.TipoProduto;
import com.brunocandido.cursomc.exceptions.ObjectNotFoundException;
import com.brunocandido.cursomc.repositories.TipoProdutoRepository;

@Service
public class TipoProdutoService {
	
	@Autowired
	TipoProdutoRepository repo;
	
	public TipoProduto find(Integer id) {
		Optional<TipoProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
