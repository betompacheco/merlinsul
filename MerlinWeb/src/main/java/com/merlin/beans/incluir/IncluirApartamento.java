package com.merlin.beans.incluir;

import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.managers.ApartamentoManager;

public class IncluirApartamento {

    private ApartamentoDTO apartamento;
    private int codigocondominio;
    private int codigoproprietario;
    private int codigoapartamento;
    private int numeroapartamento;
    private String qtdeQuarto;
    private String mensagem;

    public void doIncluir() {
        ApartamentoManager am = new ApartamentoManager();
        boolean novo = (codigoapartamento == 0);
        if (novo) {
            apartamento = am.getNewBean();
        } else {
            apartamento = am.select(codigoapartamento);
            apartamento.setCodigoapartamento(new Integer(codigoapartamento));
        }
        apartamento.setCodigocondominio(new Integer(codigocondominio));
        apartamento.setCodigoproprietario(new Integer(codigoproprietario));
        apartamento.setNumeroapartamento(new Integer((int) numeroapartamento));
        apartamento.setQtdQuartos(new Integer(qtdeQuarto));
        if (am.existApartamento(numeroapartamento, codigocondominio)) {
            mensagem = "J� existe um apartamento com este n�mero para este condom�nio";
            return;
        }
        if (novo) {
            am.insert(apartamento);
            // limpa a tela
            codigocondominio = 0;
            codigoproprietario = 0;
            qtdeQuarto = null;
            numeroapartamento = 0;
            apartamento = null;
            mensagem = "Apartamento inserido com sucesso.";
        } else {
            am.update(apartamento);
            mensagem = "Apartamento atualizado com sucesso.";
        }
    }

    public ApartamentoDTO getApartamento() {
        return apartamento;
    }

    public void setApartamento(ApartamentoDTO apartamento) {
        this.apartamento = apartamento;

        this.codigocondominio = apartamento.getCodigocondominio().intValue();
        this.codigoproprietario = apartamento.getCodigoproprietario().intValue();
        this.codigoapartamento = apartamento.getCodigoapartamento().intValue();
        this.numeroapartamento = apartamento.getNumeroapartamento().intValue();
        this.qtdeQuarto = apartamento.getQtdQuartos().toString();
    }

    public int getCodigoApartamento() {
        return codigoapartamento;
    }

    public void setCodigoApartamento(int codigoApartamento) {
        this.codigoapartamento = codigoApartamento;
    }

    public int getCodigoCondominio() {
        return codigocondominio;
    }

    public void setCodigoCondominio(int codigoCondominio) {
        this.codigocondominio = codigoCondominio;
    }

    public int getCodigoProprietario() {
        return codigoproprietario;
    }

    public void setCodigoProprietario(int codigoProprietario) {
        this.codigoproprietario = codigoProprietario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getNumeroApartamento() {
        return numeroapartamento;
    }

    public void setNumeroApartamento(int numeroApartamento) {
        this.numeroapartamento = numeroApartamento;
    }

    public String getQtdeQuarto() {
        return qtdeQuarto;
    }

    public void setQtdeQuarto(String qtdeQuarto) {
        this.qtdeQuarto = qtdeQuarto;
    }
}
