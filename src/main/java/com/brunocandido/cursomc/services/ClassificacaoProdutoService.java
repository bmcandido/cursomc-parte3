package com.brunocandido.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.Categoria;
import com.brunocandido.cursomc.domain.ClassificacaoProduto;
import com.brunocandido.cursomc.exceptions.ObjectNotFoundException;
import com.brunocandido.cursomc.repositories.ClassificacaoProdutoRepository;

@Service
public class ClassificacaoProdutoService {
	
	@Autowired // Anotação do Spring
	ClassificacaoProdutoRepository classificacao;
	
	public ClassificacaoProduto find(Integer id) {
		Optional<ClassificacaoProduto> obj = classificacao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	

}
