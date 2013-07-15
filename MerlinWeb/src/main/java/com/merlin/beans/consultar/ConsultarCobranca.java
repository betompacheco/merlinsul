package com.merlin.beans.consultar;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.data.dto.CobrancaDTO;
import com.merlin.data.managers.CobrancaManager;

public class ConsultarCobranca {
    // campos para a consulta

    private int codigoApartamento;
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        CobrancaManager cm = new CobrancaManager();
        lista = cm.select(-1, -1, codigoApartamento);
        dados = new ListDataModel(lista);
    }

    public void doBaixar() {
        CobrancaDTO cobranca = (CobrancaDTO) lista.get(dados.getRowIndex());
        CobrancaManager cm = new CobrancaManager();
        cobranca.setBaixa(true);
        cobranca.setDataPagamento(new java.util.Date());
        cm.updateCobranca(cobranca);
        lista = cm.select(-1, -1, codigoApartamento);
        dados = new ListDataModel(lista);
    }

    public DataModel getDados() {
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }

    public int getCodigoApartamento() {
        return codigoApartamento;
    }

    public void setCodigoApartamento(int codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }
}
