package com.brunocandido.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//Camada 0
@Entity // Relação Objeto Banco de Dados
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id // Relação Objeto Banco de Dados diz que o campo abaixo ID é um ID da Tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Relação Objeto Banco de Dados dependendo do banco terá que
														// ser mudado, quando salvar altomaticamente ele irá criar a
														// tabela no banco de dados B2-b
	private Integer id;
	private String nome;
    
	
	//@JsonManagedReference //Acrescentado para tratar erro no momento que roda o Jason
	@ManyToMany(mappedBy = "categorias") // Mapeando dentro da tabela categorias nao precisa fazer todo o processo basta
											// informar o funcao mappedBy
	private List<Produto> produtos = new ArrayList<>();

	public Categoria() {
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
