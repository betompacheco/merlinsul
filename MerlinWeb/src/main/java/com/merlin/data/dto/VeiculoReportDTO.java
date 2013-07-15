package com.merlin.data.dto;

public class VeiculoReportDTO {

    private String nomeCondominio;
    private String numeroApartamento;
    private Integer codigoApartamento;
    private Integer codigoVeiculo;
    private String tipoVeiculo;
    private String placaVeiculo;
    private String modeloVeiculo;
    private String fabricanteVeiculo;

    public Integer getCodigoApartamento() {
        return codigoApartamento;
    }

    public void setCodigoApartamento(Integer codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    public Integer getCodigoVeiculo() {
        return codigoVeiculo;
    }

    public void setCodigoVeiculo(Integer codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public String getFabricanteVeiculo() {
        return fabricanteVeiculo;
    }

    public void setFabricanteVeiculo(String fabricanteVeiculo) {
        this.fabricanteVeiculo = fabricanteVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getNomeCondominio() {
        return nomeCondominio;
    }

    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
}
