package com.merlin.beans.incluir;

import com.merlin.data.dto.ProprietarioDTO;
import com.merlin.data.managers.ProprietarioManager;

public class IncluirProprietario {

    private ProprietarioDTO proprietario;
    private int codigoproprietario;
    private String nomeproprietario;
    private String sexo;
    private String identidade;
    private String cpf;
    private String profissao;
    private String filiacao;
    private String telResidencial;
    private String telComercial;
    private String telCelular;
    private String mensagem;

    public void doIncluir() {
        ProprietarioManager pm = new ProprietarioManager();
        boolean novo = (codigoproprietario == 0);
        if (novo) {
            proprietario = pm.getNewBean();
        } else {
            proprietario = pm.select(codigoproprietario);
            proprietario.setCodigoproprietario(new Integer(codigoproprietario));
        }
        proprietario.setNomeproprietario(nomeproprietario);
        proprietario.setSexo(sexo);
        proprietario.setIdentidade(identidade);
        proprietario.setCpf(cpf);
        proprietario.setProfissao(profissao);
        proprietario.setFiliacao(filiacao);
        proprietario.setTelResidencial(telResidencial);
        proprietario.setTelComercial(telComercial);
        proprietario.setTelCelular(telCelular);
        if (novo) {
            pm.insert(proprietario);
            // limpa a tela

            proprietario = null;
            mensagem = "Propriet�rio inserido com sucesso.";
        } else {
            pm.update(proprietario);
            mensagem = "Propriet�rio atualizado com sucesso.";
        }

    }

    public int getCodigoproprietario() {
        return codigoproprietario;
    }

    public void setCodigoproprietario(int codigoproprietario) {
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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

    public ProprietarioDTO getProprietario() {
        return proprietario;
    }

    public void setProprietario(ProprietarioDTO proprietario) {
        this.proprietario = proprietario;
        this.codigoproprietario = proprietario.getCodigoproprietario().intValue();
        this.nomeproprietario = proprietario.getNomeproprietario();
        this.sexo = proprietario.getSexo();
        this.identidade = proprietario.getIdentidade();
        this.cpf = proprietario.getCpf();
        this.profissao = proprietario.getProfissao();
        this.filiacao = proprietario.getFiliacao();
        this.telResidencial = proprietario.getTelResidencial();
        this.telComercial = proprietario.getTelComercial();
        this.telCelular = proprietario.getTelCelular();
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
