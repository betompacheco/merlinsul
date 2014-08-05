package com.merlin.beans.consultar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ImprimirBoleto {

    private String ano;
    private String mes;
    private String codigoApartamento;
    private int mensagem;
    private boolean flagOk;

    public ImprimirBoleto() {
        //configura valoes padrao na pagina
        Calendar c = new GregorianCalendar();
        mes = Integer.toString(c.get(Calendar.MONTH));
        ano = Integer.toString(c.get(Calendar.YEAR));
    }

    public void doImprimir() {
        flagOk = true;
    }

    public String getCodigoApartamento() {
        return codigoApartamento;
    }

    public void setCodigoApartamento(String codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getMensagem() {
        return mensagem;
    }

    public void setMensagem(int mensagem) {
        this.mensagem = mensagem;
    }
}
