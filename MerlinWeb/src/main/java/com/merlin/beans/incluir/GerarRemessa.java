package com.merlin.beans.incluir;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class GerarRemessa {

	private static final Logger logger = Logger.getLogger(GerarRemessa.class.getName());

	private String mes;
	private String ano;
	private boolean flagOk = false;

	public GerarRemessa() {
		// configura valoes padrao na pagina
		Calendar c = new GregorianCalendar();
		mes = Integer.toString(c.get(Calendar.MONTH));
		ano = Integer.toString(c.get(Calendar.YEAR));
	}

	public void doGerar() {
		flagOk = true;
	}

	/**
	 * @return the ano
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	public boolean isFlagOk() {
		return flagOk;
	}

	/**
	 * @param ano
	 *            the ano to set
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setFlagOk(boolean flagOk) {
		this.flagOk = flagOk;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

}
