package com.merlin.data.options;

import java.util.List;

import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.managers.CondominioManager;

public class CondominioOptions {

    public Object[] getOptions() {
        CondominioManager pm = new CondominioManager();
        List dados = pm.select(null);
        Object[] ops = new Object[dados.size()];
        for (int i = 0; i < dados.size(); i++) {
            CondominioDTO p = (CondominioDTO) dados.get(i);
            ops[i] = new String[]{p.getCodigocondominio().toString(), p.getNomecondominio()};
        }
        return ops;
    }

    public String getText(String value) {
        CondominioManager pm = new CondominioManager();
        List dados = pm.select(null);
        String retorno = "";
        for (int i = 0; i < dados.size(); i++) {
            CondominioDTO p = (CondominioDTO) dados.get(i);
            if (value.equals(p.getCodigocondominio().toString())) {
                retorno = p.getNomecondominio();
                break;
            }
        }
        return retorno;
    }

    public String[] getOption(String value) {
        CondominioManager pm = new CondominioManager();
        List dados = pm.select(null);
        String[] op = null;
        for (int i = 0; i < dados.size(); i++) {
            CondominioDTO p = (CondominioDTO) dados.get(i);
            if (value.equals(p.getCodigocondominio().toString())) {
                op = new String[]{p.getCodigocondominio().toString(), p.getNomecondominio()};
                break;
            }

        }
        return op;
    }
}
