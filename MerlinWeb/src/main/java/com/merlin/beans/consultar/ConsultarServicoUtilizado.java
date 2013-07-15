package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirServicoUtilizado;
import com.merlin.data.dto.ServicoUtilizadoDTO;
import com.merlin.data.managers.ServicoUtilizadoManager;

public class ConsultarServicoUtilizado {

    // Campos usados para a consulta
    private String ano;
    private String mes;
    private int codigoApartamento;
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        ServicoUtilizadoManager suma = new ServicoUtilizadoManager();
        lista = suma.selectPorApartamento(codigoApartamento, Integer.parseInt(mes), Integer.parseInt(ano));
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o servico que foi clicado
        ServicoUtilizadoDTO servicoUtilizado = (ServicoUtilizadoDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirServico
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirServicoUtilizado isu = new IncluirServicoUtilizado();
        map.put("incluirServicoUtilizado", isu);

        isu.setServicoUtilizado(servicoUtilizado);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o servico que foi clicado
        ServicoUtilizadoDTO servicoUtilizado = (ServicoUtilizadoDTO) lista.get(dados.getRowIndex());
        ServicoUtilizadoManager sm = new ServicoUtilizadoManager();
        sm.delete(servicoUtilizado);
        lista.remove(servicoUtilizado);

    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public DataModel getDados() {
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }

    public int getCodigoApartamento() {
        return codigoApartamento;
    }

    public List getLista() {
        return lista;
    }

    public void setCodigoApartamento(int codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }
}
