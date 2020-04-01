package com.brunocandido.cursomc.resoucers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunocandido.cursomc.domain.Categoria;
import com.brunocandido.cursomc.dto.CategoriaDTO;
import com.brunocandido.cursomc.services.CategoriaService;


//REST CONTROLE, quando se fala em REST quer dizer que são as categorias resources
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired // Faz o instanciamento automaticamente dentro do objeto
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Depois de Criada a Classe CategoriaResourc
																	// acrescenta value="/{id}",

	/// {id} vai concatenar o Id buscado na URL

	public ResponseEntity<Categoria> find(@PathVariable Integer id) { // Também, é necessario acrescentar @PathVariable
																		// que
		// Vincula o id do RequestingMaping ao objeto que busca
		// o ID "FIND"
		// ResponseEntity<?> encapsula a resposta para o REST

		Categoria obj = service.find(id); // Acessa o Objeto de Repositorio CategoriaRepository

		return ResponseEntity.ok().body(obj); // Retornando o Objeto Encontrado na CAtegoria Repository

//    	Categoria cat1 = new Categoria(1, "Informática");
//		Categoria cat2 = new Categoria(2, "Escritório");
//		List<Categoria> lista = new ArrayList<>();
//		lista.add(cat1);
//		lista.add(cat2);
//		return lista;

	}

	// Serve para acrescentar/inserir a URL de coneção classe Categoria exemplo
	// localhost:8580/categoria/1, insere dentro do post
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Serve para alterar/update uma url já existente (Dados que estao nela)

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}

	// Serve para deletar

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)

	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);

	}

	//Quando efetuar a pesquisa no  Jason este metodo faz a requisição de acordo com parametros que vc inserir no endereço
	// http://localhost:8580/categorias/page?lines=1&direction=DESC
	//nao é obrigatorio inserir todos parametros na requisição
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "lines", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderby", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(listDto);

	}

}
