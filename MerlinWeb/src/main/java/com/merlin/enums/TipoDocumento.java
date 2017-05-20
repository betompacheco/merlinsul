package com.merlin.enums;

public enum TipoDocumento {

	CPF("01", "CPF"), CNPJ("02", "CNPJ"), PISPASEP("03", "PIS / PASEP"), NAOPOSSUI("98", "Não Possui"), OUTROS("99", "Outros");

	private String codigo;
	private String descricao;

	private TipoDocumento(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
