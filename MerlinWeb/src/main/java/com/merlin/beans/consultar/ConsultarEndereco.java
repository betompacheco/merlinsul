package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirEndereco;
import com.merlin.data.dto.EnderecoDTO;
import com.merlin.data.managers.EnderecoManager;

public class ConsultarEndereco {
    // campos para a consulta

    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        EnderecoManager em = new EnderecoManager();
        lista = em.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o endere�o que foi clicado
        EnderecoDTO endereco = (EnderecoDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirEndereco
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirEndereco ie = new IncluirEndereco();
        map.put("incluirEndereco", ie);

        ie.setEndereco(endereco);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o endereco que foi clicado
        EnderecoDTO endereco = (EnderecoDTO) lista.get(dados.getRowIndex());
        EnderecoManager em = new EnderecoManager();
        em.delete(endereco);
        lista.remove(endereco);

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
