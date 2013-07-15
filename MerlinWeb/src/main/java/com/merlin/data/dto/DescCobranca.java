package com.merlin.data.dto;

public class DescCobranca {

    private int codigoCobranca;
    private int codigoDescCobranca;
    private String descricao;
    private double valor;

    /**
     * @return Returns the codigoCobranca.
     */
    public int getCodigoCobranca() {
        return codigoCobranca;
    }

    /**
     * @param codigoCobranca The codigoCobranca to set.
     */
    public void setCodigoCobranca(int codigoCobranca) {
        this.codigoCobranca = codigoCobranca;
    }

    /**
     * @return Returns the codigoDescCobranca.
     */
    public int getCodigoDescCobranca() {
        return codigoDescCobranca;
    }

    /**
     * @param codigoDescCobranca The codigoDescCobranca to set.
     */
    public void setCodigoDescCobranca(int codigoDescCobranca) {
        this.codigoDescCobranca = codigoDescCobranca;
    }

    /**
     * @return Returns the descricao.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao The descricao to set.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return Returns the valor.
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor The valor to set.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
