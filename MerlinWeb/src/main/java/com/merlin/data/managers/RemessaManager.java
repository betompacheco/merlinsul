/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.data.managers;

import com.merlin.data.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemessaManager {

    private static final Logger logger = Logger.getLogger(RemessaManager.class.getName());

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

            //Escreve o Header
            logger.log(Level.INFO, "Escrevendo o header.");

            //Escreve os registros
            logger.log(Level.INFO, "Escrevendo os registros.");
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append(rs.getString("codigocobranca"));
                sb.append(rs.getString("valorcobrado"));
                sb.append("\n");
            }

            //Escreve o trailler
            logger.log(Level.INFO, "Escrevendo o trailler.");

            return sb.toString();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
