/*
 * Created on Feb 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.merlin.data.dto;

/**
 * @author Leonardo Lopes
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ServicoDTO {

    private Integer codigoServico;
    private String nomeServico;
    private double valorServico;
    private String tipoServico;

    /**
     * @return Returns the codigoServico.
     */
    public Integer getCodigoServico() {
        return codigoServico;
    }

    /**
     * @param codigoServico The codigoServico to set.
     */
    public void setCodigoServico(Integer codigoServico) {
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
}
