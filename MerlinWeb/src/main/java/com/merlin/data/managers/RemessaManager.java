/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.data.managers;

import com.merlin.data.DataBase;
import com.merlin.data.dto.RemessaDTO;
import com.merlin.util.NumberCodeGenerator;
import com.merlin.util.Utilitario;
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

public class RemessaManager {

    private static final Logger logger = Logger.getLogger(RemessaManager.class.getName());

    private final int ano;
    private final int mes;

    private final String datePattern = "ddMMyy";
    private final String DELIMITADOR_REGISTRO = "\r\n";
    private final byte[] EOL_REGISTRY = {0x0D, 0x0A};
    private final byte FINALIZADOR_ARQUIVO = 0x1A;

    public RemessaManager() {
        this.ano = 0;
        this.mes = 0;
    }

    public RemessaManager(int ano, int mes, boolean isForTest) {
        this.ano = ano;
        this.mes = mes;
    }

    public String montaRemessa() {
        try {
            Connection con = DataBase.getConnection();
            String qry;
            PreparedStatement st = null;
            ResultSet rs = null;

            //Obtem o indice
            int numeroSequencialRemessa = 0;
            qry = "SELECT numeroremessa FROM remessa";
            Statement sta = con.createStatement();
            rs = sta.executeQuery(qry);
            if (rs.next()) {
                numeroSequencialRemessa = rs.getInt("numeroremessa");
            }

            //Consulta as cobrancas
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

            //Verifica se existem dados de cobranca
            if (!rs.isBeforeFirst()) {
                return "Nao existem dados a serem colocados na remessa.";
            }

            StringBuilder linha = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat();

            int sequencialRegistro = 1;

            //Escreve o Header Label
            logger.log(Level.INFO, "Escrevendo o header.");
            linha.append("0"); //Identificacao do registro
            linha.append("1");//Identificador do arquivo remessa
            linha.append("REMESSA");//Literal Remessa
            linha.append("01");//Codigo de servico
            linha.append(Utilitario.complete("COBRANCA", 15, " "));//Literal Serviço
            linha.append(Utilitario.complete("4631965", 20, "0"));//Codigo da empresa
            linha.append(Utilitario.complete("CONDOMINIO ED MERLIN SUL", 30, " "));//Nome da empresa
            linha.append("237"); //Numero do Bradesco na camara de compensacao
            linha.append(Utilitario.complete("Bradesco", 15, " "));//Nome do banco por extenso

            sdf.applyPattern(datePattern);
            linha.append(sdf.format(new GregorianCalendar().getTime()));//Data da Gravacao do arquivo

            linha.append(Utilitario.complete("", 8, " "));//Branco
            linha.append("MX"); //Identificacao do sistema
            linha.append(Utilitario.complete(Integer.toString(numeroSequencialRemessa), 7, "0"));//Numero sequencial da remessa
            linha.append(Utilitario.complete("", 277, " "));//Branco
            linha.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//Número sequencial do registro de um em um

            //Registro no final de cada registro, (ODOA)
            linha.append(DELIMITADOR_REGISTRO);

            logger.log(Level.INFO, "Quantidade de caracteres...:{0}", linha.toString().getBytes().length);

            //Escreve os registros de transações
            logger.log(Level.INFO, "Escrevendo os registros.");

            while (rs.next()) {
                linha.append("1");//Identificacao do registro
                linha.append(Utilitario.complete("", 5, " ")); //Agencia de debito (Opcional)
                linha.append(Utilitario.complete("", 1, " ")); //Digito da agencia de debito (Opcional)
                linha.append(Utilitario.complete("", 5, " ")); //Razao da conta corrente (Opcional)
                linha.append(Utilitario.complete("", 7, " ")); //Conta corrente (Opcional)
                linha.append(Utilitario.complete("", 1, " ")); //Digito da conta corrente (Opcional)
                linha.append(Utilitario.complete("009278576368", 17, " ")); //Identificacao da empresa beneficiaria no banco
                linha.append(Utilitario.complete("", 25, " ")); //Numero de controle do participante (Uso da empresa)
                linha.append("000"); //Codigo do banco a ser debitado na camara de compensacao
                linha.append("2"); //Campo de multa, 2 se condiderar multa, 0 se sem multa
                linha.append(Utilitario.complete(String.format(Locale.ENGLISH, "%.2f", rs.getDouble("valormulta")), 4, " ")); //Percentual de multa
                linha.append(Utilitario.complete(rs.getString("codigocobranca"), 11, "0"));//Identificacao do titulo no banco

                //Gera o DAC de cada um dos "nosso numero"
                NumberCodeGenerator ncg = new NumberCodeGenerator();
                linha.append((String) ncg.comporNossoNumero(rs.getLong("codigocobranca"), "09").subSequence(15, 16));//Digito de auto conferencia do numero bancario

                linha.append(Utilitario.complete("", 10, " "));//Desconto de bonificacao por dia
                linha.append("2");//Condicao de emissao da papeleta de cobranca
                linha.append("N");//Ident se emite boleto para debito automatico
                linha.append(Utilitario.complete("", 10, " "));//Identifcacao da operacao do banco (Brancos)
                linha.append(" ");//Indicador rateio credito (opcional)
                linha.append(" ");//Enderecamento para aviso de debito automatico em conta corrente (opcional)
                linha.append(Utilitario.complete("", 2, " "));//Branco
                linha.append(Utilitario.complete("", 2, " "));//Identificacao da ocorrencia
                linha.append(Utilitario.complete("", 10, " "));//Nunero do documento

                sdf.applyPattern(datePattern);
                linha.append(sdf.format(rs.getDate("datavencimento"))); //Data de vencimento do titulo
                linha.append(Utilitario.complete(rs.getString("valorcobrado"), 13, " ")); //Valor do titulo
                linha.append(Utilitario.complete("", 3, "0")); //Banco encarregado da cobranca (Preencher com zeros)
                linha.append(Utilitario.complete("", 5, " ")); //Agencia depositaria (Preencher com zeros)
                linha.append("99"); //Especie do titulo
                linha.append("N"); //Identificacao
                sdf.applyPattern(datePattern);
                linha.append(sdf.format(rs.getDate("dataemissao"))); //Data de emissao do titulo
                linha.append(Utilitario.complete("", 2, "0")); //Primeira Instrucao
                linha.append(Utilitario.complete("", 2, "0")); //Primeira Instrucao
                linha.append(Utilitario.complete("", 13, " ")); //Valor a ser cobrado por dias de atraso

                //Calcula a data do desconto com 10 dias antes da data de vencimento
                Calendar cal = new GregorianCalendar();
                cal.setTime(rs.getDate("datavencimento"));
                cal.add(Calendar.DAY_OF_MONTH, -10);

                linha.append(Utilitario.complete(sdf.format(cal.getTime()), 6, " ")); //Data limite para concessao do desconto
                linha.append(Utilitario.complete(new DecimalFormat("0.##").format(rs.getDouble("valorcobrado") * 0.2), 13, " ")); //Valor do desconto
                linha.append(Utilitario.complete("", 13, " ")); //Valor do IOF
                linha.append(Utilitario.complete("", 13, " ")); //Valor do abatimento a ser concedido ou cancelado
                linha.append("99"); //Identificacao do tipo de inscricao do pagador
                linha.append(Utilitario.complete("", 14, " ")); //Numero de inscricao do pagador
                linha.append(Utilitario.complete(rs.getString("nomeproprietario"), 40, " ")); //Nome do pagador
                linha.append(Utilitario.complete(rs.getString("logradouro"), 40, " ")); //Endereco completo
                linha.append(Utilitario.complete("", 12, " ")); //1ª Mensagem
                linha.append(Utilitario.complete(rs.getString("cep").substring(0, 5), 5, " ")); //CEP
                linha.append(Utilitario.complete(rs.getString("cep").substring(5, 8), 3, " ")); //Sufixo do CEP
                linha.append(Utilitario.complete("", 60, " ")); //Sacador/Avalista ou 2ª Mensagem

                sequencialRegistro++;
                linha.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//Número sequencial do registro de um em um
                //Registro no final de cada registro, (ODOA)
                linha.append(DELIMITADOR_REGISTRO);
            }

            //Escreve o registro trailler
            logger.log(Level.INFO, "Escrevendo o trailler.");
            linha.append("9");
            linha.append(Utilitario.complete("", 393, " "));
            sequencialRegistro++;
            linha.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//Número sequencial do registro de um em um
            //Finalizador de arquivo, no final do trailler, (1A)
            linha.append(FINALIZADOR_ARQUIVO);

            //Incrementa o indice e atualiza a tabela
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

    public RemessaDTO select(int filtro) {
        List<RemessaDTO> lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return lista.get(0);
        } else {
            return null;
        }
    }

    public RemessaDTO getNewBean() {
        RemessaDTO remessa = new RemessaDTO();
        remessa.setCodigoRemessa(1);
        return remessa;
    }
}
