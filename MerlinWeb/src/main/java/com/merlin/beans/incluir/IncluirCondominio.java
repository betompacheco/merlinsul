package com.merlin.beans.incluir;

import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.managers.CondominioManager;

public class IncluirCondominio {

    private CondominioDTO condominio;
    private int codigocondominio;
    private String nome;
    private long qtd;
    private String mensagem;

    public void doIncluir() {
        CondominioManager cm = new CondominioManager();
        boolean novo = (codigocondominio == 0);
        if (novo) {
            condominio = cm.getNewBean();
        } else {
            condominio = cm.select(codigocondominio);
            condominio.setCodigocondominio(new Integer(codigocondominio));
        }
        condominio.setNomecondominio(nome);
        condominio.setQtdeapartamento(new Integer((int) qtd));
        if (novo) {
            cm.insert(condominio);
            // limpa a tela
            nome = "";
            qtd = 0;
            condominio = null;
            mensagem = "Condom�nio inserido com sucesso.";
        } else {
            cm.update(condominio);
            mensagem = "Condom�nio atualizado com sucesso.";
        }

    }

    public void setCondominio(CondominioDTO condominio) {
        this.condominio = condominio;
        this.nome = condominio.getNomecondominio();
        this.qtd = condominio.getQtdeapartamento().intValue();
        this.codigocondominio = condominio.getCodigocondominio().intValue();
    }

    public CondominioDTO getCondominio() {
        return condominio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getCodigocondominio() {
        return codigocondominio;
    }

    public void setCodigocondominio(int codigocondominio) {
        this.codigocondominio = codigocondominio;
    }
}
