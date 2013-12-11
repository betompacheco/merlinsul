package com.merlin.data.managers;

import com.merlin.data.DataBase;
import com.merlin.data.dto.ApartamentoDTO;
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

public class ApartamentoManager {

    private final static Logger logger = Logger.getLogger(ApartamentoManager.class.getName());

    public boolean update(ApartamentoDTO bean) {
        boolean ok = true;
        ApartamentoDTO pb = (ApartamentoDTO) bean;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update apartamento set " + "numeroapartamento=?, " + "codigocondominio=?, " + "codigoproprietario=?, " + "qtdequarto=? "
                    + "where codigoapartamento=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getNumeroapartamento().intValue());
            st.setInt(2, pb.getCodigocondominio().intValue());
            st.setInt(3, pb.getCodigoproprietario().intValue());
            st.setInt(4, pb.getQtdQuartos().intValue());
            st.setInt(5, pb.getCodigoapartamento().intValue());
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

    public boolean insert(ApartamentoDTO bean) {
        boolean ok = true;
        ApartamentoDTO pb = (ApartamentoDTO) bean;
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into Apartamento (codigoapartamento, codigocondominio, codigoproprietario, numeroapartamento, qtdequarto) "
                    + "values (?,?,?,?,?) ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoapartamento().intValue());
            st.setInt(2, pb.getCodigocondominio().intValue());
            st.setInt(3, pb.getCodigoproprietario().intValue());
            st.setInt(4, pb.getNumeroapartamento().intValue());
            st.setInt(5, pb.getQtdQuartos().intValue());
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

    public boolean existApartamento(int numero, int condominio) {
        Connection con = DataBase.getConnection();
        String qry;
        PreparedStatement st;
        boolean retorno = false;
        try {
            qry = "select count(*) as total from apartamento where codigocondominio = ? and numeroapartamento=? ";
            st = con.prepareStatement(qry);
            st.setInt(1, condominio);
            st.setInt(2, numero);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                if (total > 0) {
                    retorno = true;
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
        return retorno;
    }

    public List select(Object filtro) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            if (filtro instanceof String) {
                qry = "select * from apartamento where numeroapartamento like ? order by codigocondominio, numeroapartamento, qtdequarto";
                st = con.prepareStatement(qry);
                st.setString(1, filtro + "%");
            } else if (filtro instanceof Integer) {
                qry = "select * from apartamento where numeroapartamento = ? order by codigocondominio, numeroapartamento, qtdequarto";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }

            ResultSet rs = st.executeQuery();
            CondominioManager cm = new CondominioManager();
            List condominios = cm.select("");
            while (rs.next()) {
                ApartamentoDTO pb = new ApartamentoDTO();
                pb.setCodigoapartamento(new Integer(rs.getInt("codigoapartamento")));
                pb.setCodigocondominio(new Integer(rs.getInt("codigocondominio")));
                pb.setCodigoproprietario(new Integer(rs.getInt("codigoproprietario")));
                pb.setNumeroapartamento(new Integer(rs.getInt("numeroapartamento")));
                pb.setQtdQuartos(new Integer(rs.getInt("QTDEQUARTO")));
                for (int i = 0; i < condominios.size(); i++) {
                    CondominioDTO c = (CondominioDTO) condominios.get(i);
                    if (c.getCodigocondominio().equals(pb.getCodigocondominio())) {
                        pb.setCondominio(c);
                        break;
                    }
                }
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

    public List selectCondominio(int condominio) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            qry = "select * from apartamento where codigocondominio = ? order by numeroapartamento";
            st = con.prepareStatement(qry);
            st.setInt(1, condominio);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ApartamentoDTO pb = new ApartamentoDTO();
                pb.setCodigoapartamento(new Integer(rs.getInt("codigoapartamento")));
                pb.setCodigocondominio(new Integer(rs.getInt("codigocondominio")));
                pb.setCodigoproprietario(new Integer(rs.getInt("codigoproprietario")));
                pb.setNumeroapartamento(new Integer(rs.getInt("numeroapartamento")));
                pb.setQtdQuartos(new Integer(rs.getInt("QTDEQUARTO")));
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

    public ApartamentoDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (ApartamentoDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public boolean delete(ApartamentoDTO bean) {
        boolean ok = true;
        ApartamentoDTO pb = (ApartamentoDTO) bean;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from  apartamento where codigoapartamento=?  ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoapartamento().intValue());
            int retorno = st.executeUpdate();
            if (retorno < 1) {
                ok = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ok;
    }

    public ApartamentoDTO getNewBean() {
        logger.log(Level.INFO, "Obtendo o novo bean");
        ApartamentoDTO bean = new ApartamentoDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoapartamento) as codigoapartamento from apartamento ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigoapartamento");
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

        bean.setCodigoapartamento(new Integer(codigo + 1));
        return bean;
    }
}
