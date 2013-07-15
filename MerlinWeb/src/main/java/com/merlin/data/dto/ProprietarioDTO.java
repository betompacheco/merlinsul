package com.merlin.data.dto;

public class ProprietarioDTO {

    private Integer codigoproprietario;
    private String nomeproprietario;
    private String sexo;
    private String identidade;
    private String cpf;
    private String profissao;
    private String filiacao;
    private String telResidencial;
    private String telComercial;
    private String telCelular;

    public Integer getCodigoproprietario() {
        return codigoproprietario;
    }

    public void setCodigoproprietario(Integer codigoproprietario) {
        this.codigoproprietario = codigoproprietario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getNomeproprietario() {
        return nomeproprietario;
    }

    public void setNomeproprietario(String nomeproprietario) {
        this.nomeproprietario = nomeproprietario;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getTelComercial() {
        return telComercial;
    }

    public void setTelComercial(String telComercial) {
        this.telComercial = telComercial;
    }

    public String getTelResidencial() {
        return telResidencial;
    }

    public void setTelResidencial(String telResidencial) {
        this.telResidencial = telResidencial;
    }
}
