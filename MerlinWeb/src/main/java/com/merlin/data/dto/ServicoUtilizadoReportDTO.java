package com.merlin.data.dto;

import java.util.Date;

public class ServicoUtilizadoReportDTO {

    private String nomeCondominio;
    private String numeroApartamento;
    private String nomeProprietario;
    private String nomeServico;
    private Date dataUtilizacao;

    public Date getDataUtilizacao() {
        return dataUtilizacao;
    }

    public String getNomeCondominio() {
        return nomeCondominio;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setDataUtilizacao(Date dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }
}
