package com.merlin.data.managers;

import com.merlin.data.DataBase;
import com.merlin.data.dto.CondominioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CondominioManager {

    private final static Logger logger = Logger.getLogger(CondominioManager.class.getName());

    public boolean update(CondominioDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update condominio set " + "nomeCondominio=?, " + "qtdeapartamento=? " + "where codigocondominio=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setString(1, pb.getNomecondominio());
            st.setInt(2, pb.getQtdeapartamento().intValue());
            st.setInt(3, pb.getCodigocondominio().intValue());
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

    public boolean insert(CondominioDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into condominio (nomeCondominio, qtdeapartamento,  codigocondominio) " + "values (?,?,?) ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setString(1, pb.getNomecondominio());
            st.setInt(2, pb.getQtdeapartamento().intValue());
            st.setInt(3, pb.getCodigocondominio().intValue());
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

    public List select(String filtro) {
        return select((Object) filtro);
    }

    public CondominioDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (CondominioDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public List select(Object filtro) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            if (filtro instanceof String) {
                qry = "select * from condominio where nomecondominio like ?";
                st = con.prepareStatement(qry);
                st.setString(1, filtro + "%");
            } else if (filtro instanceof Integer) {
                qry = "select * from condominio where codigocondominio = ?";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CondominioDTO pb = new CondominioDTO();
                pb.setCodigocondominio(new Integer(rs.getInt("codigocondominio")));
                pb.setNomecondominio(rs.getString("nomecondominio"));
                pb.setQtdeapartamento(new Integer(rs.getInt("qtdeapartamento")));
                retorno.add(pb);
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

    public boolean delete(CondominioDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from  condominio where codigocondominio=?  ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigocondominio().intValue());
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

    public CondominioDTO getNewBean() {
        logger.log(Level.INFO, "Obtendo o novo bean");
        CondominioDTO bean = new CondominioDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigocondominio) as codigocondominio from condominio ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigocondominio");
            }
            logger.log(Level.INFO, "Obtido o id {0}", codigo);

        } catch (SQLException e) {
            logger.log(Level.INFO, e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }

        bean.setCodigocondominio(new Integer(codigo + 1));
        return bean;
    }
}
