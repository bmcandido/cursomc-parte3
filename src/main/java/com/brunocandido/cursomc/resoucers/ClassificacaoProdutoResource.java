package com.brunocandido.cursomc.resoucers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.brunocandido.cursomc.domain.ClassificacaoProduto;
import com.brunocandido.cursomc.services.ClassificacaoProdutoService;

@RestController
@RequestMapping(value = "/classificacaoproduto")
public class ClassificacaoProdutoResource {

	@Autowired // Faz o instanciamento automaticamente dentro do objeto
	ClassificacaoProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)

	public ResponseEntity<ClassificacaoProduto> find(@PathVariable Integer id) {

		ClassificacaoProduto obj = service.find(id); // Acessa o Objeto de Repositorio CategoriaRepository

		return ResponseEntity.ok().body(obj); // Retornando o Objeto Encontrado na CAtegoria Repository


	}

}
