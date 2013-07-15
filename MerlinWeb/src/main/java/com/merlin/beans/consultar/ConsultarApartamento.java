package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirApartamento;
import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.managers.ApartamentoManager;

public class ConsultarApartamento {
    // campos para a consulta

    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        ApartamentoManager am = new ApartamentoManager();
        lista = am.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o apartamento que foi clicado
        ApartamentoDTO apartamento = (ApartamentoDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirApartamentoo
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirApartamento ia = new IncluirApartamento();
        map.put("incluirApartamento", ia);

        ia.setApartamento(apartamento);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o apartamento que foi clicado
        ApartamentoDTO apartamento = (ApartamentoDTO) lista.get(dados.getRowIndex());
        ApartamentoManager am = new ApartamentoManager();
        am.delete(apartamento);
        lista.remove(apartamento);

    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public DataModel getDados() {
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
}
