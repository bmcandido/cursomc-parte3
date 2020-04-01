package com.brunocandido.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.brunocandido.cursomc.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Usado para instanciar Tabela e Subclasses
// no nosso caso PAgamentoBoleto e PagaemntoComCartao
public abstract class Pagamento implements Serializable { // Abstract para garantir que eu tenho que instanciar uma
															// classe de Extenção

	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY) não possui pois o Id é do
	// pedido e não do Pagamento
	private Integer id;
	private Integer estado;

	@JsonIgnore
	// @JsonBackReference // Em contrapartida na Classe pagamento tenho a anotação
	// @JsonManagedReference
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId // Garante que o Id informado aqui é o mesmo que está no pedido
	private Pedido pedido;

	public Pagamento() {

	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado); // Para Pegar o numero inteiro dentro da Classe ENUM EstadoPagamento
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
