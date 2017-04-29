/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.util;

import java.text.Normalizer;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.text.MaskFormatter;

public class Utilitario {

	public enum Direcao {

		ESQUERDA, DIREITA
	}

	private static final Logger logger = Logger.getLogger(Utilitario.class.getName());

	/**
	 * Completa um valor com uma sequencia de caracteres definida
	 *
	 * @param valor
	 *            O valor a ser completado
	 * @param tam
	 *            O tamanho final da sequencia
	 * @param caracter
	 *            o caracter a ser colocado
	 * @param dir
	 *            Informa se o valor deve ficar alinhado à direita ou esquerda
	 * @return A sequencia completa
	 */
	public static String complete(String valor, int tam, String caracter, Direcao dir) {
		if (valor.length() == tam) {
			return valor;
		}
		StringBuilder saida = new StringBuilder();
		for (int i = 0; i < tam - valor.length(); i++) {
			saida.append(caracter);
		}

		switch (dir) {
		case ESQUERDA:
			return valor.concat(saida.toString());
		case DIREITA:
			return saida.toString().concat(valor);
		default:
			return saida.toString().concat(valor);
		}
	}

	public static String formataCpfCnpj(String cpfCnpj) {
		MaskFormatter mf = new MaskFormatter();
		mf.setValueContainsLiteralCharacters(false);

		try {
			if (cpfCnpj.length() == 11) {
				mf.setMask("###.###.###-##");
				return mf.valueToString(cpfCnpj);
			} else if (cpfCnpj.length() == 14) {
				mf.setMask("##.###.###/####-##");
				return mf.valueToString(cpfCnpj);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return cpfCnpj;
	}

	public static String removeAcentos(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;

	};

	private Utilitario() {

	}

}
