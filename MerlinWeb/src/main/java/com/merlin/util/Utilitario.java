/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.util;

import java.util.logging.Logger;

public class Utilitario {

    private static final Logger logger = Logger.getLogger(Utilitario.class.getName());

    /**
     * Completa um valor com uma sequencia de caracteres definida
     *
     * @param valor O valor a ser completado
     * @param tam O tamanho final da sequencia
     * @param caracter o caracter a ser colocado
     * @param dir Informa se o valor deve ficar alinhado à direita ou esquerda
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
//        saida.append(valor);

        switch (dir) {
            case ESQUERDA:
                return valor.concat(saida.toString());
            case DIREITA:
                return saida.toString().concat(valor);
            default:
                return saida.toString().concat(valor);
        }

        //        return saida.toString();
    }

    private Utilitario() {

    }

    public enum Direcao {

        ESQUERDA, DIREITA
    };

}
