package com.merlin.beans.incluir;

import com.merlin.data.dto.ProprietarioDTO;
import com.merlin.data.managers.ProprietarioManager;

public class IncluirProprietario {

	private ProprietarioDTO proprietario;
	private int codigoproprietario;
	private String nomeproprietario;
	private String sexo;
	private String identidade;
	private String cpf;
	private String profissao;
	private String filiacao;
	private String telResidencial;
	private String telComercial;
	private String telCelular;
	private String mensagem;

	public void doIncluir() {
		ProprietarioManager pm = new ProprietarioManager();
		boolean novo = (codigoproprietario == 0);
		if (novo) {
			proprietario = pm.getNewBean();
		} else {
			proprietario = pm.select(codigoproprietario);
			proprietario.setCodigoproprietario(new Integer(codigoproprietario));
		}
		proprietario.setNomeproprietario(nomeproprietario);
		proprietario.setSexo(sexo);
		proprietario.setIdentidade(identidade);
		proprietario.setCpf(cpf);
		proprietario.setProfissao(profissao);
		proprietario.setFiliacao(filiacao);
		proprietario.setTelResidencial(telResidencial);
		proprietario.setTelComercial(telComercial);
		proprietario.setTelCelular(telCelular);
		if (novo) {
			pm.insert(proprietario);
			// limpa a tela

			proprietario = null;
			mensagem = "Proprietário inserido com sucesso.";
		} else {
			pm.update(proprietario);
			mensagem = "Proprietário atualizado com sucesso.";
		}

	}

	public int getCodigoproprietario() {
		return codigoproprietario;
	}

	public String getCpf() {
		return cpf;
	}

	public String getFiliacao() {
		return filiacao;
	}

	public String getIdentidade() {
		return identidade;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getNomeproprietario() {
		return nomeproprietario;
	}

	public String getProfissao() {
		return profissao;
	}

	public ProprietarioDTO getProprietario() {
		return proprietario;
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

	public void setCodigoproprietario(int codigoproprietario) {
		this.codigoproprietario = codigoproprietario;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setNomeproprietario(String nomeproprietario) {
		this.nomeproprietario = nomeproprietario;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public void setProprietario(ProprietarioDTO proprietario) {
		this.proprietario = proprietario;
		this.codigoproprietario = proprietario.getCodigoproprietario().intValue();
		this.nomeproprietario = proprietario.getNomeproprietario();
		this.sexo = proprietario.getSexo();
		this.identidade = proprietario.getIdentidade();
		this.cpf = proprietario.getCpf();
		this.profissao = proprietario.getProfissao();
		this.filiacao = proprietario.getFiliacao();
		this.telResidencial = proprietario.getTelResidencial();
		this.telComercial = proprietario.getTelComercial();
		this.telCelular = proprietario.getTelCelular();
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
