package com.merlin.beans.incluir;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.data.managers.CobrancaManager;
import com.merlin.util.GeradorDeCobranca;

public class GerarCobranca {

	private Date dataVencimento;
	private Double multa;
	private String mensagem;
	private List dadosGerados;
	private DataModel dados;
	private boolean flagConfirma;

	public GerarCobranca() {
		dataVencimento = new GregorianCalendar().getTime();
		multa = 2.0;
	}

	private void _gerar() {
		GeradorDeCobranca geradorDeCobranca = new GeradorDeCobranca();
		geradorDeCobranca.setDataVencimento(dataVencimento);
		geradorDeCobranca.setMulta(multa.doubleValue());
		geradorDeCobranca.gerarCobrancas();
		dadosGerados = geradorDeCobranca.getCobranca();
		dados = new ListDataModel(dadosGerados);
	}

	public void confirma() {
		_gerar();
	}

	private void doCancelar() {
		dataVencimento = null;
		multa = null;
		dadosGerados = null;
	}

	public void doGerar() {
		if (existeCobranca()) {
			flagConfirma = true;
		} else {
			_gerar();
		}

	}

	public void doSalvar() {
		CobrancaManager cm = new CobrancaManager();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataVencimento);
		int ano = gc.get(GregorianCalendar.YEAR);
		int mes = gc.get(GregorianCalendar.MONTH);
		cm.delete(ano, mes);
		cm.insert(dadosGerados);
		mensagem = "Cobranca armazenada";
		dados = null;
		dadosGerados = null;

	}

	private boolean existeCobranca() {
		CobrancaManager cm = new CobrancaManager();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataVencimento);
		int ano = gc.get(GregorianCalendar.YEAR);
		int mes = gc.get(GregorianCalendar.MONTH);
		List l = cm.select(ano, mes, -1);
		return l.size() > 0;
	}

	public DataModel getDados() {
		return dados;
	}

	public List getDadosGerados() {
		return dadosGerados;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Double getMulta() {
		return multa;
	}

	public boolean getTemDados() {
		if (dadosGerados == null) {
			return false;
		}
		return dadosGerados.size() > 0;
	}

	public boolean isFlagConfirma() {
		return flagConfirma;
	}

	public void setDados(DataModel dados) {
		this.dados = dados;
	}

	public void setDadosGerados(List dadosGerados) {
		this.dadosGerados = dadosGerados;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setFlagConfirma(boolean flagConfirma) {
		this.flagConfirma = flagConfirma;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}
}
