package com.merlin.tests;

import com.merlin.util.NumberCodeGenerator;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import junit.framework.TestCase;

public class NumberCodeGeneratorTest extends TestCase {

    public void testCalculaModulo11base9() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(9, ngc.calculaModulo11base9("239104761"));
        assertEquals(1, ngc.calculaModulo11base9("23918868104"));
        assertEquals(0, ngc.calculaModulo11base9("0"));
    }

    /**
     * Efetua o teste do Dígito de Autoconferência (DAC) do Bradesco
     */
    public void testCalculaDACdoBradesco() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals("P", ngc.calculaDACdoBradesco("1900000000001"));
        assertEquals("0", ngc.calculaDACdoBradesco("1900000000006"));
        assertEquals("8", ngc.calculaDACdoBradesco("1900000000002"));
    }

    /**
     * Efetua o teste do Dígito de Autoconferência (DAC) do HSBC
     */
    public void testCalculaDAC() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(9, ngc.calculaDAC("239104761"));
        assertEquals(1, ngc.calculaDAC("23918868104"));
        assertEquals(1, ngc.calculaDAC("23918827397"));
        assertEquals(1, ngc.calculaDAC("0"));
    }

    public void testCalculaDigitoverificadorBloco() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(6, ngc.calculaDigitoVerificadorBloco("237927850"));
        assertEquals(7, ngc.calculaDigitoVerificadorBloco("9000000002"));
        //Teste baseado no documento
        assertEquals(2, ngc.calculaDigitoVerificadorBloco("237900310"));
        assertEquals(3, ngc.calculaDigitoVerificadorBloco("4003177200"));
        assertEquals(5, ngc.calculaDigitoVerificadorBloco("2800952790"));
    }

    public void testNumberCodeGenerator() {

        NumberCodeGenerator ngc = new NumberCodeGenerator();

        assertEquals("19/00000000001-P", ngc.comporNossoNumero(1, "19"));
        assertEquals("19/00000000002-8", ngc.comporNossoNumero(2, "19"));
        assertEquals("19/00000000006-0", ngc.comporNossoNumero(6, "19"));
    }

    public void testFatorDeVencimento() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(1000, ngc.fatorVencimento(new Date("07/03/2000")));
        assertEquals(1002, ngc.fatorVencimento(new Date("07/05/2000")));
        assertEquals(1001, ngc.fatorVencimento(new Date("07/04/2000")));
        assertEquals(1667, ngc.fatorVencimento(new Date("05/01/2002")));
        assertEquals(3923, ngc.fatorVencimento(new Date("07/04/2008")));
        assertEquals(4758, ngc.fatorVencimento(new Date("10/17/2010")));
        assertEquals(4789, ngc.fatorVencimento(new Date("11/17/2010")));
        assertEquals(9999, ngc.fatorVencimento(new Date("02/21/2025")));
    }

    public void testDataJuliana() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        Calendar gc = new GregorianCalendar();
        gc.setTime(new Date("07/04/2008"));
        assertEquals(1868, ngc.dataJuliana(gc.getTime()));
    }

    /**
     * a conta do bradesco é - agencia 2785-5 conta corrente 7636-8 -
     * condomínio do edifício Merlin sul. O banco me deu esse código 4631965
     * .
     */
    public void testCodigoImpressoBradesco() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();

        ngc.setNumeroBanco(237); // default Bradesco
        ngc.setNumeroMoeda(9); // default Bradesco
        ngc.setVencimento(new Date("07/10/2014"));//10 de Maio de 2014
        ngc.setProcessamento(new Date("07/10/2014"));
        ngc.setNossoNumero("1"); // nao incluir os ultimos 3 digitos
        ngc.setAgencia(2785);
        ngc.setConta(7636);
        ngc.setCarteira("09");
        ngc.setCob(0); // default Bradesco
        ngc.setValor(100.26);
        ngc.generate();

        //assertEquals("23790.00769 36000.000004 00001.191428 1 61200000010026", ngc.getCodigoImpresso());
    }
}
