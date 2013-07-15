package com.merlin.data.dto;

public class OrcamentoDTO {

    private Integer codigoOrcamento;
    private Integer mes;
    private Integer ano;
    private Integer codigoCondominio;
    private Integer codigoServico;
    private Double valorOrcamento;
    private Double fundoReserva;
    private String descricaoServico;
    private CondominioDTO condominio;
    private String[] mesTexto = new String[]{"Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
        "Novembro", "Dezembro"};

    public CondominioDTO getCondominio() {
        return condominio;
    }

    public void setCondominio(CondominioDTO condominio) {
        this.condominio = condominio;
    }

    /**
     * @return Returns the ano.
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano The ano to set.
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * @return Returns the codigoCondominio.
     */
    public Integer getCodigoCondominio() {
        return codigoCondominio;
    }

    /**
     * @param codigoCondominio The codigoCondominio to set.
     */
    public void setCodigoCondominio(Integer codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
    }

    /**
     * @return Returns the codigoOrcamento.
     */
    public Integer getCodigoOrcamento() {
        return codigoOrcamento;
    }

    /**
     * @param codigoOrcamento The codigoOrcamento to set.
     */
    public void setCodigoOrcamento(Integer codigoOrcamento) {
        this.codigoOrcamento = codigoOrcamento;
    }

    /**
     * @return Returns the codigoServico.
     */
    public Integer getCodigoServico() {
        return codigoServico;
    }

    /**
     * @param codigoServico The codigoServico to set.
     */
    public void setCodigoServico(Integer codigoServico) {
        this.codigoServico = codigoServico;
    }

    /**
     * @return Returns the fundoReserva.
     */
    public Double getFundoReserva() {
        return fundoReserva;
    }

    /**
     * @param fundoReserva The fundoReserva to set.
     */
    public void setFundoReserva(Double fundoReserva) {
        this.fundoReserva = fundoReserva;
    }

    /**
     * @return Returns the mes.
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * @param mes The mes to set.
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * @return Returns the valorOrcamento.
     */
    public Double getValorOrcamento() {
        return valorOrcamento;
    }

    /**
     * @param valorOrcamento The valorOrcamento to set.
     */
    public void setValorOrcamento(Double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public String getMesTexto() {
        return mesTexto[mes.intValue()];

    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }
}
