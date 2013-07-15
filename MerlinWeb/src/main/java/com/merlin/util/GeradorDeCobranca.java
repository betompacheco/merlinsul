package com.merlin.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class GeradorDeCobranca {

    private double multa;
    private Date dataVencimento;
    private ArrayList cobranca;
    private Logger logger = Logger.getLogger("GeradorDeCobranca");

    public void gerarCobrancas() {

        String[] cotas = new String[]{"Cota", "Cota parte Comum"};
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataVencimento);
        int ano = gc.get(GregorianCalendar.YEAR);
        int mes = gc.get(GregorianCalendar.MONTH);
        CondominioManager cm = new CondominioManager();
        ApartamentoManager am = new ApartamentoManager();
        OrcamentoManager om = new OrcamentoManager();

        List condominios = cm.select("");
        cobranca = new ArrayList();
        for (int i = 0; i < condominios.size(); i++) {
            CondominioDTO condominio = (CondominioDTO) condominios.get(i);
            // pega a lista de apartamentos do condominio
            List apartamentos = am.selectCondominio(condominio.getCodigocondominio().intValue());
            List orcamentos = om.select(ano, mes, condominio.getCodigocondominio().intValue(), -1);
            if (orcamentos.size() == 0) {
                continue;
            }

            // retorna um hashmap contendo o numero total de apartamenos separados por numero de quartos
            // ex: 3/405 2/204 (405 apartamentos de 3 quartos e 204 de 2 quartos)
            HashMap countQrtApto = countQuartosApartamentos(apartamentos);

            // calcula o total de quartos (3*405 + 2*204)
            int totQuartos = countTotalQuartos(countQrtApto);

            DescCobranca desc = null;
            for (int j = 0; j < apartamentos.size(); j++) {
                ApartamentoDTO apartamento = (ApartamentoDTO) apartamentos.get(j);
                apartamento.setCondominio(condominio);
                List servicos = getServicos(apartamento.getCodigoapartamento().intValue());
                List listaDesc = new ArrayList();
                double valor = 0;
                int codigoDescCobranca = 0;
                for (int m = 0; m < orcamentos.size(); m++) {
                    OrcamentoDTO orcamento = (OrcamentoDTO) orcamentos.get(m);
                    double valorPorQuarto = orcamento.getValorOrcamento().doubleValue() / totQuartos;
                    double valorOrcamento = (valorPorQuarto * apartamento.getQtdQuartos().intValue());
                    valor += valorOrcamento;
                    logger.log(Level.FINE, "Valor 1 : " + valor);

                    desc = new DescCobranca();
                    codigoDescCobranca++;
                    desc.setCodigoDescCobranca(codigoDescCobranca);
                    // Comentado por humberto
                    desc.setDescricao((new StringBuilder(String.valueOf(orcamento.getDescricaoServico()))).append(" ")
                            .append(apartamento.getQtdQuartos()).append(" quartos").toString());

                    desc.setValor(valorOrcamento);
                    listaDesc.add(desc);
                    if (orcamento.getFundoReserva().doubleValue() > 0) {
                        codigoDescCobranca++;
                        desc = new DescCobranca();
                        desc.setCodigoDescCobranca(codigoDescCobranca);
                        desc.setDescricao("Fundo Reserva " + apartamento.getQtdQuartos() + " quartos");
                        double vlReserva = (orcamento.getFundoReserva().doubleValue() / 100) * valorOrcamento;

                        desc.setValor(vlReserva);
                        valor += vlReserva;
                        logger.log(Level.FINE, "Valor 2 : " + valor);
                        listaDesc.add(desc);
                    }
                }
                double valorTotalServicos = 0;
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
                valor = ((double) ((int) (valor * 100))) / 100d;
                cob.setValorCobrado(valor);
                logger.log(Level.FINE, "Valor total : " + valor);
                cobranca.add(cob);
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

    private int countTotalQuartos(HashMap count) {
        int total = 0;
        Iterator iterator = count.keySet().iterator();
        while (iterator.hasNext()) {
            Integer quartos = (Integer) iterator.next();
            Integer nApartamentos = (Integer) count.get(quartos);
            total += quartos.intValue() * nApartamentos.intValue();
        }
        return total;
    }

    private List getServicos(int apartamento) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataVencimento);
        // gc.add(GregorianCalendar.MONTH, -1);
        ServicoUtilizadoManager sum = new ServicoUtilizadoManager();
        int mes = gc.get(GregorianCalendar.MONTH);
        int ano = gc.get(GregorianCalendar.YEAR);
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
}
