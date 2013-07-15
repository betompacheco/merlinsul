package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirProprietario;
import com.merlin.data.dto.ProprietarioDTO;
import com.merlin.data.managers.ProprietarioManager;

public class ConsultarProprietario {
    // campos para a consulta

    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        ProprietarioManager pm = new ProprietarioManager();
        lista = pm.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o proprietario que foi clicado
        ProprietarioDTO proprietario = (ProprietarioDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirProprietario
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirProprietario ip = new IncluirProprietario();
        map.put("incluirProprietario", ip);

        ip.setProprietario(proprietario);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o proprietario que foi clicado
        ProprietarioDTO proprietario = (ProprietarioDTO) lista.get(dados.getRowIndex());
        ProprietarioManager pm = new ProprietarioManager();
        pm.delete(proprietario);
        lista.remove(proprietario);

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
