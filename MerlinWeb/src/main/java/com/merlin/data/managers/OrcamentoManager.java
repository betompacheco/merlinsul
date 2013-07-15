package com.merlin.data.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.merlin.data.DataBase;
import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.dto.OrcamentoDTO;

public class OrcamentoManager {

    public boolean update(OrcamentoDTO bean) {
        boolean ok = true;
        OrcamentoDTO pb = (OrcamentoDTO) bean;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update orcamento set " + "valororcamento=?, " + "mes=?, " + "ano=?, " + "codigocondominio=?, " + "codigoservico=?, "
                    + "fundoreserva=?, " + "descricao=? " + "where codigoorcamento=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setDouble(1, pb.getValorOrcamento().doubleValue());
            st.setInt(2, pb.getMes().intValue());
            st.setInt(3, pb.getAno().intValue());
            st.setInt(4, pb.getCodigoCondominio().intValue());
            st.setInt(5, pb.getCodigoServico().intValue());
            st.setDouble(6, pb.getFundoReserva().doubleValue());
            st.setString(7, pb.getDescricaoServico());
            st.setInt(8, pb.getCodigoOrcamento().intValue());
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

    public boolean insert(OrcamentoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {



            String qry = "insert into Orcamento (codigoorcamento, valororcamento, mes, ano, codigocondominio, codigoservico, fundoreserva, descricao) "
                    + "values (?,?,?,?,?,?,?,?) ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoOrcamento().intValue());
            st.setDouble(2, pb.getValorOrcamento().doubleValue());
            st.setInt(3, pb.getMes().intValue());
            st.setInt(4, pb.getAno().intValue());
            st.setInt(5, pb.getCodigoCondominio().intValue());
            st.setInt(6, pb.getCodigoServico().intValue());
            st.setDouble(7, pb.getFundoReserva().doubleValue());
            st.setString(8, pb.getDescricaoServico());
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

    public List select(int ano, int mes, int condominio, int orcamento) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            qry = "select * from orcamento ";
            String filtro = "";
            List param = new ArrayList();

            if (ano > -1) {
                filtro += " ano=?";
                param.add(new Integer(ano));
            }
            if (mes > -1) {
                filtro += (filtro.length() > 0 ? " AND " : "") + " mes=?";
                param.add(new Integer(mes));
            }
            if (condominio > -1) {
                filtro += (filtro.length() > 0 ? " AND " : "") + " codigocondominio=? ";
                param.add(new Integer(condominio));
            }
            if (orcamento > -1) {
                filtro += (filtro.length() > 0 ? " AND " : "") + " codigoorcamento=? ";
                param.add(new Integer(orcamento));
            }
            qry += (filtro.length() > 0 ? " WHERE " : "") + filtro + " ORDER BY codigocondominio, ano, mes";
            st = con.prepareStatement(qry);
            for (int i = 0; i < param.size(); i++) {
                st.setInt(i + 1, ((Integer) param.get(i)).intValue());
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrcamentoDTO pb = new OrcamentoDTO();
                pb.setCodigoOrcamento(new Integer(rs.getInt("codigoorcamento")));
                pb.setValorOrcamento(new Double(rs.getDouble("valororcamento")));
                pb.setMes(new Integer(rs.getInt("mes")));
                pb.setAno(new Integer(rs.getInt("ano")));
                pb.setCodigoCondominio(new Integer(rs.getInt("codigocondominio")));
                pb.setCodigoServico(new Integer(rs.getInt("codigoservico")));
                pb.setFundoReserva(new Double(rs.getDouble("fundoreserva")));
                pb.setDescricaoServico(rs.getString("descricao"));
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
        CondominioManager cm = new CondominioManager();
        List lista = cm.select("");
        HashMap cond = new HashMap();
        for (int i = 0; i < lista.size(); i++) {
            {
                CondominioDTO c = (CondominioDTO) lista.get(i);
                cond.put(c.getCodigocondominio(), c);
            }

            for (int j = 0; j < retorno.size(); j++) {
                OrcamentoDTO orc = (OrcamentoDTO) retorno.get(j);
                orc.setCondominio((CondominioDTO) cond.get(orc.getCodigoCondominio()));
            }
        }

        return retorno;
    }

    public OrcamentoDTO select(int filtro) {
        List lista = select(-1, -1, -1, filtro);
        if (lista.size() > 0) {
            return (OrcamentoDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public boolean delete(OrcamentoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from  orcamento where codigoOrcamento=?  ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoOrcamento().intValue());
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

    public OrcamentoDTO getNewBean() {
        OrcamentoDTO bean = new OrcamentoDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoorcamento) orcamento from orcamento ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("orcamento");
            }

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        bean.setCodigoOrcamento(new Integer(codigo + 1));
        return bean;
    }
}
