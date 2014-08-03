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

            //Escreve o Header Label
            logger.log(Level.INFO, "Escrevendo o header.");
            sb.append("0"); //Identificacao do registro
            sb.append("1");//Identificador do arquivo remessa
            sb.append("REMESSA");//Literal Remessa
            sb.append("01");//Codigo de servico
            sb.append(Utilitario.complete("COBRANCA", 15, " "));//Literal Serviço
            sb.append(Utilitario.complete("?????", 20, " "));//Codigo da empresa
            sb.append(Utilitario.complete("Condominio Merin Sul", 30, " "));//Nome da empresa
            sb.append("237"); //Numero do Bradesco na camara de compensacao
            sb.append(Utilitario.complete("Bradesco", 15, " "));//Nome do banco por extenso
            sb.append(new SimpleDateFormat("MMyyyy").format(new GregorianCalendar().getTime()));//Data da Gravacao do arquivo
            sb.append(Utilitario.complete("", 8, " "));//Branco
            sb.append("MX"); //Identificacao do sistema
            sb.append(delimitador_registro);

            //Escreve os registros de transações
            logger.log(Level.INFO, "Escrevendo os registros.");

            while (rs.next()) {
                sb.append(rs.getString("codigocobranca"));
                sb.append(rs.getString("valorcobrado"));
                sb.append(delimitador_registro);
            }

            //Escreve o registro trailler
            logger.log(Level.INFO, "Escrevendo o trailler.");
            sb.append("9");
            sb.append(Utilitario.complete("", 393, " "));
            sb.append(Utilitario.complete("x", 6, "0"));
            sb.append(finalizador_arquivo);

            return sb.toString();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
