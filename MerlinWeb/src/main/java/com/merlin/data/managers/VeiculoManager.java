/*
 * Created on Feb 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.merlin.data.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.merlin.data.DataBase;
import com.merlin.data.dto.VeiculoDTO;
import com.merlin.data.dto.VeiculoReportDTO;

/**
 * @author Leonardo Lopes
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class VeiculoManager {

    public boolean update(VeiculoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update veiculo set " + "tipo=?, " + "placa=?, " + "modelo=?, " + "fabricante=?, " + "codigoApartamento=? "
                    + "where codigoVeiculo=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setString(1, pb.getTipoVeiculo());
            st.setString(2, pb.getPlacaVeiculo());
            st.setString(3, pb.getModeloVeiculo());
            st.setString(4, pb.getFabricanteVeiculo());
            st.setInt(5, pb.getCodigoApartamento().intValue());
            st.setInt(6, pb.getCodigoVeiculo().intValue());

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

    public boolean insert(VeiculoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into veiculo (codigoVeiculo, tipo, placa, modelo, fabricante, codigoApartamento) " + "values (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoVeiculo().intValue());
            st.setString(2, pb.getTipoVeiculo());
            st.setString(3, pb.getPlacaVeiculo());
            st.setString(4, pb.getModeloVeiculo());
            st.setString(5, pb.getFabricanteVeiculo());
            st.setInt(6, pb.getCodigoApartamento().intValue());

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

    public List select(Object filtro) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            if (filtro instanceof String) {
                qry = "select * from veiculo where placa like ?";
                st = con.prepareStatement(qry);
                st.setString(1, filtro + "%");
            } else if (filtro instanceof Integer) {
                qry = "select * from veiculo where codigoVeiculo = ?";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                VeiculoDTO pb = new VeiculoDTO();
                pb.setCodigoVeiculo(new Integer(rs.getInt("codigoVeiculo")));
                pb.setTipoVeiculo(rs.getString("tipo"));
                pb.setPlacaVeiculo(rs.getString("placa"));
                pb.setModeloVeiculo(rs.getString("modelo"));
                pb.setFabricanteVeiculo(rs.getString("fabricante"));
                pb.setCodigoApartamento(new Integer(rs.getInt("codigoApartamento")));
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

    public VeiculoDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (VeiculoDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public boolean delete(VeiculoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from veiculo where codigoVeiculo = ? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoVeiculo().intValue());
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

    public VeiculoDTO getNewBean() {
        VeiculoDTO bean = new VeiculoDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoVeiculo) from veiculo ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigoVeiculo");
            }

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        bean.setCodigoVeiculo(new Integer(codigo + 1));
        return bean;
    }

    public List selectReport() {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            qry = "select c.nomecondominio, a.numeroapartamento," + " v.codigoveiculo, v.tipo, v.placa, v.modelo, v.fabricante, v.codigoapartamento"
                    + " from veiculo v, condominio c, apartamento a "
                    + " where v.codigoapartamento=a.codigoapartamento and a.codigocondominio = c.codigocondominio "
                    + " order by c.nomecondominio, a.numeroapartamento";
            st = con.prepareStatement(qry);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                VeiculoReportDTO pb = new VeiculoReportDTO();
                pb.setNomeCondominio(rs.getString("nomecondominio"));
                pb.setNumeroApartamento(rs.getString("numeroapartamento"));
                pb.setCodigoVeiculo(new Integer(rs.getInt("codigoVeiculo")));
                pb.setTipoVeiculo(rs.getString("tipo"));
                pb.setPlacaVeiculo(rs.getString("placa"));
                pb.setModeloVeiculo(rs.getString("modelo"));
                pb.setFabricanteVeiculo(rs.getString("fabricante"));
                pb.setCodigoApartamento(new Integer(rs.getInt("codigoApartamento")));
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
}
