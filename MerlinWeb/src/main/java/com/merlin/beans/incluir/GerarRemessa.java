package com.merlin.beans.incluir;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class GerarRemessa {

    private static final Logger logger = Logger.getLogger(GerarRemessa.class.getName());

    private String mes;
    private String ano;
    private boolean isForTest;
    private boolean flagOk = false;

    public GerarRemessa() {
        //configura valoes padrao na pagina
        Calendar c = new GregorianCalendar();
        mes = Integer.toString(c.get(Calendar.MONTH));
        ano = Integer.toString(c.get(Calendar.YEAR));
    }

    public void doGerar() {
        flagOk = true;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the isForTest
     */
    public boolean isIsForTest() {
        return isForTest;
    }

    /**
     * @param isForTest the isForTest to set
     */
    public void setIsForTest(boolean isForTest) {
        this.isForTest = isForTest;
    }

    public boolean isFlagOk() {
        return flagOk;
    }

    public void setFlagOk(boolean flagOk) {
        this.flagOk = flagOk;
    }

}
