// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 5/6/2009 19:56:40
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   GeradorDeCobranca.java
package com.merlin.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.dto.CobrancaDTO;
import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.dto.DescCobranca;
import com.merlin.data.dto.OrcamentoDTO;
import com.merlin.data.dto.ServicoUtilizadoDTO;
import com.merlin.data.managers.ApartamentoManager;
import com.merlin.data.managers.CondominioManager;
import com.merlin.data.managers.OrcamentoManager;
import com.merlin.data.managers.ServicoUtilizadoManager;

public class GeradorDeCobrancaold {

    public GeradorDeCobrancaold() {
    }

    public void gerar() {
        String cotas[] = {"Cota", "Cota parte Comum"};
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataVencimento);
        int ano = gc.get(1);
        int mes = gc.get(2);
        CondominioManager cm = new CondominioManager();
        ApartamentoManager am = new ApartamentoManager();
        OrcamentoManager om = new OrcamentoManager();
        List condominios = cm.select("");
        cobranca = new ArrayList();
        for (int i = 0; i < condominios.size(); i++) {
            CondominioDTO condominio = (CondominioDTO) condominios.get(i);
            List apartamentos = am.selectCondominio(condominio.getCodigocondominio().intValue());
            List orcamentos = om.select(ano, mes, condominio.getCodigocondominio().intValue(), -1);
            if (orcamentos.size() != 0) {
                HashMap countQrtApto = countQuartosApartamentos(apartamentos);
                int totQuartos = countTotQuartos(countQrtApto);
                DescCobranca desc = null;
                for (int j = 0; j < apartamentos.size(); j++) {
                    ApartamentoDTO apartamento = (ApartamentoDTO) apartamentos.get(j);
                    apartamento.setCondominio(condominio);
                    List servicos = getServicos(apartamento.getCodigoapartamento().intValue());
                    List listaDesc = new ArrayList();
                    double valor = 0.0D;
                    int codigoDescCobranca = 0;
                    for (int m = 0; m < orcamentos.size(); m++) {
                        OrcamentoDTO orcamento = (OrcamentoDTO) orcamentos.get(m);
                        double valorPorQuarto = orcamento.getValorOrcamento().doubleValue() / (double) totQuartos;
                        double valorOrcamento = valorPorQuarto * (double) apartamento.getQtdQuartos().intValue();
                        valor += valorOrcamento;
                        desc = new DescCobranca();
                        codigoDescCobranca++;
                        desc.setCodigoDescCobranca(codigoDescCobranca);
                        desc.setDescricao((new StringBuilder(String.valueOf(orcamento.getDescricaoServico()))).append(" ").append(apartamento.getQtdQuartos()).append(" quartos").toString());
                        desc.setValor(valorOrcamento);
                        listaDesc.add(desc);
                        if (orcamento.getFundoReserva().doubleValue() > 0.0D) {
                            codigoDescCobranca++;
                            desc = new DescCobranca();
                            desc.setCodigoDescCobranca(codigoDescCobranca);
                            desc.setDescricao((new StringBuilder("Fundo Reserva ")).append(apartamento.getQtdQuartos()).append(" quartos").toString());
                            double vlReserva = (orcamento.getFundoReserva().doubleValue() / 100D) * valorOrcamento;
                            desc.setValor(vlReserva);
                            valor += vlReserva;
                            System.out.println((new StringBuilder("Valor 2:")).append(valor).toString());
                            listaDesc.add(desc);
                        }
                    }

                    double valorTotalServicos = 0.0D;
                    for (int k = 0; k < servicos.size(); k++) {
                        ServicoUtilizadoDTO servico = (ServicoUtilizadoDTO) servicos.get(k);
                        desc = new DescCobranca();
                        codigoDescCobranca++;
                        desc.setCodigoDescCobranca(codigoDescCobranca);
                        desc.setDescricao(servico.getServico().getNomeServico());
                        valorTotalServicos += servico.getServico().getValorServico();
                        desc.setValor(servico.getServico().getValorServico());
                        listaDesc.add(desc);
                    }

                    valor += valorTotalServicos;
                    CobrancaDTO cob = new CobrancaDTO();
                    cob.setApartamento(apartamento);
                    cob.setDescricao(listaDesc);
                    cob.setValorMulta(multa);
                    cob.setCodigoApartamento(apartamento.getCodigoapartamento().intValue());
                    cob.setDataVencimento(dataVencimento);
                    cob.setDataEmissao(new Date());
                    valor = (double) (int) (valor * 100D) / 100D;
                    cob.setValorCobrado(valor);
                    System.out.println((new StringBuilder("Valor total")).append(valor).toString());
                    cobranca.add(cob);
                }

            }
        }

    }

    private HashMap countQuartosApartamentos(List apartamentos) {
        HashMap count = new HashMap();
        for (int i = 0; i < apartamentos.size(); i++) {
            ApartamentoDTO apartamento = (ApartamentoDTO) apartamentos.get(i);
            Integer qtd = apartamento.getQtdQuartos();
            Integer n = (Integer) count.get(qtd);
            if (n == null) {
                n = new Integer(1);
            } else {
                n = new Integer(n.intValue() + 1);
            }
            count.put(qtd, n);
        }

        return count;
    }

    private int countTotQuartos(HashMap count) {
        int total = 0;
        for (Iterator iterator = count.keySet().iterator(); iterator.hasNext();) {
            Integer quartos = (Integer) iterator.next();
            Integer nApartamentos = (Integer) count.get(quartos);
            total += quartos.intValue() * nApartamentos.intValue();
        }

        return total;
    }

    private List getServicos(int apartamento) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataVencimento);
        ServicoUtilizadoManager sum = new ServicoUtilizadoManager();
        int mes = gc.get(2);
        int ano = gc.get(1);
        return sum.selectPorApartamento(apartamento, mes, ano);
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public ArrayList getCobranca() {
        return cobranca;
    }

    public void setCobranca(ArrayList cobranca) {
        this.cobranca = cobranca;
    }
    private double multa;
    private Date dataVencimento;
    private ArrayList cobranca;
}
