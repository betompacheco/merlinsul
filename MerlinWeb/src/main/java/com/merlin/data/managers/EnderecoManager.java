package com.merlin.data.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.merlin.data.DataBase;
import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.dto.EnderecoDTO;

public class EnderecoManager {

    private boolean updateCobrancaFalse(int codigoapartamento) {
        boolean ok = true;
        Connection con = DataBase.getConnection();
        try {
            String qry = "update endereco set " + "enderecocobranca=? " + "where codigoapartamento=? ";
            PreparedStatement st = con.prepareStatement(qry);

            st.setBoolean(1, false);
            st.setInt(2, codigoapartamento);
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

    public boolean update(EnderecoDTO bean) {
        boolean ok = true;
        EnderecoDTO pb = (EnderecoDTO) bean;
        if (bean.isEnderecoCobranca()) {
            updateCobrancaFalse(bean.getCodigoapartamento().intValue());
        }
        Connection con = DataBase.getConnection();
        try {
            String qry = "update endereco set " + "codigoapartamento=?, " + "logradouro=?, " + "complemento=?, " + "bairro=?, " + "cidade=?, "
                    + "cep=?, " + "uf=?, " + "enderecocobranca=? " + "where codigoendereco=? ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoapartamento().intValue());
            st.setString(2, pb.getLogradouro());
            st.setString(3, pb.getComplemento());
            st.setString(4, pb.getBairro());
            st.setString(5, pb.getCidade());
            st.setString(6, pb.getCep());
            st.setString(7, pb.getUf());
            st.setBoolean(8, pb.isEnderecoCobranca());
            st.setInt(9, pb.getCodigoendereco().intValue());
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

    public boolean insert(EnderecoDTO bean) {
        boolean ok = true;
        EnderecoDTO pb = (EnderecoDTO) bean;
        if (bean.isEnderecoCobranca()) {
            updateCobrancaFalse(bean.getCodigoapartamento().intValue());
        }
        Connection con = DataBase.getConnection();
        try {
            String qry = "insert into Endereco (codigoendereco, codigoapartamento, logradouro, complemento, bairro, cidade, uf, cep, enderecocobranca) "
                    + "values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoendereco().intValue());
            st.setInt(2, pb.getCodigoapartamento().intValue());
            st.setString(3, pb.getLogradouro());
            st.setString(4, pb.getComplemento());
            st.setString(5, pb.getBairro());
            st.setString(6, pb.getCidade());
            st.setString(7, pb.getUf());
            st.setString(8, pb.getCep());
            st.setBoolean(9, pb.isEnderecoCobranca());
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
                qry = "select * from endereco WHERE logradouro like ?";
                st = con.prepareStatement(qry);
                st.setString(1, filtro + "%");
            } else if (filtro instanceof Integer) {
                qry = "select * from endereco WHERE codigoendereco = ?";
                st = con.prepareStatement(qry);
                st.setInt(1, ((Integer) filtro).intValue());
            } else {
                return null;
            }

            ApartamentoManager ap = new ApartamentoManager();
            List aps = ap.select("");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EnderecoDTO pb = new EnderecoDTO();
                pb.setCodigoendereco(new Integer(rs.getInt("codigoendereco")));
                pb.setCodigoapartamento(new Integer(rs.getInt("codigoapartamento")));
                pb.setLogradouro(rs.getString("logradouro"));
                pb.setComplemento(rs.getString("complemento"));
                pb.setBairro(rs.getString("bairro"));
                pb.setCidade(rs.getString("cidade"));
                pb.setUf(rs.getString("uf"));
                pb.setCep(rs.getString("cep"));
                pb.setEnderecoCobranca(rs.getBoolean("enderecoCobranca"));
                for (int i = 0; i < aps.size(); i++) {
                    ApartamentoDTO aDTO = (ApartamentoDTO) aps.get(i);
                    if (pb.getCodigoapartamento().equals(aDTO.getCodigoapartamento())) {
                        pb.setApartamento(aDTO);
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

    public List select(String filtro) {
        return select((Object) filtro);
    }

    public EnderecoDTO selectApartamento(Integer filtro) {
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        EnderecoDTO endRetorno = null;
        String qry;
        PreparedStatement st;
        try {
            qry = "select * from endereco where codigoapartamento = ?";

            st = con.prepareStatement(qry);
            st.setInt(1, filtro.intValue());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EnderecoDTO pb = new EnderecoDTO();
                pb.setCodigoendereco(new Integer(rs.getInt("codigoendereco")));
                pb.setCodigoapartamento(new Integer(rs.getInt("codigoapartamento")));
                pb.setLogradouro(rs.getString("logradouro"));
                pb.setComplemento(rs.getString("complemento"));
                pb.setBairro(rs.getString("bairro"));
                pb.setCidade(rs.getString("cidade"));
                pb.setUf(rs.getString("uf"));
                pb.setCep(rs.getString("cep"));
                pb.setEnderecoCobranca(rs.getBoolean("enderecoCobranca"));
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
        if (retorno.size() > 1) {
            for (int i = 0; i < retorno.size(); i++) {
                EnderecoDTO endereco = (EnderecoDTO) retorno.get(i);
                if (endereco.isEnderecoCobranca()) {
                    endRetorno = endereco;
                    break;
                }
            }
            if (endRetorno == null) {
                endRetorno = (EnderecoDTO) retorno.get(0);
            }
        } else {
            if (retorno.size() == 1) {
                endRetorno = (EnderecoDTO) retorno.get(0);
            }
        }
        return endRetorno;
    }

    public EnderecoDTO select(int filtro) {
        List lista = select(new Integer(filtro));
        if (lista.size() > 0) {
            return (EnderecoDTO) lista.get(0);
        } else {
            return null;
        }
    }

    public boolean delete(EnderecoDTO bean) {
        boolean ok = true;
        EnderecoDTO pb = (EnderecoDTO) bean;
        Connection con = DataBase.getConnection();
        try {
            String qry = "delete from  endereco where codigoendereco=?  ";
            PreparedStatement st = con.prepareStatement(qry);
            st.setInt(1, pb.getCodigoendereco().intValue());
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

    public EnderecoDTO getNewBean() {
        EnderecoDTO bean = new EnderecoDTO();
        Connection con = DataBase.getConnection();
        List retorno = new ArrayList();
        int codigo = 0;
        try {
            String qry = "select max(codigoendereco) from endereco ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            if (rs.next()) {
                codigo = rs.getInt("codigoendereco");
            }

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

        bean.setCodigoendereco(new Integer(codigo + 1));
        return bean;
    }
}
