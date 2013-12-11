package com.merlin.data.managers;

import com.merlin.data.DataBase;
import com.merlin.data.dto.ProprietarioDTO;
import com.merlin.data.dto.ProprietarioReportDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProprietarioManager {

    private final static Logger logger = Logger.getLogger(ProprietarioManager.class.getName());

    public boolean update(ProprietarioDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update proprietario set " + "nomeProprietario=?, " + "sexo=?, " + "identidade=?, " + "cpf=?, " + "profissao=?, "
                    + "filiacao=?, " + "telresidencial=?, " + "telcomercial=?, " + "telcelular=? " + "where codigoproprietario=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setString(1, pb.getNomeproprietario());
            st.setString(2, pb.getSexo());
            st.setString(3, pb.getIdentidade());
            st.setString(4, pb.getCpf());
            st.setString(5, pb.getProfissao());
            st.setString(6, pb.getFiliacao());
            st.setString(7, pb.getTelResidencial());
            st.setString(8, pb.getTelComercial());
            st.setString(9, pb.getTelCelular());
            st.setInt(10, pb.getCodigoproprietario().intValue());
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

    public boolean insert(ProprietarioDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into proprietario (nomeProprietario, sexo, identidade, cpf, profissao, filiacao, telresidencial, telcomercial, telcelular, codigoproprietario) "
                    + "values (?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setString(1, pb.getNomeproprietario());
            st.setString(2, pb.getSexo());
            st.setString(3, pb.getIdentidade());
            st.setString(4, pb.getCpf());
            st.setString(5, pb.getProfissao());
            st.setString(6, pb.getFiliacao());
            st.setString(7, pb.getTelResidencial());
            st.setString(8, pb.getTelComercial());
            st.setString(9, pb.getTelCelular());
            st.setInt(10, pb.getCodigoproprietario().intValue());
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
                qry = "select * from proprietario where nomeproprietario like ? order by nomeproprietario";
                st = con.prepareStatement(qry);
                st.setString(1, filtro + "%");
            } else if (filtro instanceof Integer) {
                qry = "select * from proprietario where codigoproprietario = ? order by nomeproprietario";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProprietarioDTO pb = new ProprietarioDTO();
                pb.setCodigoproprietario(new Integer(rs.getInt("codigoproprietario")));
                pb.setNomeproprietario(rs.getString("nomeproprietario"));
                pb.setSexo(rs.getString("sexo"));
                pb.setIdentidade(rs.getString("identidade"));
                pb.setCpf(rs.getString("cpf"));
                pb.setProfissao(rs.getString("profissao"));
                pb.setFiliacao(rs.getString("filiacao"));
                pb.setTelResidencial(rs.getString("telresidencial"));
                pb.setTelComercial(rs.getString("telcomercial"));
                pb.setTelCelular(rs.getString("telcelular"));
                retorno.add(pb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
        return retorno;
    }

    public ProprietarioDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (ProprietarioDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public List select(String filtro) {
        return select((Object) filtro);
    }

    public boolean delete(ProprietarioDTO pb) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from  proprietario where codigoproprietario=?  ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoproprietario().intValue());
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

    public ProprietarioDTO getNewBean() {
        logger.log(Level.INFO, "Obtendo o novo bean");
        ProprietarioDTO bean = new ProprietarioDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoproprietario) as codigoproprietario from proprietario ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigoproprietario");
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

        bean.setCodigoproprietario(new Integer(codigo + 1));
        return bean;
    }

    public List selectReport() {

        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        String qry;
        PreparedStatement st = null;
        try {
            qry = "select c.nomeCondominio, a.numeroApartamento, p.* " + " from proprietario p, condominio c, apartamento a "
                    + " where a.codigoproprietario=p.codigoproprietario and a.codigocondominio=c.codigocondominio ";
            st = con.prepareStatement(qry);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProprietarioReportDTO pb = new ProprietarioReportDTO();
                pb.setNomeCondominio(rs.getString("nomecondominio"));
                pb.setNumeroApartamento(rs.getString("numeroapartamento"));
                pb.setCodigoproprietario(new Integer(rs.getInt("codigoproprietario")));
                pb.setNomeproprietario(rs.getString("nomeproprietario"));
                pb.setSexo(rs.getString("sexo"));
                pb.setIdentidade(rs.getString("identidade"));
                pb.setCpf(rs.getString("cpf"));
                pb.setProfissao(rs.getString("profissao"));
                pb.setFiliacao(rs.getString("filiacao"));
                pb.setTelResidencial(rs.getString("telresidencial"));
                pb.setTelComercial(rs.getString("telcomercial"));
                pb.setTelCelular(rs.getString("telcelular"));
                retorno.add(pb);
            }

        } catch (SQLException e) {
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
