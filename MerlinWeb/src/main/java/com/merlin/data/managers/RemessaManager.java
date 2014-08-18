/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.data.managers;

import com.merlin.data.DataBase;
import com.merlin.util.Utilitario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemessaManager {

    private static final Logger logger = Logger.getLogger(RemessaManager.class.getName());

    private int ano;
    private int mes;
    private boolean isForTest;

    private final String delimitador_registro = "\r\n";
    private final byte finalizador_arquivo = 0x1A;

    public RemessaManager(int ano, int mes, boolean isForTest) {
        this.ano = ano;
        this.mes = mes;
        this.isForTest = isForTest;
    }

    public String montaRemessa() {
        try {
            Connection con = DataBase.getConnection();
            String qry;
            PreparedStatement st;

            qry = "select c.codigocobranca as codcobranca, p.nomeproprietario as nome, c.dataemissao, c.datavencimento, c.valorcobrado from proprietario as p, apartamento as a, cobranca as c where p.codigoproprietario = a.codigoproprietario and a.codigoapartamento = c.codigoapartamento order by codcobranca, nome";
            st = con.prepareStatement(qry);
//        st.setInt(1, condominio);
//        st.setInt(2, numero);

            ResultSet rs = st.executeQuery();

            //Verifica se existem dados de cobranca
            if (!rs.isBeforeFirst()) {
                return "Nao existem dados a serem colocados na remessa.";
            }

            if (isForTest) {
                logger.log(Level.INFO, "Gerando o arquivo somente para teste, implementar...");
            }

            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat();

            int sequencialRegistro = 1;

            //Escreve o Header Label
            logger.log(Level.INFO, "Escrevendo o header.");
            sb.append("0"); //Identificacao do registro
            sb.append("1");//Identificador do arquivo remessa
            sb.append("REMESSA");//Literal Remessa
            sb.append("01");//Codigo de servico
            sb.append(Utilitario.complete("COBRANCA", 15, " "));//Literal Servi�o
            sb.append(Utilitario.complete("????", 20, " "));//Codigo da empresa
            sb.append(Utilitario.complete("Condominio Merin Sul", 30, " "));//Nome da empresa
            sb.append("237"); //Numero do Bradesco na camara de compensacao
            sb.append(Utilitario.complete("Bradesco", 15, " "));//Nome do banco por extenso

            sdf.applyPattern("MMyyyy");
            sb.append(sdf.format(new GregorianCalendar().getTime()));//Data da Gravacao do arquivo
            sb.append(Utilitario.complete("", 8, " "));//Branco
            sb.append("MX"); //Identificacao do sistema
            sb.append(Utilitario.complete("????", 7, " "));//Numero sequencial da remessa
            sb.append(Utilitario.complete("", 277, " "));//Literal Servi�o
            sb.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//N�mero sequencial do registro de um em um
            sb.append(delimitador_registro);

            //Escreve os registros de transa��es
            logger.log(Level.INFO, "Escrevendo os registros.");

            while (rs.next()) {
                sb.append("1");//Identificacao do registro
                sb.append(Utilitario.complete("", 5, " ")); //Agencia de debito (Opcional)
                sb.append(Utilitario.complete("", 1, " ")); //Digito da agencia de debito (Opcional)
                sb.append(Utilitario.complete("", 5, " ")); //Razao da conta corrente (Opcional)
                sb.append(Utilitario.complete("", 7, " ")); //Conta corrente (Opcional)
                sb.append(Utilitario.complete("", 1, " ")); //Digito da conta corrente (Opcional)
                sb.append(Utilitario.complete("????", 17, " ")); //Identificacao da empresa beneficiaria no banco
                sb.append(Utilitario.complete("", 25, " ")); //Numero de controle do participante (Uso da empresa)
                sb.append("237"); //Codigo do banco a ser debitado na camara de compensacao
                sb.append("2"); //Campo de multa, 2 se condiderar multa, 0 se sem multa
                sb.append(Utilitario.complete("2", 4, " ")); //Percentual de multa
                sb.append(Utilitario.complete(rs.getString("codcobranca"), 11, "0"));//Identificacao do titulo no banco
                //Digito de auto conferencia do numero bancario
                //Desconto de bonificacao por dia
                sb.append("2");//Condicao de emissao da papeleta de cobranca
                sb.append("N");//Ident se emite boleto para debito automatico
                sb.append(Utilitario.complete("", 10, " "));//Identifcacao da operacao do banco (Brancos)
                sb.append(" ");//Indicador rateio credito (opcional)
                sb.append(" ");//Enderecamento para aviso de debito automatico em conta corrente (opcional)
                sb.append(Utilitario.complete("", 2, " "));//Branco
                //Identificacao da ocorrencia
                //Nunero do documento
                sdf.applyPattern("ddMMyyyy");
                sb.append(sdf.format(rs.getDate("datavencimento"))); //Data de vencimento do titulo
                sb.append(Utilitario.complete(rs.getString("valorcobrado").replace(".", ""), 13, " ")); //Valor do titulo
                sb.append(Utilitario.complete("", 3, "0")); //Banco encarregado da cobranca (Preencher com zeros)
                sb.append(Utilitario.complete("", 5, " ")); //Agencia depositaria (Preencher com zeros)
                sb.append("99"); //Especie do titulo
                sb.append("N"); //Identificacao
                sdf.applyPattern("ddMMyyyy");
                sb.append(sdf.format(rs.getDate("dataemissao"))); //Data de emissao do titulo
                sb.append(Utilitario.complete("", 2, " ")); //Primeira Instrucao
                sb.append(Utilitario.complete("", 2, " ")); //Primeira Instrucao
                sb.append(Utilitario.complete("", 13, " ")); //Valor a ser cobrado por dias de atraso
                sb.append(Utilitario.complete("??????", 6, " ")); //Data limite para concessao do desconto
                sb.append(Utilitario.complete("", 13, " ")); //Valor do desconto
                sb.append(Utilitario.complete("", 13, " ")); //Valor do IOF
                sb.append(Utilitario.complete("", 13, " ")); //Valor do abatimento a ser concedido ou cancelado
                sb.append("99"); //Identificacao do tipo de inscricao do pagador
                sb.append(Utilitario.complete("", 14, " ")); //Numero de inscricao do pagador
                sb.append(Utilitario.complete("", 2, " ")); //Nome do pagador

//                sb.append(rs.getString("codigocobranca"));
                sequencialRegistro++;
                sb.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//N�mero sequencial do registro de um em um

                sb.append(delimitador_registro);
            }

            //Escreve o registro trailler
            logger.log(Level.INFO, "Escrevendo o trailler.");
            sb.append("9");
            sb.append(Utilitario.complete("", 393, " "));
            sequencialRegistro++;
            sb.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//N�mero sequencial do registro de um em um
            sb.append(finalizador_arquivo);

            return sb.toString();
        } catch (SQLException ex) {
            logger.log(Level.INFO, ex.getMessage());
        }
        return "";
    }
}