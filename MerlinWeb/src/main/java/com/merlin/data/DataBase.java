package com.merlin.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

	public static String path = "";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			// String url="jdbc:hsqldb:file:"+path+"banco/banco";
			String url = "jdbc:hsqldb:hsql://localhost/banco";
			String user = "sa";
			String pass = "senha";
			con = DriverManager.getConnection(url, user, pass);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * @param filtros
	 * @param con
	 * @param qry
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getStatementFiltered(List filtros, Connection con, String qry) throws SQLException {
		String where = "";
		List parametros = new ArrayList();
		if (filtros != null) {
			for (int i = 0; i < filtros.size(); i++) {
				/*
				 * Filter filtro = (Filter) filtros.get(i); Field campo =
				 * filtro.getField(); if (filtro.getValues().size()>0) { Object
				 * valor=filtro.getValues().get(0); if
				 * (campo.getType()==Field.TEXT){ if (valor!=null &&
				 * valor.toString().trim().length()>0){ where+=
				 * (where.length()>0?" AND ":" ") + campo.getName() +
				 * " like ? "; parametros.add(valor.toString() + "%"); } } }
				 */
			}
		}
		if (parametros.size() > 0) {
			qry += " WHERE " + where;
		}
		PreparedStatement st = con.prepareStatement(qry);

		if (parametros.size() > 0) {
			for (int i = 0; i < parametros.size(); i++) {
				Object valor = parametros.get(i);
				if (valor instanceof String) {
					st.setString(i + 1, valor.toString());
				}
			}

		}
		return st;
	}
}
