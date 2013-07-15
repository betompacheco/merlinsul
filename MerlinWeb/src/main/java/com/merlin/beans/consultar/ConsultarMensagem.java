package com.merlin.beans.consultar;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.merlin.beans.incluir.IncluirMensagem;
import com.merlin.data.dto.MensagemDTO;
import com.merlin.data.managers.MensagemManager;

public class ConsultarMensagem {
    // campos para a consulta

    private String criterio = "";
    private DataModel dados;
    private List lista;

    public void doConsultar() {
        MensagemManager em = new MensagemManager();
        lista = em.select(criterio);
        dados = new ListDataModel(lista);
    }

    public String doEditar() {
        // pega o endere�o que foi clicado
        MensagemDTO Mensagem = (MensagemDTO) lista.get(dados.getRowIndex());

        // pega o contexto e adiciona ao request o mamagedbean de incluirMensagem
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestMap();
        IncluirMensagem ie = new IncluirMensagem();
        map.put("incluirMensagem", ie);

        ie.setMensagemDTO(Mensagem);

        lista.removeAll(lista);

        // retornando editar, no faces-config.xml deve direcionar para a tela de inclus�o
        return "editar";
    }

    public void doExcluir() {
        // pega o Mensagem que foi clicado
        MensagemDTO Mensagem = (MensagemDTO) lista.get(dados.getRowIndex());
        MensagemManager em = new MensagemManager();
        em.delete(Mensagem);
        lista.remove(Mensagem);

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
