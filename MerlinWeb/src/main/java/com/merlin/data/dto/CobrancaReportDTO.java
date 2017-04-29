package com.merlin.data.dto;

import java.util.Date;

public class CobrancaReportDTO {

	private Date dataEmissao;
	private Date dataVencimento;
	private double valorDocumento;
	private double valorDesconto;
	private double valorMulta;
	private double valorCobrado;
	private double valorPago;
	private boolean baixa;
	private Date dataPagamento;
	private String numeroApartamento;
	private String nomeCondominio;
	private String nomeProprietario;
	private String cpfCnpjProprietario;

	public String getCpfCnpjProprietario() {
		return cpfCnpjProprietario;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public String getNumeroApartamento() {
		return numeroApartamento;
	}

	public double getValorCobrado() {
		return valorCobrado;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public double getValorDocumento() {
		return valorDocumento;
	}

	public double getValorMulta() {
		return valorMulta;
	}

	public double getValorPago() {
		return valorPago;
	}

	public boolean isBaixa() {
		return baixa;
	}

	public void setBaixa(boolean baixa) {
		this.baixa = baixa;
	}

	public void setCpfCnpjProprietario(String cpfCnpjProprietario) {
		this.cpfCnpjProprietario = cpfCnpjProprietario;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public void setNumeroApartamento(String numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}

	public void setValorCobrado(double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public void setValorDocumento(double valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
}
