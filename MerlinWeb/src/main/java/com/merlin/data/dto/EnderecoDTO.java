package com.merlin.data.dto;

public class EnderecoDTO {

    private Integer codigoapartamento;
    private Integer codigoendereco;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private boolean enderecoCobranca;
    private ApartamentoDTO apartamento;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getCodigoapartamento() {
        return codigoapartamento;
    }

    public void setCodigoapartamento(Integer codigoapartamento) {
        this.codigoapartamento = codigoapartamento;
    }

    public Integer getCodigoendereco() {
        return codigoendereco;
    }

    public void setCodigoendereco(Integer codigoendereco) {
        this.codigoendereco = codigoendereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean isEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(boolean enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public ApartamentoDTO getApartamento() {
        return apartamento;
    }

    public void setApartamento(ApartamentoDTO apartamento) {
        this.apartamento = apartamento;
    }
}
