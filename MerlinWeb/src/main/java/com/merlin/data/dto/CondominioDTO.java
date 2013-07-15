package com.merlin.data.dto;

public class CondominioDTO {

    private Integer codigocondominio;
    private String nomecondominio;
    private Integer qtdeapartamento;

    /**
     * @return Returns the codigocondominio.
     */
    public Integer getCodigocondominio() {
        return codigocondominio;
    }

    /**
     * @param codigocondominio The codigocondominio to set.
     */
    public void setCodigocondominio(Integer codigocondominio) {
        this.codigocondominio = codigocondominio;
    }

    /**
     * @return Returns the nomecondominio.
     */
    public String getNomecondominio() {
        return nomecondominio;
    }

    /**
     * @param nomecondominio The nomecondominio to set.
     */
    public void setNomecondominio(String nomecondominio) {
        this.nomecondominio = nomecondominio;
    }

    /**
     * @return Returns the qtdeapartamento.
     */
    public Integer getQtdeapartamento() {
        return qtdeapartamento;
    }

    /**
     * @param qtdeapartamento The qtdeapartamento to set.
     */
    public void setQtdeapartamento(Integer qtdeapartamento) {
        this.qtdeapartamento = qtdeapartamento;
    }
}
