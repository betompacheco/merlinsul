package com.merlin.data.dto;

public class ApartamentoDTO {

    private Integer codigocondominio;
    private Integer codigoproprietario;
    private Integer codigoapartamento;
    private Integer numeroapartamento;
    private Integer qtdQuartos;
    private CondominioDTO condominio;

    public Integer getCodigoapartamento() {
        return codigoapartamento;
    }

    public void setCodigoapartamento(Integer codigoapartamento) {
        this.codigoapartamento = codigoapartamento;
    }

    public Integer getCodigocondominio() {
        return codigocondominio;
    }

    public void setCodigocondominio(Integer codigocondominio) {
        this.codigocondominio = codigocondominio;
    }

    public Integer getCodigoproprietario() {
        return codigoproprietario;
    }

    public void setCodigoproprietario(Integer codigoproprietario) {
        this.codigoproprietario = codigoproprietario;
    }

    public Integer getNumeroapartamento() {
        return numeroapartamento;
    }

    public void setNumeroapartamento(Integer numeroapartamento) {
        this.numeroapartamento = numeroapartamento;
    }

    public Integer getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(Integer qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public CondominioDTO getCondominio() {
        return condominio;
    }

    public void setCondominio(CondominioDTO condominio) {
        this.condominio = condominio;
    }
}
