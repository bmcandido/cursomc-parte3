package com.brunocandido.cursomc.resoucers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brunocandido.cursomc.domain.TipoProduto;
import com.brunocandido.cursomc.services.TipoProdutoService;

@RestController
@RequestMapping(value = "/tipoproduto")
public class TipoProdutoResource {
	
	@Autowired
	TipoProdutoService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<TipoProduto> find(@PathVariable Integer id){
		
		TipoProduto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
		
	}

}
