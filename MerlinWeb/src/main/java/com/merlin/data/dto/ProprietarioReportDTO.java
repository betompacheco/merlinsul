package com.merlin.data.dto;

public class ProprietarioReportDTO extends ProprietarioDTO {

    private String nomeCondominio;
    private String numeroApartamento;

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
}
