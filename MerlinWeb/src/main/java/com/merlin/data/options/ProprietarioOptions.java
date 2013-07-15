package com.merlin.data.options;

import java.util.List;

import com.merlin.data.dto.ProprietarioDTO;
import com.merlin.data.managers.ProprietarioManager;

public class ProprietarioOptions {

    public Object[] getOptions() {
        ProprietarioManager pm = new ProprietarioManager();
        List dados = pm.select(null);
        Object[] ops = new Object[dados.size()];
        for (int i = 0; i < dados.size(); i++) {
            ProprietarioDTO p = (ProprietarioDTO) dados.get(i);
            ops[i] = new String[]{p.getCodigoproprietario().toString(), p.getNomeproprietario()};
        }
        return ops;
    }

    public String getText(String value) {
        ProprietarioManager pm = new ProprietarioManager();
        List dados = pm.select(null);
        String retorno = "";
        for (int i = 0; i < dados.size(); i++) {
            ProprietarioDTO p = (ProprietarioDTO) dados.get(i);
            if (value.equals(p.getCodigoproprietario().toString())) {
                retorno = p.getNomeproprietario();
                break;
            }
        }
        return retorno;
    }

    public String[] getOption(String value) {
        ProprietarioManager pm = new ProprietarioManager();
        List dados = pm.select(null);
        String[] op = null;
        for (int i = 0; i < dados.size(); i++) {
            ProprietarioDTO p = (ProprietarioDTO) dados.get(i);
            if (value.equals(p.getCodigoproprietario().toString())) {
                op = new String[]{p.getCodigoproprietario().toString(), p.getNomeproprietario()};
                break;
            }

        }
        return op;
    }

    public static void main(String[] args) {
    }
}
