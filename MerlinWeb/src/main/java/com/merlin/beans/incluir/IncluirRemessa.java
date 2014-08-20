/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.beans.incluir;

import com.merlin.data.dto.RemessaDTO;
import com.merlin.data.managers.RemessaManager;
import java.util.Date;
import java.util.logging.Logger;

public class IncluirRemessa {

    private static final Logger logger = Logger.getLogger(IncluirServicoUtilizado.class.getName());

    private RemessaDTO remessa;
    private int codigoRemessa;
    private int numeroRemessa;
    private Date dataEmissao;
    private String mensagem;

    public IncluirRemessa() {
        RemessaManager rm = new RemessaManager();
        remessa = rm.select(1);
        codigoRemessa = remessa.getCodigoRemessa();
        numeroRemessa = remessa.getNumeroRemessa();
        dataEmissao = remessa.getDataEmissao();
    }

    public void doIncluir() {
        RemessaManager rm = new RemessaManager();

        remessa = rm.getNewBean();
        remessa.setNumeroRemessa(numeroRemessa);
        remessa.setDataEmissao(dataEmissao);

        rm.update(remessa);
        // limpa a tela

        remessa = null;
        mensagem = "Sequencial da remessa atualizado com sucesso.";
    }

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

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
