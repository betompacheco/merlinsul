package com.merlin.data.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.merlin.data.DataBase;
import com.merlin.data.dto.ServicoDTO;
import com.merlin.data.dto.ServicoUtilizadoDTO;
import com.merlin.data.dto.ServicoUtilizadoReportDTO;

public class ServicoUtilizadoManager {

    public boolean update(ServicoUtilizadoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update servicoutilizado set codigoapartamento=?, " + "codigoservico=?, "
                    + "datautilizacao=? where codigoServicoUtilizado=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoApartamento().intValue());
            st.setInt(2, pb.getCodigoServico().intValue());
            st.setDate(3, new java.sql.Date(pb.getDataUtilizacao().getTime()));
            st.setInt(4, pb.getCodigoServicoUtilizado().intValue());
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

    public boolean insert(ServicoUtilizadoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into servicoutilizado ( codigoapartamento, codigoservico, datautilizacao) " + "values (?,?,?)";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoApartamento().intValue());
            st.setInt(2, pb.getCodigoServico().intValue());
            st.setDate(3, new java.sql.Date(pb.getDataUtilizacao().getTime()));
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

    public List select(Object filtro) {
        ServicoManager sm = new ServicoManager();
        Map ls = sm.selectHash();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        try {
            if (filtro instanceof Integer) {
                qry = "select s.*, a.numeroapartamento  from servicoutilizado s, apartamento a where s.codigoapartamento = a.codigoapartamento and s.codigoServicoUtilizado like ? order by codigoServicoUtilizado";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServicoUtilizadoDTO pb = new ServicoUtilizadoDTO();
                pb.setCodigoServicoUtilizado(new Integer(rs.getInt("codigoServicoUtilizado")));
                pb.setCodigoApartamento(new Integer(rs.getInt("numeroapartamento")));
                // Recebe o numero em vez do codigo, para ficar melhor de ver
                pb.setCodigoServico(new Integer(rs.getInt("codigoservico")));
                pb.setDataUtilizacao(rs.getDate("datautilizacao"));
                pb.setServico((ServicoDTO) ls.get(pb.getCodigoServico()));
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

    public List selectPorApartamento(int apartamento, int mes, int ano) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st;
        GregorianCalendar dataInicial = new GregorianCalendar(ano, mes, 1);
        GregorianCalendar dataFinal = new GregorianCalendar(ano, mes, 1);
        dataFinal.add(GregorianCalendar.MONTH, 1);
        dataFinal.add(GregorianCalendar.DATE, -1);
        try {
            qry = "select * from servicoutilizado where codigoapartamento = ? and datautilizacao between ? and ? ";
            st = con.prepareStatement(qry);
            st.setInt(1, apartamento);
            st.setDate(2, new java.sql.Date(dataInicial.getTimeInMillis()));
            st.setDate(3, new java.sql.Date(dataFinal.getTimeInMillis()));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServicoUtilizadoDTO pb = new ServicoUtilizadoDTO();
                pb.setCodigoServicoUtilizado(new Integer(rs.getInt("codigoServicoUtilizado")));
                pb.setCodigoApartamento(new Integer(rs.getInt("codigoapartamento")));
                pb.setCodigoServico(new Integer(rs.getInt("codigoservico")));
                pb.setDataUtilizacao(rs.getDate("datautilizacao"));
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
        ServicoManager sm = new ServicoManager();
        List servicos = sm.select("");
        for (int i = 0; i < retorno.size(); i++) {
            ServicoUtilizadoDTO su = (ServicoUtilizadoDTO) retorno.get(i);
            for (int j = 0; j < servicos.size(); j++) {
                ServicoDTO servicoDTO = (ServicoDTO) servicos.get(j);
                if (su.getCodigoServico().equals(servicoDTO.getCodigoServico())) {
                    su.setServico(servicoDTO);
                    break;
                }
            }
        }

        return retorno;
    }

    public List select(String filtro) {
        return select((Object) filtro);
    }

    public ServicoUtilizadoDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (ServicoUtilizadoDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public boolean delete(ServicoUtilizadoDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from servicoutilizado where codigoServicoUtilizado = ?";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoServicoUtilizado().intValue());
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

    public ServicoUtilizadoDTO getNewBean() {
        ServicoUtilizadoDTO bean = new ServicoUtilizadoDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoServicoutilizado) from ServicoUtilizado ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigoServicoUtilizado");
            }

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        bean.setCodigoServico(new Integer(codigo + 1));
        return bean;
    }

    public List selectReportServicosUtilizados(int ano, int mes, int apartamento) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        java.util.Date dtInicial = null;
        java.util.Date dtFinal = null;

        if (ano > -1) {
            GregorianCalendar gc = null;
            if (mes > -1) {
                gc = new GregorianCalendar(ano, mes, 1, 0, 0, 0);
                dtInicial = gc.getTime();
                gc.add(GregorianCalendar.MONTH, 1);
            } else {
                gc = new GregorianCalendar(ano, 0, 1, 0, 0, 0);
                dtInicial = gc.getTime();
                gc.add(GregorianCalendar.YEAR, 1);
            }
            gc.add(GregorianCalendar.DATE, -1);
            gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
            gc.set(GregorianCalendar.MINUTE, 59);
            gc.set(GregorianCalendar.SECOND, 59);
            dtFinal = gc.getTime();
        }

        String qry;
        PreparedStatement st;
        List parametros = new ArrayList();
        try {
            qry = "select c.nomecondominio, p.nomeproprietario, a.numeroapartamento, s.nomeservico, su.* "
                    + " from servicoutilizado su, condominio c, proprietario p, apartamento a, servico s  where "
                    + "su.codigoapartamento = a.codigoapartamento and " + "su.codigoservico = s.codigoservico and "
                    + "a.codigoproprietario = p.codigoproprietario and " + "a.codigocondominio = c.codigocondominio";

            if (ano > -1) {
                qry += " and dataUtilizacao between ? and ?  ";
                parametros.add(dtInicial);
                parametros.add(dtFinal);
            }
            if (apartamento > 0) {
                qry += " and codigoapartamento=? ";
                parametros.add(new Integer(apartamento));
            }
            // parametros.add(new java.util.Date());
            qry += " order by nomecondominio, nomeproprietario ";
            st = con.prepareStatement(qry);
            for (int i = 0; i < parametros.size(); i++) {
                Object ob = parametros.get(i);
                if (ob instanceof java.util.Date) {
                    st.setDate(i + 1, new java.sql.Date(((java.util.Date) ob).getTime()));
                } else if (ob instanceof Integer) {
                    st.setInt(i + 1, ((Integer) ob).intValue());
                }
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServicoUtilizadoReportDTO sur = new ServicoUtilizadoReportDTO();
                sur.setNomeCondominio(rs.getString("nomecondominio"));
                sur.setNomeProprietario(rs.getString("nomeproprietario"));
                sur.setNumeroApartamento(rs.getString("numeroApartamento"));
                sur.setNomeServico(rs.getString("nomeServico"));
                sur.setDataUtilizacao(rs.getDate("dataUtilizacao"));
                retorno.add(sur);
            }
            rs.close();

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
