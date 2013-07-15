package com.merlin.data.dto;

import java.util.Date;

public class ServicoUtilizadoDTO {

    private Integer codigoServicoUtilizado;
    private Integer codigoProprietario;
    private Integer codigoApartamento;
    private Integer codigoServico;
    private Date dataUtilizacao;
    private ServicoDTO servico;

    public Integer getCodigoApartamento() {
        return codigoApartamento;
    }

    public void setCodigoApartamento(Integer codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    public Integer getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(Integer codigoServico) {
        this.codigoServico = codigoServico;
    }

    public Date getDataUtilizacao() {
        return dataUtilizacao;
    }

    public void setDataUtilizacao(Date dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    public ServicoDTO getServico() {
        return servico;
    }

    public void setServico(ServicoDTO servico) {
        this.servico = servico;
    }

    public Integer getCodigoProprietario() {
        return codigoProprietario;
    }

    public void setCodigoProprietario(Integer codigoProprietario) {
        this.codigoProprietario = codigoProprietario;
    }

    public Integer getCodigoServicoUtilizado() {
        return codigoServicoUtilizado;
    }

    public void setCodigoServicoUtilizado(Integer codigoServicoUtilizado) {
        this.codigoServicoUtilizado = codigoServicoUtilizado;
    }
}
