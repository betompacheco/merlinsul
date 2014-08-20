package com.merlin.data.managers;

import com.merlin.data.DataBase;
import com.merlin.data.dto.ServicoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoManager {

    private final static Logger logger = Logger.getLogger(ServicoManager.class.getName());

    public boolean update(ServicoDTO servico) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update servico set " + "nomeservico=?, " + "valorservico=?, " + "tiposervico=? " + "where codigoservico=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setString(1, servico.getNomeServico());
            st.setDouble(2, servico.getValorServico());
            st.setString(3, servico.getTipoServico());
            st.setInt(4, servico.getCodigoServico().intValue());

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

    public boolean insert(ServicoDTO pb) {
        logger.log(Level.INFO, "Inserindo um serviço");

        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into servico (codigoservico, nomeservico, valorservico, tiposervico) " + "values (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoServico().intValue());
            st.setString(2, pb.getNomeServico());
            st.setDouble(3, pb.getValorServico());
            st.setString(4, pb.getTipoServico());

            int retorno = st.executeUpdate();
            if (retorno < 1) {
                ok = false;
            }

        } catch (SQLException e) {
            ok = false;
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ok;
    }

    public List select(Object filtro) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            if (filtro instanceof String) {
                qry = "select * from servico where nomeservico like ? order by nomeservico";
                st = con.prepareStatement(qry);
                st.setString(1, filtro + "%");
            } else if (filtro instanceof Integer) {
                qry = "select * from servico where codigoservico = ?  order by nomeservico";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServicoDTO pb = new ServicoDTO();
                pb.setCodigoServico(new Integer(rs.getInt("codigoservico")));
                pb.setNomeServico(rs.getString("nomeservico"));
                pb.setValorServico(rs.getDouble("valorservico"));
                pb.setTipoServico(rs.getString("tiposervico"));
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

    public List select(String filtro) {
        return select((Object) filtro);
    }

    public ServicoDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (ServicoDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public Map selectHash() {
        List lista = select("");
        Map retorno = new HashMap();
        for (int i = 0; i < lista.size(); i++) {
            ServicoDTO servico = (ServicoDTO) lista.get(i);
            retorno.put(servico.getCodigoServico(), servico);
        }
        return retorno;
    }

    public boolean delete(ServicoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from servico where codigoservico = ?  ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoServico().intValue());
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

    public ServicoDTO getNewBean() {
        logger.log(Level.INFO, "Obtendo o novo bean");
        ServicoDTO bean = new ServicoDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoservico) as codigoservico from servico";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigoservico");
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

        bean.setCodigoServico(new Integer(codigo + 1));
        return bean;
    }
}
