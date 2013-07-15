package com.merlin.data.dto;

public class VeiculoDTO {

    private Integer codigoApartamento;
    private Integer codigoVeiculo;
    private String tipoVeiculo;
    private String placaVeiculo;
    private String modeloVeiculo;
    private String fabricanteVeiculo;

    /**
     * @return Returns the codigoApartamento.
     */
    public Integer getCodigoApartamento() {
        return codigoApartamento;
    }

    /**
     * @param codigoApartamento The codigoApartamento to set.
     */
    public void setCodigoApartamento(Integer codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    /**
     * @return Returns the codigoVeiculo.
     */
    public Integer getCodigoVeiculo() {
        return codigoVeiculo;
    }

    /**
     * @param codigoVeiculo The codigoVeiculo to set.
     */
    public void setCodigoVeiculo(Integer codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    /**
     * @return Returns the fabricanteVeiculo.
     */
    public String getFabricanteVeiculo() {
        return fabricanteVeiculo;
    }

    /**
     * @param fabricanteVeiculo The fabricanteVeiculo to set.
     */
    public void setFabricanteVeiculo(String fabricanteVeiculo) {
        this.fabricanteVeiculo = fabricanteVeiculo;
    }

    /**
     * @return Returns the modeloVeiculo.
     */
    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    /**
     * @param modeloVeiculo The modeloVeiculo to set.
     */
    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    /**
     * @return Returns the placaVeiculo.
     */
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    /**
     * @param placaVeiculo The placaVeiculo to set.
     */
    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    /**
     * @return Returns the tipoVeiculo.
     */
    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    /**
     * @param tipoVeiculo The tipoVeiculo to set.
     */
    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
}
