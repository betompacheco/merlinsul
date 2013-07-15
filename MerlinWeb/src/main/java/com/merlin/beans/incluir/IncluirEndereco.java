package com.merlin.beans.incluir;

import com.merlin.data.dto.EnderecoDTO;
import com.merlin.data.managers.EnderecoManager;

public class IncluirEndereco {

    private EnderecoDTO endereco;
    private int codigoapartamento;
    private int codigoendereco;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private boolean enderecoCobranca;
    private String mensagem;

    public void doIncluir() {
        EnderecoManager em = new EnderecoManager();
        boolean novo = (codigoendereco == 0);
        if (novo) {
            endereco = em.getNewBean();
        } else {
            endereco = em.select(codigoendereco);
            endereco.setCodigoendereco(new Integer(codigoendereco));
        }
        endereco.setCodigoapartamento(new Integer((int) codigoapartamento));
        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setCep(cep);
        endereco.setEnderecoCobranca(enderecoCobranca);
        if (novo) {
            em.insert(endereco);
            // limpa a tela
            mensagem = "Endere�o inserido com sucesso.";
        } else {
            em.update(endereco);
            mensagem = "Endere�o atualizado com sucesso.";
        }

    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
        this.codigoendereco = endereco.getCodigoendereco().intValue();
        this.codigoapartamento = endereco.getCodigoapartamento().intValue();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.cep = endereco.getCep();
        this.enderecoCobranca = endereco.isEnderecoCobranca();
    }

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

    public int getCodigoapartamento() {
        return codigoapartamento;
    }

    public void setCodigoapartamento(int codigoapartamento) {
        this.codigoapartamento = codigoapartamento;
    }

    public int getCodigoendereco() {
        return codigoendereco;
    }

    public void setCodigoendereco(int codigoendereco) {
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
