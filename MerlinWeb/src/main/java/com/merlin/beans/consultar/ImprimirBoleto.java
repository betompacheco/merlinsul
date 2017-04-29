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
		// configura valoes padrao na pagina
		Calendar c = new GregorianCalendar();
		mes = Integer.toString(c.get(Calendar.MONTH));
		ano = Integer.toString(c.get(Calendar.YEAR));
	}

	public void doImprimir() {
		flagOk = true;
	}

	public String getAno() {
		return ano;
	}

	public String getCodigoApartamento() {
		return codigoApartamento;
	}

	public int getMensagem() {
		return mensagem;
	}

	public String getMes() {
		return mes;
	}

	public boolean isFlagOk() {
		return flagOk;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setCodigoApartamento(String codigoApartamento) {
		this.codigoApartamento = codigoApartamento;
	}

	public void setFlagOk(boolean flagOk) {
		this.flagOk = flagOk;
	}

	public void setMensagem(int mensagem) {
		this.mensagem = mensagem;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
}
