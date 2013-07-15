package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirOrcamento;
import com.merlin.data.dto.OrcamentoDTO;
import com.merlin.data.managers.OrcamentoManager;

public class ConsultarOrcamento {
    // campos para a consulta

    private String ano;
    private String mes;
    private int codigoCondominio = 1;
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        OrcamentoManager om = new OrcamentoManager();
        int a = ano != null && !"".equals(ano) ? Integer.parseInt(ano) : -1;
        int m = mes != null && !"".equals(mes) ? Integer.parseInt(mes) : -1;
        lista = om.select(a, m, codigoCondominio, -1);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o apartamento que foi clicado
        OrcamentoDTO orcamento = (OrcamentoDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirApartamentoo
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirOrcamento ia = new IncluirOrcamento();
        map.put("incluirOrcamento", ia);

        ia.setOrcamento(orcamento);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o apartamento que foi clicado
        OrcamentoDTO orcamento = (OrcamentoDTO) lista.get(dados.getRowIndex());
        OrcamentoManager am = new OrcamentoManager();
        am.delete(orcamento);
        lista.remove(orcamento);

    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getCodigoCondominio() {
        return codigoCondominio;
    }

    public void setCodigoCondominio(int codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
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
}
