package com.merlin.data.options;

import java.util.List;

import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.managers.ApartamentoManager;

public class ApartamentoOptions {

    public Object[] getOptions() {
        ApartamentoManager pm = new ApartamentoManager();
        List dados = pm.select(null);
        Object[] ops = new String[dados.size()];
        for (int i = 0; i < dados.size(); i++) {
            ApartamentoDTO p = (ApartamentoDTO) dados.get(i);
            ops[i] = new String[]{p.getCodigoapartamento().toString(), p.getNumeroapartamento().toString()};
        }
        return ops;
    }

    public String getText(String value) {
        ApartamentoManager pm = new ApartamentoManager();
        List dados = pm.select(null);
        String retorno = "";
        for (int i = 0; i < dados.size(); i++) {
            ApartamentoDTO p = (ApartamentoDTO) dados.get(i);
            if (value.equals(p.getCodigoapartamento().toString())) {
                retorno = p.getNumeroapartamento().toString();
                break;
            }
        }
        return retorno;
    }

    public String[] getOption(String value) {
        ApartamentoManager pm = new ApartamentoManager();
        List dados = pm.select(null);
        String[] op = null;
        for (int i = 0; i < dados.size(); i++) {
            ApartamentoDTO p = (ApartamentoDTO) dados.get(i);
            if (value.equals(p.getCodigoapartamento().toString())) {
                op = new String[]{p.getCodigoapartamento().toString(), p.getNumeroapartamento().toString()};
                break;
            }

        }
        return op;
    }
}
