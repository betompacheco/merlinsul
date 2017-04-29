package com.merlin.data.dto;

public class ProprietarioDTO {

	private Integer codigoproprietario;
	private String nomeproprietario;
	private String sexo;
	private String identidade;
	private String cpfCnpj;
	private String profissao;
	private String filiacao;
	private String telResidencial;
	private String telComercial;
	private String telCelular;

	public Integer getCodigoproprietario() {
		return codigoproprietario;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getFiliacao() {
		return filiacao;
	}

	public String getIdentidade() {
		return identidade;
	}

	public String getNomeproprietario() {
		return nomeproprietario;
	}

	public String getProfissao() {
		return profissao;
	}

	public String getSexo() {
		return sexo;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public String getTelComercial() {
		return telComercial;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setCodigoproprietario(Integer codigoproprietario) {
		this.codigoproprietario = codigoproprietario;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public void setNomeproprietario(String nomeproprietario) {
		this.nomeproprietario = nomeproprietario;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public void setTelComercial(String telComercial) {
		this.telComercial = telComercial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}
}
