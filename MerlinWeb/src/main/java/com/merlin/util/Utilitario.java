/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.util;

import java.util.logging.Logger;

public class Utilitario {

    /**
     * Completa um valor com uma sequencia de caracteres definida
     *
     * @param valor O valor a ser completado
     * @param tam O tamanho final da sequencia
     * @param caracter o caracter a ser colocado
     * @return
     */
    public static String complete(String valor, int tam, String caracter) {
        if (valor.length() == tam) {
            return valor;
        }
        StringBuilder saida = new StringBuilder();
        for (int i = 0; i < tam - valor.length(); i++) {
            saida.append(caracter);
        }
        saida.append(valor);
        return saida.toString();

    }
    private static final Logger LOG = Logger.getLogger(Utilitario.class.getName());

}
