package com.merlin.data.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import com.merlin.data.DataBase;
import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.dto.CobrancaDTO;
import com.merlin.data.dto.CobrancaReportDTO;
import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.dto.DescCobranca;
import com.merlin.util.Config;
import com.merlin.util.NumberCodeGenerator;

public class CobrancaManager {

	private final static Logger logger = Logger.getLogger(CobrancaManager.class.getName());

	public void delete(int ano, int mes) {
		Connection con = DataBase.getConnection();
		List retorno = new ArrayList();
		GregorianCalendar gc = new GregorianCalendar(ano, mes, 1, 0, 0, 0);
		Date dtInicial = gc.getTime();
		gc.add(GregorianCalendar.MONTH, 1);
		gc.add(GregorianCalendar.DATE, -1);
		gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
		gc.set(GregorianCalendar.MINUTE, 59);
		gc.set(GregorianCalendar.SECOND, 59);
		Date dtFinal = gc.getTime();

		String qry;
		PreparedStatement st;
		try {
			qry = "delete from desccobranca where codigocobranca in (select codigocobranca from cobranca where datavencimento between ? and ? )";
			st = con.prepareStatement(qry);
			st.setDate(1, new java.sql.Date(dtInicial.getTime()));
			st.setDate(2, new java.sql.Date(dtFinal.getTime()));

			st.executeUpdate();
			qry = "delete  from cobranca where datavencimento between ? and ? ";
			st = con.prepareStatement(qry);
			st.setDate(1, new java.sql.Date(dtInicial.getTime()));
			st.setDate(2, new java.sql.Date(dtFinal.getTime()));

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}

	private List getDescCobranca(Connection con, int codigocobranca) throws SQLException {
		List retorno = new ArrayList();
		String qry;
		PreparedStatement st;
		qry = "select * from desccobranca where codigocobranca = ? order by codigodesccobranca ";
		st = con.prepareStatement(qry);
		st.setInt(1, codigocobranca);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			DescCobranca desc = new DescCobranca();
			desc.setCodigoDescCobranca(rs.getInt("codigodesccobranca"));
			desc.setCodigoCobranca(rs.getInt("codigocobranca"));
			desc.setDescricao(rs.getString("descricao"));
			desc.setValor(rs.getDouble("valor"));
			retorno.add(desc);
		}

		return retorno;

	}

	public void insert(CobrancaDTO cobranca) {
		List lista = new ArrayList();
		lista.add(cobranca);
		insert(lista);
	}

