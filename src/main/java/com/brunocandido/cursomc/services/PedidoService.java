package com.brunocandido.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.ItemPedido;
import com.brunocandido.cursomc.domain.PagamentoBoleto;
import com.brunocandido.cursomc.domain.Pedido;
import com.brunocandido.cursomc.domain.Produto;
import com.brunocandido.cursomc.enuns.EstadoPagamento;
import com.brunocandido.cursomc.exceptions.ObjectNotFoundException;
import com.brunocandido.cursomc.repositories.ItemPedidoRepository;
import com.brunocandido.cursomc.repositories.PagamentoRepository;
import com.brunocandido.cursomc.repositories.PedidoRepository;
import com.brunocandido.cursomc.repositories.ProdutoRepository;

//2º Camada - Chama Repository

@Service
public class PedidoService {

	@Autowired // Anotação do Spring

	PedidoRepository repo;

	@Autowired

	BoletoService boletoService;

	@Autowired

	PagamentoRepository pagamentoRepository;

	@Autowired

	ProdutoRepository produtoRepository;

	@Autowired

	ItemPedidoRepository itemPedidoRepository;

	@Autowired

	ClienteServices clienteServices;
	
	@Autowired
	
	private EmailService emailService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	// Caso houvesse uma integragraçao com algum webservice este "cara" não
	// existiria, este servico iria
	// integrar com algum webservice para buscar essa informação dentro da Classe
	// BoletoService

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		// Setando o Cliente para Aparecer no POST quando mando imprimir a String da
		// classe domain.Cliente
		obj.setCliente(clienteServices.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			// ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());

			Optional<Produto> prod = produtoRepository.findById(ip.getProduto().getId());
			ip.setProduto(prod.get());
			ip.setPreco(prod.get().getPreco());
			
			

			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());

		// Quando eu coloco o objeto dentro do println automaticamente ele chama o
		// toString do objeto
		// Teste e-mail
		System.out.println(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
