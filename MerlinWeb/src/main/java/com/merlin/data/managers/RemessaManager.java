/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.data.managers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.merlin.data.DataBase;
import com.merlin.data.dto.RemessaDTO;
import com.merlin.enums.TipoDocumento;
import com.merlin.util.NumberCodeGenerator;
import com.merlin.util.Utilitario;
import com.merlin.util.Utilitario.Direcao;

public class RemessaManager {

	private static final Logger logger = Logger.getLogger(RemessaManager.class.getName());

	private final int ano;
	private final int mes;

	private final String datePattern = "ddMMyy";
	private final String DELIMITADOR_REGISTRO = "\r\n";

	public RemessaManager() {
		this.ano = 0;
		this.mes = 0;
	}

	public RemessaManager(int ano, int mes, boolean isForTest) {
		this.ano = ano;
		this.mes = mes;
	}

	public RemessaDTO getNewBean() {
		RemessaDTO remessa = new RemessaDTO();
		remessa.setCodigoRemessa(1);
		return remessa;
	}

	public String montaRemessa() {
		try {
			Connection con = DataBase.getConnection();
			String qry;
			PreparedStatement st = null;
			ResultSet rs = null;

			// Obtem o indice
			int numeroSequencialRemessa = 0;
			qry = "SELECT numeroremessa FROM remessa";
			Statement sta = con.createStatement();
			rs = sta.executeQuery(qry);
			if (rs.next()) {
				numeroSequencialRemessa = rs.getInt("numeroremessa");
			}

			// Consulta as cobrancas
			qry = "SELECT c.*, p.*, e.* FROM proprietario AS p, apartamento AS a, cobranca AS c, endereco AS e WHERE p.codigoproprietario = a.codigoproprietario AND a.codigoapartamento = c.codigoapartamento AND a.codigoapartamento = e.codigoapartamento AND c.datavencimento BETWEEN ? AND ? ORDER BY codigocobranca, nomeproprietario";
			st = con.prepareStatement(qry);

			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, ano);
			c.set(Calendar.MONTH, mes);
			int diaInicioMes = c.getActualMinimum(Calendar.DAY_OF_MONTH);
			int diaFinalMes = c.getActualMaximum(Calendar.DAY_OF_MONTH);

			st.setDate(1, new Date(new GregorianCalendar(ano, mes, diaInicioMes).getTimeInMillis()));
			st.setDate(2, new Date(new GregorianCalendar(ano, mes, diaFinalMes).getTimeInMillis()));
			rs = st.executeQuery();

			// Verifica se existem dados de cobranca
			if (!rs.isBeforeFirst()) {
				return "Nao existem dados a serem colocados na remessa.";
			}

			StringBuilder linha = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat();

			int sequencialRegistro = 1;

			// Escreve o Header Label
			logger.log(Level.INFO, "Escrevendo o header.");
			linha.append("0"); // Identificacao do registro
			linha.append("1");// Identificador do arquivo remessa
			linha.append("REMESSA");// Literal Remessa
			linha.append("01");// Codigo de servico
			linha.append(Utilitario.complete("COBRANCA", 15, " ", Direcao.ESQUERDA));// Literal Serviço
			linha.append(Utilitario.complete("4631965", 20, "0", Direcao.DIREITA));// Codigo da empresa
			linha.append(Utilitario.complete("CONDOMINIO EDIFICIO MERLIN SUL", 30, " ", Direcao.ESQUERDA));// Nome da empresa
			linha.append("237"); // Numero do Bradesco na camara de compensacao
			linha.append(Utilitario.complete("BRADESCO", 15, " ", Direcao.ESQUERDA));// Nome do banco por extenso

			sdf.applyPattern(datePattern);
			linha.append(sdf.format(new GregorianCalendar().getTime()));// Data da Gravacao do arquivo

			linha.append(Utilitario.complete("", 8, " ", Direcao.ESQUERDA));// Branco
			linha.append("MX"); // Identificacao do sistema
			linha.append(Utilitario.complete(Integer.toString(numeroSequencialRemessa), 7, "0", Direcao.DIREITA));// Numero sequencial da remessa
			linha.append(Utilitario.complete("", 277, " ", Direcao.ESQUERDA));// Branco
			linha.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0", Direcao.DIREITA));// Número sequencial do registro de um em um

			// Registro no final de cada registro, (ODOA)
			linha.append(DELIMITADOR_REGISTRO);

			logger.log(Level.INFO, "Quantidade de caracteres...:{0}", linha.toString().getBytes().length);

			// Escreve os registros de transações
			logger.log(Level.INFO, "Escrevendo os registros.");

			while (rs.next()) {
				linha.append("1");// Identificacao do registro
				linha.append(Utilitario.complete("", 5, "0", Direcao.ESQUERDA)); // Agencia de debito (Opcional)
				linha.append(Utilitario.complete("", 1, "0", Direcao.ESQUERDA)); // Digito da agencia de debito (Opcional)
				linha.append(Utilitario.complete("", 5, "0", Direcao.ESQUERDA)); // Razao da conta corrente (Opcional)
				linha.append(Utilitario.complete("", 7, "0", Direcao.ESQUERDA)); // Conta corrente (Opcional)
				linha.append(Utilitario.complete("", 1, "0", Direcao.ESQUERDA)); // Digito da conta corrente (Opcional)

				// Identificações da Empresa Beneficiária no Banco
				linha.append("0"); // 21 a 21 - Zero
				linha.append("009"); // 22 a 24 - códigos da carteira
				linha.append("02785"); // 25 a 29 - códigos da Agência Beneficiários, sem o dígito
				linha.append("0007636"); // 30 a 36 - Contas Corrente
				linha.append("8"); // 37 a 37 - dígitos da Conta

				linha.append(Utilitario.complete("", 25, " ", Direcao.ESQUERDA)); // Numero de controle do participante (Uso da empresa)
				linha.append("000"); // Codigo do banco a ser debitado na camara de compensacao
				linha.append("2"); // Campo de multa, 2 se condiderar multa, 0 se sem multa
				linha.append(Utilitario.complete(String.format(Locale.ENGLISH, "%02d", rs.getInt("valormulta")), 4, "0", Direcao.ESQUERDA)); // Percentual de multa
				linha.append(Utilitario.complete(rs.getString("codigocobranca"), 11, "0", Direcao.DIREITA));// Identificacao do titulo no banco

				// Gera o DAC de cada um dos "nosso numero"
				NumberCodeGenerator ncg = new NumberCodeGenerator();
				linha.append((String) ncg.comporNossoNumero(rs.getLong("codigocobranca"), "09").subSequence(15, 16));// Digito de auto conferencia do numero bancario

				linha.append(Utilitario.complete("", 10, "0", Direcao.ESQUERDA));// Desconto de bonificacao por dia
				linha.append("2");// Condicao de emissao da papeleta de cobranca
				linha.append("N");// Ident se emite boleto para debito automatico
				linha.append(Utilitario.complete("", 10, " ", Direcao.ESQUERDA));// Identifcacao da operacao do banco (Brancos)
				linha.append(" ");// Indicador rateio credito (opcional)
				linha.append(" ");// Enderecamento para aviso de debito automatico em conta corrente (opcional)
				linha.append(Utilitario.complete("", 2, " ", Direcao.ESQUERDA));// Branco
				linha.append(Utilitario.complete("01", 2, " ", Direcao.ESQUERDA));// Identificacao da ocorrencia
				linha.append(Utilitario.complete(rs.getString("codigocobranca"), 10, "0", Direcao.DIREITA));// Numero do documento

				sdf.applyPattern(datePattern);
				linha.append(sdf.format(rs.getDate("datavencimento"))); // Data de vencimento do titulo

				// Valor do titulo
				DecimalFormat format = new DecimalFormat();
				format.setMaximumFractionDigits(2);
				format.setMinimumFractionDigits(2);
				linha.append(Utilitario.complete(format.format(rs.getDouble("valorcobrado")).replace(".", "").replace(",", ""), 13, "0", Direcao.DIREITA));

				linha.append(Utilitario.complete("000", 3, "0", Direcao.ESQUERDA)); // Banco encarregado da cobranca (Preencher com zeros)
				linha.append(Utilitario.complete("", 5, "0", Direcao.ESQUERDA)); // Agencia depositaria (Preencher com zeros)
				linha.append("99"); // Especie do titulo
				linha.append("N"); // Identificacao
				sdf.applyPattern(datePattern);
				linha.append(sdf.format(rs.getDate("dataemissao"))); // Data de emissao do titulo
				linha.append(Utilitario.complete("", 2, "0", Direcao.ESQUERDA)); // Primeira Instrucao
				linha.append(Utilitario.complete("", 2, "0", Direcao.ESQUERDA)); // Primeira Instrucao

				// Inclui a mora diaria por atraso
				linha.append(Utilitario.complete(new DecimalFormat("0.##").format(rs.getDouble("valormulta")).replace(",", ""), 13, "0", Direcao.DIREITA)); // Valor a ser cobrado por dias de atraso

				// Calcula a data do desconto com 10 dias antes da data de vencimento
				Calendar myCal2 = new GregorianCalendar();
				myCal2.setTime(rs.getDate("datavencimento"));
				myCal2.add(Calendar.DAY_OF_MONTH, -10);

				linha.append(Utilitario.complete(sdf.format(myCal2.getTime()), 6, " ", Direcao.ESQUERDA)); // Data limite para concessao do desconto
				linha.append(Utilitario.complete(new DecimalFormat("0.##").format(rs.getDouble("valorcobrado") * 0.2).replace(",", ""), 13, "0", Direcao.DIREITA)); // Valor do desconto
				linha.append(Utilitario.complete("", 13, "0", Direcao.ESQUERDA)); // Valor do IOF
				linha.append(Utilitario.complete("", 13, "0", Direcao.ESQUERDA)); // Valor do abatimento a ser concedido ou cancelado

				// Identificacao do tipo de inscricao do pagador
				linha.append(rs.getString("cpfCnpj").length() == 11 ? TipoDocumento.CPF.getCodigo() : TipoDocumento.CNPJ.getCodigo());

				linha.append(Utilitario.complete(rs.getString("cpfCnpj"), 14, "0", Direcao.DIREITA)); // Numero de inscricao do pagador
				linha.append(Utilitario.complete(Utilitario.removeAcentos(rs.getString("nomeproprietario")).toUpperCase(), 40, " ", Direcao.ESQUERDA)); // Nome do pagador
				linha.append(Utilitario.complete(Utilitario.removeAcentos(rs.getString("logradouro")).toUpperCase(), 40, " ", Direcao.ESQUERDA)); // Endereco completo
				linha.append(Utilitario.complete("", 12, " ", Direcao.ESQUERDA)); // 1ª Mensagem
				linha.append(Utilitario.complete(rs.getString("cep").substring(0, 5), 5, " ", Direcao.ESQUERDA)); // CEP
				linha.append(Utilitario.complete(rs.getString("cep").substring(5, 8), 3, " ", Direcao.ESQUERDA)); // Sufixo do CEP
				linha.append(Utilitario.complete("", 60, " ", Direcao.ESQUERDA)); // Sacador/Avalista ou 2ª Mensagem

				sequencialRegistro++;
				linha.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0", Direcao.DIREITA));// Número sequencial do registro de um em um
				// Registro no final de cada registro, (ODOA)
				linha.append(DELIMITADOR_REGISTRO);
			}

			// Escreve o registro trailler
			logger.log(Level.INFO, "Escrevendo o trailler.");
			linha.append("9");
			linha.append(Utilitario.complete("", 393, " ", Direcao.ESQUERDA));
			sequencialRegistro++;
			linha.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0", Direcao.DIREITA));// Número sequencial do registro de um em um
			// linha.append((byte) 0x1A);

			// Incrementa o indice e atualiza a tabela
			numeroSequencialRemessa++;
			qry = "update  remessa set numeroremessa=?, dataemissao=?";
			st = con.prepareStatement(qry);
			st.setInt(1, numeroSequencialRemessa);
			st.setDate(2, new Date(new GregorianCalendar().getTimeInMillis()));
			st.executeUpdate();

			return linha.toString();
		} catch (SQLException ex) {
			logger.log(Level.INFO, ex.getMessage());
		}
		return "";
	}

	public RemessaDTO select(int filtro) {
		List<RemessaDTO> lista = select(new Integer(filtro));
		if (lista.size() > 0) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	public List<RemessaDTO> select(Object filtro) {
		Connection con = DataBase.getConnection();
		List<RemessaDTO> retorno = new ArrayList<RemessaDTO>();
		String qry;
		PreparedStatement st;
		try {
			if (filtro instanceof String) {
				qry = "select * from remessa where numeroremessa like ? order by numeroremessa";
				st = con.prepareStatement(qry);
				st.setString(1, filtro + "%");
			} else if (filtro instanceof Integer) {
				qry = "select * from remessa where codigoremessa = ?  order by numeroremessa";
				st = con.prepareStatement(qry);
				st.setInt(1, ((Integer) filtro));
			} else {
				return null;
			}

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				RemessaDTO remessa = new RemessaDTO();
				remessa.setCodigoRemessa(rs.getInt("codigoremessa"));
				remessa.setNumeroRemessa(rs.getInt("numeroremessa"));
				remessa.setDataEmissao(rs.getDate("dataemissao"));
				retorno.add(remessa);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.log(Level.INFO, e.getMessage());
			}
		}
		return retorno;
	}

	public boolean update(RemessaDTO remessa) {
		boolean ok = true;
		Connection con = DataBase.getConnection();
		try {
			String qry = "update remessa set numeroremessa=?, dataemissao=? where codigoremessa=? ";
			PreparedStatement st = con.prepareStatement(qry);
			st.setInt(1, remessa.getNumeroRemessa());
			st.setDate(2, (Date) remessa.getDataEmissao());
			st.setInt(3, remessa.getCodigoRemessa());

			int retorno = st.executeUpdate();
			if (retorno < 1) {
				ok = false;
			}

		} catch (SQLException e) {
			ok = false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return ok;
	}
}
