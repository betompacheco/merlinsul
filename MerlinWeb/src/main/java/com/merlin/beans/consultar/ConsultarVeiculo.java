/*
 * Created on Feb 20, 2005
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

import com.merlin.beans.incluir.IncluirVeiculo;
import com.merlin.data.dto.VeiculoDTO;
import com.merlin.data.managers.VeiculoManager;

/**
 * @author Leonardo Lopes
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConsultarVeiculo {

    // Campos usados para a consulta
    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        VeiculoManager vm = new VeiculoManager();
        lista = vm.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o veiculo que foi clicado
        VeiculoDTO veiculo = (VeiculoDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirVeiculo
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirVeiculo iv = new IncluirVeiculo();
        map.put("incluirVeiculo", iv);

        iv.setVeiculo(veiculo);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o veiculo que foi clicado
        VeiculoDTO veiculo = (VeiculoDTO) lista.get(dados.getRowIndex());
        VeiculoManager vm = new VeiculoManager();
        vm.delete(veiculo);
        lista.remove(veiculo);

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
