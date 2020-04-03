package com.brunocandido.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore // Jason vai ignorar esta marcação
	@EmbeddedId // Significa que minha chave é dupla e esta relacionada em no ID da outra classe
	private ItemPedidoPrimaryKey id = new ItemPedidoPrimaryKey();
	private double desconto;
	private double quantidade;
	private double preco;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, double desconto, double quantidade, double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto); // Ao Criar o Construtor eu Substitui o id pela Classe Pedido e Produto
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;

	}

	// Um metodo quando ele não é vinculado a um campo da tabela ele irá aparecer no
	// Json

	public double getSubTotal() {
		return (preco * quantidade) - desconto;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();

	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return id.getProduto();

	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public ItemPedidoPrimaryKey getId() {
		return id;
	}

	public void setId(ItemPedidoPrimaryKey id) {
		this.id = id;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		//Convertendo para Brasileiro valores
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getnome());
		builder.append(", Qte : ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário :");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal :");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");

		return builder.toString();
	}

	// Enviando e-mail, primeiro passo inserir o meto toString

}
