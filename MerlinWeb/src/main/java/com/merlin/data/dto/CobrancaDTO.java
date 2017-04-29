package com.merlin.data.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.merlin.data.managers.EnderecoManager;
import com.merlin.data.managers.ProprietarioManager;
import com.merlin.util.Config;
import com.merlin.util.NumberCodeGenerator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CobrancaDTO {

	private int codigoCobranca;
	private Date dataEmissao;
	private Date dataVencimento;
	private double valorDocumento;
	private double valorDesconto;
	private double valorMulta;
	private double valorCobrado;
	private double valorPago;
	private boolean baixa;
	private Date dataPagamento;
	private int codigoMensagen = 1;
	private int codigoApartamento;
	private List descricao;
	private int numeroApartamento;
	private String nomeCondominio;
	private String nomeSacado;
	private String cpfCnpjSacado;
	private String endereco;
	private String digitavel;
	private String codigoBarras;
	private String codigoDocumento;// Nosso Numero
	private NumberFormat nm;
	private SimpleDateFormat df;
	private ApartamentoDTO apartamento;

	public void calc() {
		nm = NumberFormat.getNumberInstance();
		nm.setGroupingUsed(true);
		nm.setMaximumFractionDigits(2);
		nm.setMinimumFractionDigits(2);
		df = new SimpleDateFormat("dd/MM/yyyy");

		NumberCodeGenerator ncg = new NumberCodeGenerator();
		ncg.setAgencia(Config.AGENCIA);
		ncg.setCarteira(Config.CARTEIRA);
		ncg.setConta(Config.CONTA);
		ncg.setNumeroBanco(Config.BANCO);
		ncg.setNumeroMoeda(Config.MOEDA);
		ncg.setProcessamento(new Date());
		ncg.setValor(getValorCobrado());
		ncg.setVencimento(getDataVencimento());
		ncg.setNossoNumero(Integer.toString(getCodigoCobranca()));
		ncg.generate();

		digitavel = ncg.getCodigoImpresso();
		codigoBarras = ncg.getCodigoBarras();

		ProprietarioManager pm = new ProprietarioManager();
		EnderecoManager em = new EnderecoManager();
		ProprietarioDTO proprietario = pm.select(apartamento.getCodigoproprietario().intValue());
		EnderecoDTO enderecoDTO = em.selectApartamento(apartamento.getCodigoapartamento());
		if (proprietario != null) {
			nomeSacado = proprietario.getNomeproprietario();
			cpfCnpjSacado = proprietario.getCpf();
		}

		if (enderecoDTO != null) {
			endereco = enderecoDTO.getLogradouro() + "\n";
			endereco += "CEP: " + enderecoDTO.getCep() + " " + enderecoDTO.getBairro() + " " + enderecoDTO.getCidade()
					+ " " + enderecoDTO.getUf();
		}
	}

	public ApartamentoDTO getApartamento() {
		return apartamento;
	}

	/**
	 * @return Returns the codigoApartamento.
	 */
	public int getCodigoApartamento() {
		return codigoApartamento;
	}

	/**
	 * @return Returns the codigoBarras.
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @return Returns the codigoCobranca.
	 */
	public int getCodigoCobranca() {
		return codigoCobranca;
	}

	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	/**
	 * @return Returns the codigoMensagen.
	 */
	public int getCodigoMensagen() {
		return codigoMensagen;
	}

	public String getCpfCnpjSacado() {
		return cpfCnpjSacado;
	}

	/**
	 * @return Returns the dataEmissao.
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * @return Returns the dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public JRBeanCollectionDataSource getDataSource() {
		return new JRBeanCollectionDataSource(descricao);
	}

	/**
	 * @return Returns the dataVencimento.
	 */
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public String getDataVencimentoMaisMes() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataVencimento);
		gc.add(GregorianCalendar.MONTH, +1);
		return df.format(gc.getTime());
	}

	public String getDataVencimentoMenos10() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataVencimento);
		gc.add(GregorianCalendar.DATE, -10);
		return df.format(gc.getTime());
	}

	public String getDataVencimentoMenos9() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataVencimento);
		gc.add(GregorianCalendar.DATE, -9);
		return df.format(gc.getTime());
	}

	public String getDataVencimentoTexto() {
		return df.format(dataVencimento);
	}

	/**
	 * @return Returns the descricao.
	 */
	public List getDescricao() {
		return descricao;
	}

	/**
	 * @return Returns the digitavel.
	 */
	public String getDigitavel() {
		return digitavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getMultaTexto() {
		return nm.format(valorMulta);
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public String getNomeSacado() {
		return nomeSacado;
	}

	public String getNossoNumero() {
		DecimalFormat dc = new DecimalFormat("000000000000");
		return dc.format(codigoCobranca);
	}

	public int getNumeroApartamento() {
		return numeroApartamento;
	}

	/**
	 * @return Returns the valorCobrado.
	 */
	public double getValorCobrado() {
		return valorCobrado;
	}

	public String getValorCobradoMenos10() {
		return nm.format(valorCobrado * (1 - 0.2));
	}

	public String getValorCobradoTexto() {
		return nm.format(valorCobrado);
	}

	/**
	 * @return Returns the valorDesconto.
	 */
	public double getValorDesconto() {
		return valorDesconto;
	}

	/**
	 * @return Returns the valorDocumento.
	 */
	public double getValorDocumento() {
		return valorDocumento;
	}

	/**
	 * @return Returns the valorMulta.
	 */
	public double getValorMulta() {
		return valorMulta;
	}

	/**
	 * @return Returns the valorPago.
	 */
	public double getValorPago() {
		return valorPago;
	}

	/**
	 * @return Returns the baixa.
	 */
	public boolean isBaixa() {
		return baixa;
	}

	public void setApartamento(ApartamentoDTO apartamento) {
		this.apartamento = apartamento;
	}

	/**
	 * @param baixa
	 *            The baixa to set.
	 */
	public void setBaixa(boolean baixa) {
		this.baixa = baixa;
	}

	/**
	 * @param codigoApartamento
	 *            The codigoApartamento to set.
	 */
	public void setCodigoApartamento(int codigoApartamento) {
		this.codigoApartamento = codigoApartamento;
	}

	/**
	 * @param codigoCobranca
	 *            The codigoCobranca to set.
	 */
	public void setCodigoCobranca(int codigoCobranca) {
		this.codigoCobranca = codigoCobranca;
	}

	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	/**
	 * @param codigoMensagen
	 *            The codigoMensagen to set.
	 */
	public void setCodigoMensagen(int codigoMensagen) {
		this.codigoMensagen = codigoMensagen;
	}

	/**
	 * @param dataEmissao
	 *            The dataEmissao to set.
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @param dataPagamento
	 *            The dataPagamento to set.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @param dataVencimento
	 *            The dataVencimento to set.
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	/**
	 * @param descricao
	 *            The descricao to set.
	 */
	public void setDescricao(List descricao) {
		this.descricao = descricao;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public void setNumeroApartamento(int numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}

	/**
	 * @param valorCobrado
	 *            The valorCobrado to set.
	 */
	public void setValorCobrado(double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	/**
	 * @param valorDesconto
	 *            The valorDesconto to set.
	 */
	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	/**
	 * @param valorDocumento
	 *            The valorDocumento to set.
	 */
	public void setValorDocumento(double valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * @param valorMulta
	 *            The valorMulta to set.
	 */
	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

	/**
	 * @param valorPago
	 *            The valorPago to set.
	 */
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
}
