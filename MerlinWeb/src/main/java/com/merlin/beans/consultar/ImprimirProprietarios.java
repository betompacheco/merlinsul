package com.merlin.beans.consultar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import com.merlin.data.managers.ProprietarioManager;

public class ImprimirProprietarios {

    private boolean flagOk = false;

    public void doImprimir() {
        ProprietarioManager pm = new ProprietarioManager();
        List proprietarios = pm.selectReport();

        FacesContext fc = FacesContext.getCurrentInstance();
        List rpt = new ArrayList();
        rpt.add("proprietarios.jrxml"); // nome do rpt
        rpt.add(proprietarios); // datasource
        rpt.add(new HashMap()); // parametros do rpt
        fc.getExternalContext().getSessionMap().put("relatorio", rpt);
        flagOk = true;

    }

    public boolean isFlagOk() {
        return flagOk;
    }

    public void setFlagOk(boolean flagOk) {
        this.flagOk = flagOk;
    }
}
