/*
 * Created on Feb 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirServico;
import com.merlin.data.dto.ServicoDTO;
import com.merlin.data.managers.ServicoManager;

/**
 * @author Leonardo Lopes
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConsultarServico {

    // Campos usados para a consulta
    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        ServicoManager sm = new ServicoManager();
        lista = sm.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o servico que foi clicado
        ServicoDTO servico = (ServicoDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirServico
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirServico is = new IncluirServico();
        map.put("incluirServico", is);

        is.setServico(servico);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o servico que foi clicado
        ServicoDTO servico = (ServicoDTO) lista.get(dados.getRowIndex());
        ServicoManager sm = new ServicoManager();
        sm.delete(servico);
        lista.remove(servico);

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
