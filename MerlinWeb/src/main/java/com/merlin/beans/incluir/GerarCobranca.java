package com.merlin.beans.incluir;

import com.merlin.data.managers.CobrancaManager;
import com.merlin.util.GeradorDeCobranca;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public class GerarCobranca {

    private Date dataVencimento;
    private Double multa;
    private String mensagem;
    private List dadosGerados;
    private DataModel dados;
    private boolean flagConfirma;

    public GerarCobranca() {

        dataVencimento = new GregorianCalendar().getTime();
        multa = 2.0;
    }

    public void doGerar() {
        if (existeCobranca()) {
            flagConfirma = true;
        } else {
            _gerar();
        }

    }

    public void confirma() {
        _gerar();
    }

    private void _gerar() {
        GeradorDeCobranca gerador = new GeradorDeCobranca();

        gerador.setDataVencimento(dataVencimento);
        gerador.setMulta(multa.doubleValue());
        gerador.gerarCobrancas();
        dadosGerados = gerador.getCobranca();
        dados = new ListDataModel(dadosGerados);

    }

    private boolean existeCobranca() {
        CobrancaManager cm = new CobrancaManager();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataVencimento);
        int ano = gc.get(GregorianCalendar.YEAR);
        int mes = gc.get(GregorianCalendar.MONTH);
        List l = cm.select(ano, mes, -1);
        return l.size() > 0;
    }

    public void doSalvar() {
        CobrancaManager cm = new CobrancaManager();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dataVencimento);
        int ano = gc.get(GregorianCalendar.YEAR);
        int mes = gc.get(GregorianCalendar.MONTH);
        cm.delete(ano, mes);
        cm.insert(dadosGerados);
        mensagem = "Cobranca armazenada";
        dados = null;
        dadosGerados = null;

    }

    private void doCancelar() {
        dataVencimento = null;
        multa = null;
        dadosGerados = null;
    }

    public boolean getTemDados() {
        if (dadosGerados == null) {
            return false;
        }
        return dadosGerados.size() > 0;
    }

    public List getDadosGerados() {
        return dadosGerados;
    }

    public void setDadosGerados(List dadosGerados) {
        this.dadosGerados = dadosGerados;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public boolean isFlagConfirma() {
        return flagConfirma;
    }

    public void setFlagConfirma(boolean flagConfirma) {
        this.flagConfirma = flagConfirma;
    }

    public DataModel getDados() {
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
}
