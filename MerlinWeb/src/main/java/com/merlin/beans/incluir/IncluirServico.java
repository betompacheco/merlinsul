/*
 * Created on 11/02/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.merlin.beans.incluir;

import com.merlin.data.dto.ServicoDTO;
import com.merlin.data.managers.ServicoManager;
import java.util.logging.Logger;

public class IncluirServico {

    private final static Logger logger = Logger.getLogger(IncluirServico.class.getName());

    private ServicoDTO servico;
    private int codigoServico;
    private String nomeServico;
    private double valorServico;
    private String tipoServico;
    private String mensagem;

    public void doIncluir() {
        ServicoManager sm = new ServicoManager();
        boolean novo = (codigoServico == 0);
        if (novo) {
            servico = sm.getNewBean();
        } else {
            servico = sm.select(codigoServico);
            servico.setCodigoServico(new Integer(codigoServico));
        }
        servico.setNomeServico(nomeServico);
        servico.setValorServico(valorServico);
        servico.setTipoServico(tipoServico);

        if (novo) {
            sm.insert(servico);
            // limpa a tela

            servico = null;
            mensagem = "Serviço inserido com sucesso.";
        } else {
            sm.update(servico);
            // limpa a tela

            servico = null;
            mensagem = "Serviço atualizado com sucesso.";
        }

    }

    public void setServico(ServicoDTO servico) {
        this.servico = servico;
        this.codigoServico = servico.getCodigoServico().intValue();
        this.nomeServico = servico.getNomeServico();
        this.valorServico = servico.getValorServico();
        this.tipoServico = servico.getTipoServico();
    }

    /**
     * @return Returns the codigoServico.
     */
    public int getCodigoServico() {
        return codigoServico;
    }

    /**
     * @param codigoServico The codigoServico to set.
     */
    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    /**
     * @return Returns the nomeServico.
     */
    public String getNomeServico() {
        return nomeServico;
    }

    /**
     * @param nomeServico The nomeServico to set.
     */
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    /**
     * @return Returns the tipoServico.
     */
    public String getTipoServico() {
        return tipoServico;
    }

    /**
     * @param tipoServico The tipoServico to set.
     */
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * @return Returns the valorServico.
     */
    public double getValorServico() {
        return valorServico;
    }

    /**
     * @param valorServico The valorServico to set.
     */
    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    /**
     * @return Returns the servico.
     */
    public ServicoDTO getServico() {
        return servico;
    }

    /**
     * @return Returns the mensagem.
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem The mensagem to set.
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
