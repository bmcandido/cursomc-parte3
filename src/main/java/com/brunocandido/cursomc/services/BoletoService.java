package com.brunocandido.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.brunocandido.cursomc.domain.PagamentoBoleto;

@Service
public class BoletoService {
	
	// No mundo real teria uma chamada de um webService dentro dessa classe para buscar 
	// a data de vencimento do boleto
	public void preencherPagamentoBoleto(PagamentoBoleto  pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
