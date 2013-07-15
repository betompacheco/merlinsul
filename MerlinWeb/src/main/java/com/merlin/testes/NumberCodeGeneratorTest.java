package com.merlin.testes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import com.merlin.util.NumberCodeGenerator;

public class NumberCodeGeneratorTest extends TestCase {

    public void testCalculaModulo11() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        assertEquals(9, ngc.calculaModulo11("239104761"));
        assertEquals(1, ngc.calculaModulo11("23918868104"));
        assertEquals(0, ngc.calculaModulo11("0"));
    }

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
        assertEquals(1001, ngc.fatorVencimento(new Date("07/04/2000")));
        assertEquals(1667, ngc.fatorVencimento(new Date("05/01/2002")));
        assertEquals(3923, ngc.fatorVencimento(new Date("07/04/2008")));
        assertEquals(4758, ngc.fatorVencimento(new Date("10/17/2010")));
        assertEquals(9999, ngc.fatorVencimento(new Date("02/21/2025")));
    }

    public void testDataJuliana() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        Calendar gc = new GregorianCalendar();
        gc.setTime(new Date("07/04/2008"));
        assertEquals(1868, ngc.dataJuliana(gc.getTime()));
    }

    public void testCodigoImpresso() {
        NumberCodeGenerator ngc = new NumberCodeGenerator();

        ngc.setNumeroBanco(399); // default HSBC
        ngc.setNumeroMoeda(9); // default HSBC
        ngc.setVencimento(new Date("07/04/2008"));
        ngc.setProcessamento(null);
        ngc.setNossoNumero("0000239104761"); // nao incluir os ultimos 3 digitos
        ngc.setAgencia(1227);
        ngc.setConta(8351202);
        ngc.setCob(0); // default HSBC
        ngc.setValor(1200);
        ngc.generate();
        assertEquals("39998.35121 02000.023917 04761.186826 4 39230000120000", ngc.getCodigoImpresso());
    }
}
