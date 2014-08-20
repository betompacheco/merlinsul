/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.data.dto;

import java.util.Date;


public class RemessaDTO {

    private int codigoRemessa;
    private int numeroRemessa;
    private Date dataEmissao;

    /**
     * @return the codigoRemessa
     */
    public int getCodigoRemessa() {
        return codigoRemessa;
    }

    /**
     * @param codigoRemessa the codigoRemessa to set
     */
    public void setCodigoRemessa(int codigoRemessa) {
        this.codigoRemessa = codigoRemessa;
    }

    /**
     * @return the numeroRemessa
     */
    public int getNumeroRemessa() {
        return numeroRemessa;
    }

    /**
     * @param numeroRemessa the numeroRemessa to set
     */
    public void setNumeroRemessa(int numeroRemessa) {
        this.numeroRemessa = numeroRemessa;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
