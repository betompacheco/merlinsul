package com.merlin.beans.incluir;

import com.merlin.data.dto.MensagemDTO;
import com.merlin.data.managers.MensagemManager;

public class IncluirMensagem {

    private MensagemDTO mensagemDTO;
    private int codigoMensagem;
    private String textoMensagem;
    private String mensagem;

    public String getTextoMensagem() {
        return textoMensagem;
    }

    public void setTextoMensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void doIncluir() {
        MensagemManager em = new MensagemManager();
        boolean novo = (codigoMensagem == 0);
        if (novo) {
            mensagemDTO = em.getNewBean();
        } else {
            mensagemDTO = em.select(codigoMensagem);
            mensagemDTO.setCodigoMensagem(new Integer(codigoMensagem));
        }

        mensagemDTO.setTextoMensagem(textoMensagem);
        if (novo) {
            em.insert(mensagemDTO);
            // limpa a tela
            mensagem = "Endere�o inserido com sucesso.";
        } else {
            em.update(mensagemDTO);
            mensagem = "Endere�o atualizado com sucesso.";
        }

    }

    public MensagemDTO getMensagemDTO() {
        return mensagemDTO;
    }

    public void setMensagemDTO(MensagemDTO Mensagem) {
        this.mensagemDTO = Mensagem;
        this.codigoMensagem = Mensagem.getCodigoMensagem().intValue();
        this.textoMensagem = Mensagem.getTextoMensagem();
    }

    public int getCodigoMensagem() {
        return codigoMensagem;
    }

    public void setCodigoMensagem(int codigoMensagem) {
        this.codigoMensagem = codigoMensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
