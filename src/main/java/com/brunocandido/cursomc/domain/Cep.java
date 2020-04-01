package com.brunocandido.cursomc.domain;

import java.io.Serializable;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

public class Cep implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String bairro;
	private String logadouro;
	private String cep;
	private String complemento;

	public Cep() {
	}

	public Cep(String cepe) throws Exception {
		super();
		ViaCEPClient cliente = new ViaCEPClient();
		ViaCEPEndereco codigoCep = cliente.getEndereco(cepe);
		this.bairro = codigoCep.getBairro();
		this.logadouro = codigoCep.getLogradouro();
		this.cep = cepe;

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
