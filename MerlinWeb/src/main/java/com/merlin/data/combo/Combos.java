package com.merlin.data.combo;

import com.merlin.data.dto.ApartamentoDTO;
import com.merlin.data.dto.CondominioDTO;
import com.merlin.data.dto.MensagemDTO;
import com.merlin.data.dto.ProprietarioDTO;
import com.merlin.data.dto.ServicoDTO;
import com.merlin.data.managers.ApartamentoManager;
import com.merlin.data.managers.CondominioManager;
import com.merlin.data.managers.MensagemManager;
import com.merlin.data.managers.ProprietarioManager;
import com.merlin.data.managers.ServicoManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.faces.model.SelectItem;

public class Combos {

    public List getComboMensagem() {
        MensagemManager cm = new MensagemManager();
        List mensagems = cm.select("");
        List rcombo = new ArrayList();
        for (int i = 0; i < mensagems.size(); i++) {
            MensagemDTO mensagem = (MensagemDTO) mensagems.get(i);
            rcombo.add(new SelectItem(mensagem.getCodigoMensagem(), mensagem.getTextoMensagem().substring(0, Math.min(mensagem.getTextoMensagem().length(), 60)) + " ..."));
        }
        return rcombo;
    }

    public List getComboCondominio() {
        CondominioManager cm = new CondominioManager();
        List condominios = cm.select("");
        List rcombo = new ArrayList();
        rcombo.add(new SelectItem("", ""));
        for (int i = 0; i < condominios.size(); i++) {
            CondominioDTO condominio = (CondominioDTO) condominios.get(i);
            rcombo.add(new SelectItem(condominio.getCodigocondominio(), condominio.getNomecondominio()));
        }
        return rcombo;
    }

    public List getComboProprietario() {
        ProprietarioManager pm = new ProprietarioManager();
        List proprietarios = pm.select("");
        List rcombo = new ArrayList();
        rcombo.add(new SelectItem("", ""));
        for (int i = 0; i < proprietarios.size(); i++) {
            ProprietarioDTO proprietario = (ProprietarioDTO) proprietarios.get(i);
            rcombo.add(new SelectItem(proprietario.getCodigoproprietario(), proprietario.getNomeproprietario()));
        }
        return rcombo;
    }

    public List getComboProprietarioZero() {
        ProprietarioManager pm = new ProprietarioManager();
        List proprietarios = pm.select("");
        List rcombo = new ArrayList();
        rcombo.add(new SelectItem(new Integer(0), ""));
        for (int i = 0; i < proprietarios.size(); i++) {
            ProprietarioDTO proprietario = (ProprietarioDTO) proprietarios.get(i);
            rcombo.add(new SelectItem(proprietario.getCodigoproprietario(), proprietario.getNomeproprietario()));
        }
        return rcombo;
    }

    public List getComboApartamento() {
        ApartamentoManager am = new ApartamentoManager();
        CondominioManager cm = new CondominioManager();
        List apartamentos = am.select("");
        List rcombo = new ArrayList();
        List condominios = cm.select("");
        HashMap cond = new HashMap();

        for (int i = 0; i < condominios.size(); i++) {
            CondominioDTO condominio = (CondominioDTO) condominios.get(i);
            cond.put(condominio.getCodigocondominio(), condominio);
        }

        rcombo.add(new SelectItem("", ""));

        for (int i = 0; i < apartamentos.size(); i++) {
            ApartamentoDTO apartamento = (ApartamentoDTO) apartamentos.get(i);
            CondominioDTO condominio = (CondominioDTO) cond.get(apartamento.getCodigocondominio());
            rcombo.add(new SelectItem(apartamento.getCodigoapartamento(), condominio.getNomecondominio() + " - "
                    + apartamento.getNumeroapartamento().toString()));
        }
        Collections.sort(rcombo, new Comparator() {
            public int compare(Object arg0, Object arg1) {
                SelectItem s1 = (SelectItem) arg0;
                SelectItem s2 = (SelectItem) arg1;
                return s1.getLabel().compareTo(s2.getLabel());
            }
        });

        return rcombo;
    }

    public List getComboApartamentoZero() {
        ApartamentoManager am = new ApartamentoManager();
        CondominioManager cm = new CondominioManager();
        List apartamentos = am.select("");
        List rcombo = new ArrayList();
        List condominios = cm.select("");
        HashMap cond = new HashMap();

        for (int i = 0; i < condominios.size(); i++) {
            CondominioDTO condominio = (CondominioDTO) condominios.get(i);
            cond.put(condominio.getCodigocondominio(), condominio);
        }

        rcombo.add(new SelectItem(new Integer(0), ""));

        for (int i = 0; i < apartamentos.size(); i++) {
            ApartamentoDTO apartamento = (ApartamentoDTO) apartamentos.get(i);
            CondominioDTO condominio = (CondominioDTO) cond.get(apartamento.getCodigocondominio());
            rcombo.add(new SelectItem(apartamento.getCodigoapartamento(), condominio.getNomecondominio() + " - "
                    + apartamento.getNumeroapartamento().toString()));
        }
        Collections.sort(rcombo, new Comparator() {
            public int compare(Object arg0, Object arg1) {
                SelectItem s1 = (SelectItem) arg0;
                SelectItem s2 = (SelectItem) arg1;
                return s1.getLabel().compareTo(s2.getLabel());
            }
        });

        return rcombo;
    }

    public List getComboServico() {
        ServicoManager sm = new ServicoManager();
        List servicos = sm.select("");
        List rcombo = new ArrayList();
        rcombo.add(new SelectItem("", ""));
        for (int i = 0; i < servicos.size(); i++) {
            ServicoDTO servico = (ServicoDTO) servicos.get(i);
            rcombo.add(new SelectItem(servico.getCodigoServico(), servico.getNomeServico()));
        }

        return rcombo;
    }
}