	public void insert(List lista) {
		Connection con = DataBase.getConnection();
		String qry;
		PreparedStatement st;
		PreparedStatement st2;
		try {
			int codigocobranca = 0;
			int codigodesccobranca = 0;
			qry = "select max(codigocobranca) as tot from cobranca";
			Statement stt = con.createStatement();
			ResultSet rs = stt.executeQuery(qry);
			if (rs.next()) {
				codigocobranca = rs.getInt("tot");
			}
			qry = "select max(codigodesccobranca) as tot from desccobranca";
			stt = con.createStatement();
			rs = stt.executeQuery(qry);
			if (rs.next()) {
				codigodesccobranca = rs.getInt("tot");
			}

			qry = "insert into cobranca (" + "codigocobranca, " + "dataemissao, " + "datavencimento, " + "valordocumento, " + "valordesconto, " + "valormulta, " + "valorcobrado, " + "valorpago, " + "baixa, " + "datapagamento, "
					+ "codigomensagem, " + "codigoapartamento) " + "values (?,?,?,?,?,?,?,?,?,?,?,?) ";
			st = con.prepareStatement(qry);
			qry = "insert into desccobranca (codigodesccobranca, codigocobranca, descricao, valor) values (?,?,?,?) ";
			st2 = con.prepareStatement(qry);
			for (int i = 0; i < lista.size(); i++) {
				CobrancaDTO cobranca = (CobrancaDTO) lista.get(i);
				codigocobranca++;
				cobranca.setCodigoCobranca(codigocobranca);
				st.setInt(1, cobranca.getCodigoCobranca());
				st.setDate(2, new java.sql.Date(cobranca.getDataEmissao().getTime()));
				st.setDate(3, new java.sql.Date(cobranca.getDataVencimento().getTime()));
				st.setDouble(4, cobranca.getValorDocumento());
				st.setDouble(5, cobranca.getValorDesconto());
				st.setDouble(6, cobranca.getValorMulta());
				st.setDouble(7, cobranca.getValorCobrado());
				st.setDouble(8, cobranca.getValorPago());
				st.setBoolean(9, cobranca.isBaixa());
				if (cobranca.getDataPagamento() != null) {
					st.setDate(10, new java.sql.Date(cobranca.getDataPagamento().getTime()));
				} else {
					st.setNull(10, Types.DATE);

				}
				st.setInt(11, cobranca.getCodigoMensagen());
				st.setInt(12, cobranca.getCodigoApartamento());

				st.executeUpdate();
				for (int j = 0; j < cobranca.getDescricao().size(); j++) {
					DescCobranca desc = (DescCobranca) cobranca.getDescricao().get(j);
					codigodesccobranca++;
					desc.setCodigoCobranca(codigocobranca);
					desc.setCodigoDescCobranca(codigodesccobranca);
					st2.setInt(1, desc.getCodigoDescCobranca());
					st2.setInt(2, desc.getCodigoCobranca());
					st2.setString(3, desc.getDescricao());
					st2.setDouble(4, desc.getValor());
					st2.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}

	private CobrancaDTO leCobranca(ResultSet rs) throws SQLException {
		CobrancaDTO cob = new CobrancaDTO();
		cob.setCodigoCobranca(rs.getInt("codigocobranca"));
		cob.setDataEmissao(rs.getDate("dataemissao"));
		cob.setDataVencimento(rs.getDate("datavencimento"));
		cob.setValorDocumento(rs.getDouble("valordocumento"));
		cob.setValorDesconto(rs.getDouble("valordesconto"));
		cob.setValorMulta(rs.getDouble("valormulta"));
		cob.setValorCobrado(rs.getDouble("valorcobrado"));
		cob.setValorPago(rs.getDouble("valorpago"));
		cob.setBaixa(rs.getBoolean("baixa"));
		cob.setDataPagamento(rs.getDate("datapagamento"));
		cob.setCodigoMensagen(rs.getInt("codigomensagem"));
		cob.setCodigoApartamento(rs.getInt("codigoapartamento"));
		return cob;
	}

	/**
	 *
	 * @param ano
	 * @param mes
	 * @param apartamento
	 * @return A lista de cobrancas
	 */
	public List select(int ano, int mes, int apartamento) {
		Connection con = DataBase.getConnection();
		List retorno = new ArrayList();
		Date dtInicial = null;
		Date dtFinal = null;
		if (ano > -1 && mes > -1) {
			GregorianCalendar gc = new GregorianCalendar(ano, mes, 1, 0, 0, 0);
			dtInicial = gc.getTime();
			gc.add(GregorianCalendar.MONTH, 1);
			gc.add(GregorianCalendar.DATE, -1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE, 59);
			gc.set(GregorianCalendar.SECOND, 59);
			dtFinal = gc.getTime();
		}
		ApartamentoManager am = new ApartamentoManager();
		CondominioManager cm = new CondominioManager();

		String qry;
		PreparedStatement st;
		List parametros = new ArrayList();
		try {
			qry = "select * from cobranca where ";
			if (ano > -1 && mes > -1) {
				qry += " datavencimento between ? and ?  ";
				parametros.add(dtInicial);
				parametros.add(dtFinal);
			}
			if (apartamento > 0) {
				if (ano > -1 && mes > -1) {
					qry += " and ";
				}
				qry += "  codigoapartamento=? ";
				parametros.add(new Integer(apartamento));
			}
			st = con.prepareStatement(qry);
			for (int i = 0; i < parametros.size(); i++) {
				Object ob = parametros.get(i);
				if (ob instanceof Date) {
					st.setDate(i + 1, new java.sql.Date(((Date) ob).getTime()));
				} else if (ob instanceof Integer) {
					st.setInt(i + 1, ((Integer) ob).intValue());
				}
			}

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CobrancaDTO cobranca = leCobranca(rs);
				retorno.add(cobranca);
			}
			rs.close();

			// le todos os apartamentos
			List listApartamentos = am.select("");
			// le todos os condominios
			List listCondominios = cm.select("");

			for (int i = 0; i < retorno.size(); i++) {
				CobrancaDTO cobranca = (CobrancaDTO) retorno.get(i);
				for (int a = 0; a < listApartamentos.size(); a++) {
					ApartamentoDTO apto = (ApartamentoDTO) listApartamentos.get(a);
					if (apto.getCodigoapartamento().intValue() == cobranca.getCodigoApartamento()) {
						cobranca.setApartamento(apto);
						for (int c = 0; c < listCondominios.size(); c++) {
							CondominioDTO cond = (CondominioDTO) listCondominios.get(c);
							if (apto.getCodigocondominio().equals(cond.getCodigocondominio())) {
								apto.setCondominio(cond);
								break;
							}
						}
						break;
					}
				}
				cobranca.calc(); // Calcula o Codigo de barras

				// Coloca o Nosso Numero com o DAC
				NumberCodeGenerator ncg = new NumberCodeGenerator();
				cobranca.setCodigoDocumento(ncg.comporNossoNumero(cobranca.getCodigoCobranca(), Config.CARTEIRA));

				// Coloca o numero de apartamento que deve aparecer no boleto
				cobranca.setNumeroApartamento(cobranca.getApartamento().getNumeroapartamento().intValue());
				// Coloca o nome do Condominio que deve aparecer no boleto
				cobranca.setNomeCondominio(cobranca.getApartamento().getCondominio().getNomecondominio().toString());

				cobranca.setDescricao(getDescCobranca(con, cobranca.getCodigoCobranca()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return retorno;

	}

	public List selectReportInadimplentes(int ano, int mes, int apartamento) {
		Connection con = DataBase.getConnection();
		List retorno = new ArrayList();
		Date dtInicial = null;
		Date dtFinal = null;
		if (ano > -1) {
			GregorianCalendar gc = null;
			if (mes > -1) {
				gc = new GregorianCalendar(ano, mes, 1, 0, 0, 0);
				dtInicial = gc.getTime();
				gc.add(GregorianCalendar.MONTH, 1);
			} else {
				gc = new GregorianCalendar(ano, 0, 1, 0, 0, 0);
				dtInicial = gc.getTime();
				gc.add(GregorianCalendar.YEAR, 1);
			}
			gc.add(GregorianCalendar.DATE, -1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE, 59);
			gc.set(GregorianCalendar.SECOND, 59);
			dtFinal = gc.getTime();
		}

		String qry;
		PreparedStatement st;
		List parametros = new ArrayList();
		try {
			qry = "select c.nomecondominio, p.nomeproprietario, a.numeroapartamento, co.* " + " from cobranca co, condominio c, proprietario p, apartamento a where " + " co.codigoapartamento = a.codigoapartamento and "
					+ " a.codigoproprietario = p.codigoproprietario and " + " a.codigocondominio = c.codigocondominio ";
			if (ano > -1 && mes > -1) {
				qry += " and datavencimento between ? and ?  ";
				parametros.add(dtInicial);
				parametros.add(dtFinal);
			}
			if (apartamento > 0) {
				qry += " and codigoapartamento=? ";
				parametros.add(new Integer(apartamento));
			}
			qry += " and datavencimento < ? and not baixa ";
			parametros.add(new Date());
			qry += " order by nomecondominio, nomeproprietario ";
			st = con.prepareStatement(qry);
			for (int i = 0; i < parametros.size(); i++) {
				Object ob = parametros.get(i);
				if (ob instanceof Date) {
					st.setDate(i + 1, new java.sql.Date(((Date) ob).getTime()));
				} else if (ob instanceof Integer) {
					st.setInt(i + 1, ((Integer) ob).intValue());
				}
			}

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CobrancaReportDTO cob = new CobrancaReportDTO();
				cob.setNomeCondominio(rs.getString("nomecondominio"));
				cob.setNomeProprietario(rs.getString("nomeproprietario"));
				cob.setNumeroApartamento(rs.getString("numeroApartamento"));
				cob.setDataEmissao(rs.getDate("dataemissao"));
				cob.setDataVencimento(rs.getDate("datavencimento"));
				cob.setValorDocumento(rs.getDouble("valordocumento"));
				cob.setValorDesconto(rs.getDouble("valordesconto"));
				cob.setValorMulta(rs.getDouble("valormulta"));
				cob.setValorCobrado(rs.getDouble("valorcobrado"));
				cob.setValorPago(rs.getDouble("valorpago"));
				cob.setBaixa(rs.getBoolean("baixa"));
				cob.setDataPagamento(rs.getDate("datapagamento"));
				retorno.add(cob);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return retorno;

	}

	public List selectReportMovimentos(int ano, int mes, int proprietario) {
		Connection con = DataBase.getConnection();
		List retorno = new ArrayList();
		Date dtInicial = null;
		Date dtFinal = null;
		if (ano > -1) {
			GregorianCalendar gc = null;
			if (mes > -1) {
				gc = new GregorianCalendar(ano, mes, 1, 0, 0, 0);
				dtInicial = gc.getTime();
				gc.add(GregorianCalendar.MONTH, 1);
			} else {
				gc = new GregorianCalendar(ano, 0, 1, 0, 0, 0);
				dtInicial = gc.getTime();
				gc.add(GregorianCalendar.YEAR, 1);
			}
			gc.add(GregorianCalendar.DATE, -1);
			gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gc.set(GregorianCalendar.MINUTE, 59);
			gc.set(GregorianCalendar.SECOND, 59);
			dtFinal = gc.getTime();
		}

		String qry;
		PreparedStatement st;
		List parametros = new ArrayList();
		try {
			qry = "select c.nomecondominio, p.nomeproprietario, p.cpf,a.numeroapartamento, co.* " + " from cobranca co, condominio c, proprietario p, apartamento a where " + " co.codigoapartamento = a.codigoapartamento and "
					+ " a.codigoproprietario = p.codigoproprietario and " + " a.codigocondominio = c.codigocondominio ";
			if (ano > -1 && mes > -1) {
				qry += " and datavencimento between ? and ?  ";
				parametros.add(dtInicial);
				parametros.add(dtFinal);
			}
			if (proprietario > 0) {
				qry += " and codigoproprietario=? ";
				parametros.add(new Integer(proprietario));
			}
			qry += " and datavencimento < ? and baixa ";
			parametros.add(new Date());
			qry += " order by nomecondominio, nomeproprietario ";
			st = con.prepareStatement(qry);
			for (int i = 0; i < parametros.size(); i++) {
				Object ob = parametros.get(i);
				if (ob instanceof Date) {
					st.setDate(i + 1, new java.sql.Date(((Date) ob).getTime()));
				} else if (ob instanceof Integer) {
					st.setInt(i + 1, ((Integer) ob).intValue());
				}
			}

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CobrancaReportDTO cob = new CobrancaReportDTO();
				cob.setNomeCondominio(rs.getString("nomecondominio"));
				cob.setNomeProprietario(rs.getString("nomeproprietario"));
				cob.setCpfCnpjProprietario(rs.getString("cpfCnpj"));
				cob.setNumeroApartamento(rs.getString("numeroApartamento"));
				cob.setDataEmissao(rs.getDate("dataemissao"));
				cob.setDataVencimento(rs.getDate("datavencimento"));
				cob.setValorDocumento(rs.getDouble("valordocumento"));
				cob.setValorDesconto(rs.getDouble("valordesconto"));
				cob.setValorMulta(rs.getDouble("valormulta"));
				cob.setValorCobrado(rs.getDouble("valorcobrado"));
				cob.setValorPago(rs.getDouble("valorpago"));
				cob.setBaixa(rs.getBoolean("baixa"));
				cob.setDataPagamento(rs.getDate("datapagamento"));
				retorno.add(cob);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return retorno;
	}

	public void updateCobranca(CobrancaDTO cobranca) {
		Connection con = DataBase.getConnection();
		String qry;
		PreparedStatement st;
		PreparedStatement st2;
		try {
			qry = "update  cobranca set " + "dataemissao=?, " + "datavencimento=?, " + "valordocumento=?, " + "valordesconto=?, " + "valormulta=?, " + "valorcobrado=?, " + "valorpago=?, " + "baixa=?, " + "datapagamento=?, "
					+ "codigomensagem=?, " + "codigoapartamento=? " + " where codigocobranca=?";
			st = con.prepareStatement(qry);
			st.setDate(1, new java.sql.Date(cobranca.getDataEmissao().getTime()));
			st.setDate(2, new java.sql.Date(cobranca.getDataVencimento().getTime()));
			st.setDouble(3, cobranca.getValorDocumento());
			st.setDouble(4, cobranca.getValorDesconto());
			st.setDouble(5, cobranca.getValorMulta());
			st.setDouble(6, cobranca.getValorCobrado());
			st.setDouble(7, cobranca.getValorPago());
			st.setBoolean(8, cobranca.isBaixa());
			if (cobranca.getDataPagamento() != null) {
				st.setDate(9, new java.sql.Date(cobranca.getDataPagamento().getTime()));
			} else {
				st.setNull(9, Types.DATE);

			}
			st.setInt(10, cobranca.getCodigoMensagen());
			st.setInt(11, cobranca.getCodigoApartamento());
			st.setInt(12, cobranca.getCodigoCobranca());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}
}
