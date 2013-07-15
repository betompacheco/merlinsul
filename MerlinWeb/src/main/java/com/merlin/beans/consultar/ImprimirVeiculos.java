/*
 * Created on 15/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.merlin.beans.consultar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import com.merlin.data.managers.VeiculoManager;

/**
 * @author Luis Henrique
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ImprimirVeiculos {

    private boolean flagOk = false;

    public void doImprimir() {
        VeiculoManager vm = new VeiculoManager();
        List veiculos = vm.selectReport();

        FacesContext fc = FacesContext.getCurrentInstance();
        List rptVeiculos = new ArrayList();
        rptVeiculos.add("veiculos.jrxml"); // nome do rpt
        rptVeiculos.add(veiculos); // datasource
        rptVeiculos.add(new HashMap()); // parametros do rpt
        fc.getExternalContext().getSessionMap().put("relatorio", rptVeiculos);
        flagOk = true;

    }

    public boolean isFlagOk() {
        return flagOk;
    }

    public void setFlagOk(boolean flagOk) {
        this.flagOk = flagOk;
    }
}
