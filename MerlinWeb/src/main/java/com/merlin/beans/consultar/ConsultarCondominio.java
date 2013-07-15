package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirCondominio;
import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.managers.CondominioManager;

public class ConsultarCondominio {
    // campos para a consulta

    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        CondominioManager cm = new CondominioManager();
        lista = cm.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o condominio que foi clicado
        CondominioDTO condominio = (CondominioDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirCondominio
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirCondominio ic = new IncluirCondominio();
        map.put("incluirCondominio", ic);

        ic.setCondominio(condominio);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o condominio que foi clicado
        CondominioDTO condominio = (CondominioDTO) lista.get(dados.getRowIndex());
        CondominioManager cm = new CondominioManager();
        cm.delete(condominio);
        lista.remove(condominio);

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
