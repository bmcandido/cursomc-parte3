package com.brunocandido.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date instante;
    
	
	//@JsonManagedReference //Dentro da Classe Pagamento eu faço um @JsonBackReference contrapartida a essa anotação
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") // Quando o Id está em outra classe é usada esta anotação
																// com estes parametros
																// Chamado de mapeamento bidirecional 1 para 1
	private Pagamento pagamento;

	@JsonManagedReference // Dentro da Classe Cliente é feito o @JsonBackReference seria a contrapartida
							// do @JsonManagedReference
							// Esta anotação serve para que o serviço não dê um loop infinito
	//@JsonManagedReference 
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente; // Mapeado pois o Cliente pode ver o pedido e o Pedido pode enchergar o cliente

	@ManyToOne
	@JoinColumn(name = "endereco_entreda_id")
	private Enderecos enderecoDeEntrega;

	@OneToMany(mappedBy = "id.pedido") // Observa na Classe ItemPedidoPrimaryKey que a associação é a junção do id da
										// Classe mais a classe pedido
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {

	}

	public Pedido(Integer id, Date instante, Cliente cliente, Enderecos enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Enderecos getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Enderecos enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
