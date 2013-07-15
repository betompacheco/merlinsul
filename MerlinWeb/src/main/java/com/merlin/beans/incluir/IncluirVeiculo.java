/*
 * Created on 11/02/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.merlin.beans.incluir;

import com.merlin.data.dto.VeiculoDTO;
import com.merlin.data.managers.VeiculoManager;

/**
 * @author Leonardo Lopes
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IncluirVeiculo {

    private VeiculoDTO veiculo;
    private int codigoVeiculo;
    private int codigoApartamento;
    private String tipoVeiculo;
    private String placaVeiculo;
    private String modeloVeiculo;
    private String fabricanteVeiculo;
    private String mensagem;

    public void doIncluir() {
        VeiculoManager vm = new VeiculoManager();
        boolean novo = (codigoVeiculo == 0);

        if (novo) {
            veiculo = vm.getNewBean();
        } else {
            veiculo = vm.select(codigoVeiculo);
            veiculo.setCodigoVeiculo(new Integer(codigoVeiculo));
        }
        veiculo.setCodigoApartamento(new Integer(codigoApartamento));
        veiculo.setTipoVeiculo(tipoVeiculo);
        veiculo.setPlacaVeiculo(placaVeiculo);
        veiculo.setModeloVeiculo(modeloVeiculo);
        veiculo.setFabricanteVeiculo(fabricanteVeiculo);

        if (novo) {
            vm.insert(veiculo);
            // limpa a tela

            veiculo = null;
            mensagem = "Veiculo inserido com sucesso.";
        } else {
            vm.update(veiculo);

            mensagem = "Veiculo atualizado com sucesso.";
        }
    }

    public void setVeiculo(VeiculoDTO veiculo) {
        this.veiculo = veiculo;
        this.codigoVeiculo = veiculo.getCodigoVeiculo().intValue();
        this.codigoApartamento = veiculo.getCodigoApartamento().intValue();
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.placaVeiculo = veiculo.getPlacaVeiculo();
        this.modeloVeiculo = veiculo.getModeloVeiculo();
        this.fabricanteVeiculo = veiculo.getFabricanteVeiculo();
    }

    /**
     * @return Returns the codigoApartamento.
     */
    public int getCodigoApartamento() {
        return codigoApartamento;
    }

    /**
     * @param codigoApartamento The codigoApartamento to set.
     */
    public void setCodigoApartamento(int codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    /**
     * @return Returns the codigoVeiculo.
     */
    public int getCodigoVeiculo() {
        return codigoVeiculo;
    }

    /**
     * @param codigoVeiculo The codigoVeiculo to set.
     */
    public void setCodigoVeiculo(int codigoVeiculo) {
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

    /**
     * @return Returns the veiculo.
     */
    public VeiculoDTO getVeiculo() {
        return veiculo;
    }
}
