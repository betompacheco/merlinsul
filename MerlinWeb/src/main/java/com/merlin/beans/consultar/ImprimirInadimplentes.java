package com.merlin.beans.consultar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import com.merlin.data.managers.CobrancaManager;

public class ImprimirInadimplentes {

    private int codigoapartamento;
    private String ano;
    private String mes;
    private boolean flagOk = false;

    public void doImprimir() {
        CobrancaManager cm = new CobrancaManager();
        int iapto = 0;
        int iano = -1;
        int imes = -1;

        // try { iapto = Integer.parseInt(codigoapartamento); } catch (Exception e) {}
        try {
            iano = Integer.parseInt(ano);
        } catch (Exception e) {
        }
        try {
            imes = Integer.parseInt(mes);
        } catch (Exception e) {
        }

        List cobrancas = cm.selectReportInadimplentes(iano, imes, codigoapartamento);

        FacesContext fc = FacesContext.getCurrentInstance();
        List rpt = new ArrayList();
        rpt.add("inadimplentes.jrxml"); // nome do rpt
        rpt.add(cobrancas); // datasource
        rpt.add(new HashMap()); // parametros do rpt
        fc.getExternalContext().getSessionMap().put("relatorio", rpt);
        flagOk = true;

    }

    public boolean isFlagOk() {
        return flagOk;
    }

    public void setFlagOk(boolean flagOk) {
        this.flagOk = flagOk;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getCodigoapartamento() {
        return codigoapartamento;
    }

    public void setCodigoapartamento(int codigoapartamento) {
        this.codigoapartamento = codigoapartamento;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
