package com.brunocandido.cursomc.dto;

import java.io.Serializable;

import com.brunocandido.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;
	private Double preco;
	private String complemento;

	public ProdutoDTO() {

	}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getnome();
		preco = obj.getPreco();
		complemento=obj.getComplemento();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return nome;
	}

	public void setDescricao(String descricao) {
		this.nome = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
