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
    private final String delimitador_registro = "\r\n";
    private final byte finalizador_arquivo = 0x1A;

    public String montaRemessa() {
        try {
            Connection con = DataBase.getConnection();
            String qry;
            PreparedStatement st;

            qry = "select * from cobranca";
            st = con.prepareStatement(qry);
//        st.setInt(1, condominio);
//        st.setInt(2, numero);

            ResultSet rs = st.executeQuery();
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat();

            int sequencialRegistro = 1;

            //Escreve o Header Label
            logger.log(Level.INFO, "Escrevendo o header.");
            sb.append("0"); //Identificacao do registro
            sb.append("1");//Identificador do arquivo remessa
            sb.append("REMESSA");//Literal Remessa
            sb.append("01");//Codigo de servico
            sb.append(Utilitario.complete("COBRANCA", 15, " "));//Literal Serviço
            sb.append(Utilitario.complete("????", 20, " "));//Codigo da empresa
            sb.append(Utilitario.complete("Condominio Merin Sul", 30, " "));//Nome da empresa
            sb.append("237"); //Numero do Bradesco na camara de compensacao
            sb.append(Utilitario.complete("Bradesco", 15, " "));//Nome do banco por extenso

            sdf.applyPattern("MMyyyy");
            sb.append(sdf.format(new GregorianCalendar().getTime()));//Data da Gravacao do arquivo
            sb.append(Utilitario.complete("", 8, " "));//Branco
            sb.append("MX"); //Identificacao do sistema
            sb.append(Utilitario.complete("????", 7, " "));//Numero sequencial da remessa
            sb.append(Utilitario.complete("", 277, " "));//Literal Serviço
            sb.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//Número sequencial do registro de um em um
            sb.append(delimitador_registro);

            //Escreve os registros de transações
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
                sb.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//Número sequencial do registro de um em um

                sb.append(delimitador_registro);
            }

            //Escreve o registro trailler
            logger.log(Level.INFO, "Escrevendo o trailler.");
            sb.append("9");
            sb.append(Utilitario.complete("", 393, " "));
            sequencialRegistro++;
            sb.append(Utilitario.complete(Integer.toString(sequencialRegistro), 6, "0"));//Número sequencial do registro de um em um
            sb.append(finalizador_arquivo);

            return sb.toString();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
