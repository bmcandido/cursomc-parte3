package com.brunocandido.cursomc.resoucers.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	// Essa função serve para decodificar o retorno de uma String quando ele é
	// decodificada dentro da URL
	// os espaços são substituidos por %, o cara abaixo ele desfaz isso

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			return "";
		}

	}

	// Busca atraves de uma lista de ID separados por virgula retornando um inteiro

	public static List<Integer> decodeIntList(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {

			list.add(Integer.parseInt(vet[i]));

		}
		return list;
	}
}
