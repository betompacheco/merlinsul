package com.merlin.beans.incluir;

import java.util.GregorianCalendar;

import com.merlin.data.dto.OrcamentoDTO;
import com.merlin.data.managers.OrcamentoManager;

public class IncluirOrcamento {

    private int codigoOrcamento;
    private String ano;
    private String mes;
    private double valorOrcamento;
    private String codigoServico;
    private double fundoReserva;
    private String mensagem;
    private OrcamentoDTO orcamento;
    private Integer codigoCondominio;
    private String descricao;

    public void doIncluir() {
        OrcamentoManager om = new OrcamentoManager();
        OrcamentoDTO orcamento = new OrcamentoDTO();
        boolean novo = (codigoOrcamento == 0);
        if (novo) {
            orcamento = om.getNewBean();
        } else {
            orcamento = om.select(codigoOrcamento);
            orcamento.setCodigoOrcamento(new Integer(codigoOrcamento));
        }
        orcamento.setCodigoCondominio(codigoCondominio);
        orcamento.setValorOrcamento(new Double(valorOrcamento));
        orcamento.setMes(new Integer(mes));
        orcamento.setAno(new Integer(ano));
        orcamento.setCodigoServico(new Integer(codigoServico));

        if ("1".equals(codigoServico)) {
            orcamento.setDescricaoServico("Cota");
        } else if ("2".equals(codigoServico)) {
            orcamento.setDescricaoServico("Cota Parte Comum");
        } else if ("3".equals(codigoServico)) {
            orcamento.setDescricaoServico(descricao);
        }
        orcamento.setFundoReserva(new Double(fundoReserva));

        if (novo) {
            om.insert(orcamento);
            // limpa a tela
            ano = "";
            mes = "";
            valorOrcamento = 0;
            codigoServico = "";
            fundoReserva = 0;

            mensagem = "Orcamento inserido com sucesso.";
        } else {
            om.update(orcamento);
            mensagem = "Orcamento atualizado com sucesso.";
        }

    }

    public String getAno() {
        if (ano == null) {
            GregorianCalendar gc = new GregorianCalendar();
            mes = Integer.toString(gc.get(GregorianCalendar.MONTH));
            ano = Integer.toString(gc.get(GregorianCalendar.YEAR));
        }

        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public double getFundoReserva() {
        return fundoReserva;
    }

    public void setFundoReserva(double fundoReserva) {
        this.fundoReserva = fundoReserva;
    }

    public String getMes() {
        if (mes == null) {
            GregorianCalendar gc = new GregorianCalendar();
            mes = Integer.toString(gc.get(GregorianCalendar.MONTH));
            ano = Integer.toString(gc.get(GregorianCalendar.YEAR));
        }
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public OrcamentoDTO getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(OrcamentoDTO orcamento) {
        this.orcamento = orcamento;
        this.ano = orcamento.getAno().toString();
        this.mes = orcamento.getMes().toString();
        this.codigoCondominio = orcamento.getCodigoCondominio();
        this.codigoOrcamento = orcamento.getCodigoOrcamento().intValue();
        this.valorOrcamento = orcamento.getValorOrcamento().doubleValue();
        this.fundoReserva = orcamento.getFundoReserva().doubleValue();
        this.codigoServico = orcamento.getCodigoServico().toString();
        this.descricao = orcamento.getDescricaoServico();
    }

    public int getCodigoOrcamento() {
        return codigoOrcamento;
    }

    public void setCodigoOrcamento(int codigoOrcamento) {
        this.codigoOrcamento = codigoOrcamento;
    }

    public Integer getCodigoCondominio() {
        return codigoCondominio;
    }

    public void setCodigoCondominio(Integer codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
