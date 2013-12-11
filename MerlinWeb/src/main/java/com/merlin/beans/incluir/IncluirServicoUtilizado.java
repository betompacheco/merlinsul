package com.merlin.beans.incluir;

import com.merlin.data.dto.ServicoUtilizadoDTO;
import com.merlin.data.managers.ServicoUtilizadoManager;
import java.util.Date;
import java.util.logging.Logger;

public class IncluirServicoUtilizado {

    private ServicoUtilizadoDTO servicoUtilizado;
    private int codigoServicoUtilizado;
    private int codigoApartamento;
    private int codigoServico;
    private Date dataUtilizacao;
    private String mensagem;

    private final static Logger logger = Logger.getLogger(IncluirServicoUtilizado.class.getName());

    public void doIncluir() {
        ServicoUtilizadoManager sm = new ServicoUtilizadoManager();
        // ServicoUtilizadoDTO servicoUtilizado = new ServicoUtilizadoDTO();
        boolean novo = (codigoServicoUtilizado == 0);
        if (novo) {
            servicoUtilizado = sm.getNewBean();
        } else {
            servicoUtilizado = sm.select(codigoServicoUtilizado);
            servicoUtilizado.setCodigoServicoUtilizado(new Integer(codigoServicoUtilizado));
        }
        servicoUtilizado.setCodigoServico(new Integer(codigoServico));
        servicoUtilizado.setCodigoApartamento(new Integer(codigoApartamento));
        servicoUtilizado.setDataUtilizacao(dataUtilizacao);

        if (novo) {
            sm.insert(servicoUtilizado);
            // limpa a tela

            servicoUtilizado = null;
            dataUtilizacao = null;
            codigoServico = 0;
            codigoApartamento = 0;
            mensagem = "Serviço inserido com sucesso.";
        } else {
            sm.update(servicoUtilizado);
            // limpa a tela

            servicoUtilizado = null;
            mensagem = "Serviço atualizado com sucesso.";
        }

    }

    public int getCodigoApartamento() {
        return codigoApartamento;
    }

    public void setCodigoApartamento(int codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public Date getDataUtilizacao() {
        return dataUtilizacao;
    }

    public void setDataUtilizacao(Date dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ServicoUtilizadoDTO getServicoUtilizado() {
        return servicoUtilizado;
    }

    public void setServicoUtilizado(ServicoUtilizadoDTO servicoUtilizado) {
        this.servicoUtilizado = servicoUtilizado;
        this.setCodigoServicoUtilizado(servicoUtilizado.getCodigoServicoUtilizado().intValue());
        this.setCodigoApartamento(servicoUtilizado.getCodigoApartamento().intValue());
        this.setCodigoServico(servicoUtilizado.getCodigoServico().intValue());
        this.setDataUtilizacao(servicoUtilizado.getDataUtilizacao());
    }

    public int getCodigoServicoUtilizado() {
        return codigoServicoUtilizado;
    }

    public void setCodigoServicoUtilizado(int codigoServicoUtilizado) {
        this.codigoServicoUtilizado = codigoServicoUtilizado;
    }
}
