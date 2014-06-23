package com.merlin.tests;

import com.merlin.util.NumberCodeGenerator;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import junit.framework.TestCase;

public class NumberCodeGeneratorTest extends TestCase {

    public void testCalculaModulo11() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(9, ngc.calculaModulo11("239104761"));
        assertEquals(1, ngc.calculaModulo11("23918868104"));
        assertEquals(0, ngc.calculaModulo11("0"));
    }

    /**
     * Efetua o teste do Dígito de Autoconferência (DAC)
     */
    public void testCalculaDAC() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(9, ngc.calculaDAC("239104761"));
        assertEquals(1, ngc.calculaDAC("23918868104"));
        assertEquals(1, ngc.calculaDAC("23918827397"));
        assertEquals(1, ngc.calculaDAC("0"));
    }

    public void testCalcDigitoBloco() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(3, ngc.calculaDigitoVerificadorBloco("4600000000"));
        assertEquals(5, ngc.calculaDigitoVerificadorBloco("0000123292"));
        assertEquals(1, ngc.calculaDigitoVerificadorBloco("399983512"));
        assertEquals(0, ngc.calculaDigitoVerificadorBloco("0009220192"));
        assertEquals(6, ngc.calculaDigitoVerificadorBloco("0476118682"));
        assertEquals(7, ngc.calculaDigitoVerificadorBloco("0200002391"));
        assertEquals(2, ngc.calculaDigitoVerificadorBloco("0010323292"));
        assertEquals(7, ngc.calculaDigitoVerificadorBloco("11"));
        assertEquals(4, ngc.calculaDigitoVerificadorBloco("22"));
        assertEquals(1, ngc.calculaDigitoVerificadorBloco("33"));
        assertEquals(8, ngc.calculaDigitoVerificadorBloco("44"));
        assertEquals(4, ngc.calculaDigitoVerificadorBloco("55"));
        assertEquals(1, ngc.calculaDigitoVerificadorBloco("66"));
    }

    public void testNumberCodeGenerator() {

        NumberCodeGenerator ngc = new NumberCodeGenerator();
        Long valor;
        // Teste temporario
        valor = new Long("1947");
        assertEquals(valor.longValue(), ngc.comporCodigoDocumento(1, 2606046, new GregorianCalendar(2011, Calendar.NOVEMBER, 20).getTime()));

        // COMPOSIÇÃO DO CÓDIGO DO DOCUMENTO COM USO DO TIPO IDENTIFICADOR “4”
        valor = new Long("239104761941");
        assertEquals(valor.longValue(), ngc.comporCodigoDocumento(239104761, 8351202, new GregorianCalendar(2008, Calendar.JULY, 4).getTime()));

        // COMPOSIÇÃO DO CÓDIGO DO DOCUMENTO COM USO DO TIPO IDENTIFICADOR “5”
        valor = new Long("239104761950");
        assertEquals(valor.longValue(), ngc.comporCodigoDocumento(239104761, 8351202, null));
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

    public void testCodigoImpressoHSBC() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();

        ngc.setNumeroBanco(399); // default HSBC
        ngc.setNumeroMoeda(9); // default HSBC
        ngc.setVencimento(new Date("07/04/2008"));
        ngc.setProcessamento(null);
        ngc.setNossoNumero("0000239104761"); // nao incluir os ultimos 3 digitos
        ngc.setAgencia(1227);
        ngc.setConta(8351202);
        ngc.setCob(0); // default HSBC
        ngc.setValor(1200.00);
        ngc.generate();
        assertEquals("39998.35121 02000.023917 04761.186826 4 39230000120000", ngc.getCodigoImpresso());
    }

    /**
     * a conta do bradesco é - agencia 2785-5 conta corrente 7636-8 - condomínio
     * do edifício Merlin sul. O banco me deu esse código 4631965 .
     */
    public void testCodigoImpressoBradesco() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();

        ngc.setNumeroBanco(237); // default Bradesco
        ngc.setNumeroMoeda(9); // default Bradesco
        ngc.setVencimento(new Date("07/10/2014"));//10 de Maio de 2014
        ngc.setProcessamento(new Date("07/10/2014"));
        ngc.setNossoNumero("00000608842"); // nao incluir os ultimos 3 digitos
        ngc.setAgencia(2785);
        ngc.setConta(7636);
        ngc.setCob(0); // default Bradesco
        ngc.setValor(100.26);
        ngc.generate();

        System.out.println(ngc.getCodigoImpresso());

        assertEquals("23790.00769 36000.000061 08842.191424 7 61200000010026", ngc.getCodigoImpresso());
    }
}
