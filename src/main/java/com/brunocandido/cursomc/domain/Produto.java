package com.brunocandido.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descricao;
	private Double preco;
	private String complemento;
    
	@JsonIgnore
	//@JsonBackReference
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id")) // Estes
																																					// ID
	// Tabela Temporaria de Ligação
	private List<Categoria> categorias = new ArrayList<>();
     
	@JsonIgnore
	//@JsonBackReference
	@ManyToMany
	@JoinTable(name = "PRODUTO_CLASSIFICACAO", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "classificacaoproduto_id")) // Estes
																																									// Comandos
	private List<ClassificacaoProduto> classificacaoProduto = new ArrayList<>();
   
	@JsonIgnore
	//@JsonBackReference
	@ManyToMany
	@JoinTable(name = "PRODUTO_TIPO", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "tipoproduto_id")) // Estes
																																					// ID
	private List<TipoProduto> tipoProduto = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto") // Observa na Classe ItemPedidoPrimaryKey que a associação é a junção do id da
	// Classe mais a classe produto
	private Set<ItemPedido> itens = new HashSet<>();

	public Produto() {

	}

	public Produto(Integer id, String descricao, Double preco, String complemento) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.complemento = complemento;
		// Categoria não foi inicializada porque já foi inicializada dentro da
		// declaração
	}

	@JsonIgnore // Tem que ignorar o Get também para não serializar entao o @JsonIgnore é feito
				// aqui dentro da declaracao private Set<ItemPedido> itens = new HashSet<>();
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : this.itens) {
			lista.add(x.getPedido());
		}
		return lista;
	} // Criado este metodo para retornar dentro de produto a lista de pedidos

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<ClassificacaoProduto> getClassificacaoProduto() {
		return classificacaoProduto;
	}

	public void setClassificacaoProduto(List<ClassificacaoProduto> classificacaoProduto) {
		this.classificacaoProduto = classificacaoProduto;
	}

	public List<TipoProduto> getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(List<TipoProduto> tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
