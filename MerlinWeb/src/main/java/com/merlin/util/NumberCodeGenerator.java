package com.merlin.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NumberCodeGenerator {

    private int numeroBanco;
    private int numeroMoeda = 9;
    private Date vencimento;
    private double valor;
    private int agencia;
    private long conta;
    private int carteira;
    private int cob;
    private Date processamento;
    private String nossoNumero;
    private String codigoImpresso;
    private String codigoBarras;
    private Logger logger = Logger.getLogger("NumberCodeGenerator");

    public void generate() {
        codigoBarras = criarCodigoBarras();
        // Inclui o Digito verificador do codigo de barras
        int dac = calculaDAC(codigoBarras);
        codigoBarras = codigoBarras.substring(0, 4) + Integer.toString(dac) + codigoBarras.substring(4);
        codigoImpresso = digitavel(Integer.toString(dac));
        codigoImpresso = formataDigitavel(codigoImpresso);
        logger.log(Level.FINE, "Codigo Impresso : {0}", codigoImpresso);
        logger.log(Level.FINE, "Codigo de Barras : {0}", codigoBarras);
        logger.log(Level.FINE, "Tamanho do Codigo de Barras : {0}", codigoBarras.length());
    }

    private String digitavel(String DAC) {
        StringBuffer codigo = new StringBuffer();
        StringBuffer tmp = new StringBuffer();

        String codigocedente = complete(Long.toString(conta), 7, "0");
        String codigodocumento = complete(nossoNumero, 13, "0");

        tmp.append(complete(Integer.toString(numeroBanco), 3, "0")); // numero do banco
        tmp.append(complete(Integer.toString(numeroMoeda), 1, "0")); // numero da moeda
        tmp.append(codigocedente.substring(0, 5)); // primeira parte do codigo do cedente (conta)
        tmp.append(calculaDigitoVerificadorBloco(tmp.toString())); // digito de verificacao do primeiro bloco
        codigo.append(tmp); // adiciona o primeiro bloco

        tmp = new StringBuffer();
        tmp.append(codigocedente.substring(5)); // final do codigo do cedente
        tmp.append(codigodocumento.substring(0, 8));// primeira parte do codigo do documento (nosso numero)
        tmp.append(calculaDigitoVerificadorBloco(tmp.toString())); // digito de verificacao do primeiro bloco
        codigo.append(tmp); // adiciona o segundo bloco

        tmp = new StringBuffer();
        tmp.append(codigodocumento.substring(8)); // final do codigo do documento
        tmp.append(complete(Integer.toString(dataJuliana(vencimento)), 4, "0")); // data no formato juliano
        tmp.append("2"); // codigo do produto
        tmp.append(calculaDigitoVerificadorBloco(tmp.toString())); // digito de verificacao do terceiro bloco
        codigo.append(tmp); // adiciona o terceiro bloco

        codigo.append(DAC); // digito verificador do codigo de barras, o codigo de barras tem que ser calculado antes

        tmp = new StringBuffer();
        tmp.append(complete(Long.toString(fatorVencimento(vencimento)), 4, "0"));
        int valor = valorSemPonto(this.valor);
        if (valor == 0) {
            tmp.append(complete("", 10, "0"));
        } else {
            tmp.append(complete(Integer.toString(valor), 10, "0"));
        }

        codigo.append(tmp);// adiciona o quinto bloco

        return codigo.toString();
    }

    private String formataDigitavel(String digitavel) {
        StringBuffer retorno = new StringBuffer();
        retorno.append(digitavel.substring(0, 5));
        retorno.append(".");
        retorno.append(digitavel.substring(5, 10));
        retorno.append(" ");
        retorno.append(digitavel.substring(10, 15));
        retorno.append(".");
        retorno.append(digitavel.substring(15, 21));
        retorno.append(" ");
        retorno.append(digitavel.substring(21, 26));
        retorno.append(".");
        retorno.append(digitavel.substring(26, 32));
        retorno.append(" ");
        retorno.append(digitavel.substring(32, 33));
        retorno.append(" ");
        retorno.append(digitavel.substring(33, 47));
        return retorno.toString();
    }

    /**
     * <pre>
     * POSIÇÃO
     * DE ATÉ TAMANHO CONTEÚDO
     * 01 03  03      Código do HSBC na Câmara de Compensação, igual a 399.
     * 04 04  01      Tipo de Moeda (9 para moeda Real ou 0 para Moeda Variável).
     * 05 05  01      Dígito de Autoconferência (DAC).
     * 06 09  04      Fator de Vencimento.
     * 10 19  10      Valor do Documento.Se Moeda Variável, o valor deverá ser igual a zeros.
     * 20 26  07      Código do Cedente
     * 27 39  13      Número Bancário, igual ao Código do Documento, sem os dígitos verificadores e tipo identificador.
     * 40 43  04      Data de Vencimento no Formato Juliano.
     * 44 44  01      Código do Produto CNR, igual a 2.
     * </pre>
     *
     * @return
     */
    private String criarCodigoBarras() {
        StringBuffer codigo = new StringBuffer();
        StringBuffer tmp = new StringBuffer();

        String codigocedente = complete(Long.toString(conta), 7, "0");
        String codigodocumento = complete(nossoNumero, 13, "0");

        // Numero do banco
        tmp.append(complete(Integer.toString(numeroBanco), 3, "0"));
        // Tipo de moeda
        tmp.append(complete(Integer.toString(numeroMoeda), 1, "0"));

        // Fator de Vencimento
        tmp.append(complete(Long.toString(fatorVencimento(vencimento)), 4, "0"));
        // Valor do Documento
        int valor = valorSemPonto(this.valor);
        if (valor == 0) {
            tmp.append(complete("", 10, "0"));
        } else {
            tmp.append(complete(Integer.toString(valor), 10, "0"));
        }

        // Codigo do cedente (conta)
        tmp.append(codigocedente);
        // Final do codigo do documento
        tmp.append(codigodocumento);
        // Data no formato juliano
        tmp.append(complete(Integer.toString(dataJuliana(vencimento)), 4, "0"));
        // codigo do produto CNR
        tmp.append("2");

        codigo.append(tmp);
        return codigo.toString();
    }

    public int calculaModulo11base9(String valor) {
        int[] pesos = new int[]{9, 8, 7, 6, 5, 4, 3, 2};

        int tmp = 0;
        int iPeso = 0;

        for (int i = valor.length() - 1; i > -1; i--) {
            int vl = valor.charAt(i) - 48;
            int vlbase = pesos[iPeso];
            int tot = vl * vlbase;
            tmp += tot;
            iPeso++;
            if (iPeso > 7) {
                iPeso = 0;
            }
        }

        int resto = tmp % 11;
        int result = resto;
        // Se o resto da divisão for igual a 0 (zero) ou 10, o primeiro dígito verificador será igual a 0 (zero).
        if (resto == 0 || resto == 10) {
            result = 0;
        }

        return result;

    }

    public String calculaDACdoBradesco(String valor) {
        int[] pesos = new int[]{2, 3, 4, 5, 6, 7};

        int tmp = 0;
        int iPeso = 0;

        for (int i = valor.length() - 1; i > -1; i--) {
            int vl = valor.charAt(i) - 48;
            int vlbase = pesos[iPeso];
            int tot = vl * vlbase;
            tmp += tot;
            iPeso++;
            if (iPeso > 5) {
                iPeso = 0;
            }
        }

        int resto = tmp % 11;
        int result = 11 - resto;

        //Se o resto da divisão for “1”, desprezar a diferença entre o divisor
        //menos o resto que será “10” e considerar o dígito como “P”.
        if (resto == 1) {
            return "P";
        }
        //Se o resto da divisão for “0”, desprezar o cálculo de subtração entre
        //divisor e resto, e considerar o “0” como dígito.
        if (resto == 0) {
            result = 0;
        }

        return Integer.toString(result);
    }

    // Calcula o Digito de Autoconferência (DAC) do HSBC
    public int calculaDAC(String valor) {
        int[] pesos = new int[]{2, 3, 4, 5, 6, 7, 8, 9};

        int tmp = 0;
        int iPeso = 0;

        for (int i = valor.length() - 1; i > -1; i--) {
            int vl = valor.charAt(i) - 48;
            int vlbase = pesos[iPeso];
            int tot = vl * vlbase;
            tmp += tot;
            iPeso++;
            if (iPeso > 7) {
                iPeso = 0;
            }
        }

        int resto = tmp % 11;
        int result = 11 - resto;
        // Como criterio quando o resto da divisao for igual a 0 (zero), 1 (um) ou 10 (dez), o DAC adotado devera ser sempre igual a 1 (um), pois
        // 11-0=11, 11-1=10 e 11-10=1.
        if (resto == 0 || resto == 1 || resto == 10) {
            result = 1;
        }

        return result;
    }

    public int calculaDigitoVerificadorBloco(String valor) {
        int resto = 0;
        int[] pesos = new int[]{2, 1};
        int tmp = 0;
        int iPeso = 0;

        for (int i = valor.length() - 1; i > -1; i--) {
            int vl = valor.charAt(i) - 48;
            int vlbase = pesos[iPeso];
            int tot = vl * vlbase;
            if (tot >= 10) {
                String x = Integer.toString(tot);
                tot = (x.charAt(0) - 48) + (x.charAt(1) - 48);
            }
            tmp += tot;
            iPeso++;
            if (iPeso > 1) {
                iPeso = 0;
            }
        }
        int result = 0;
        if (tmp >= 10) {
            resto = tmp % 10;
            if (resto == 0) {
                result = 0;
            } else {
                result = 10 - resto;
            }
        } else {
            result = 10 - tmp;
        }
        return result;
    }

    public long fatorVencimento(Date venc) {
        GregorianCalendar vencimento = new GregorianCalendar();
        vencimento.setTime(venc);
        GregorianCalendar base = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
        long diferenca = vencimento.getTimeInMillis() - base.getTimeInMillis();
        long dias = diferenca / (24 * 60 * 60 * 1000);

        return dias;

        /*
         * String[] data = getDataVencimento().split("/"); String dia = data[0]; String mes = data[1]; String ano = data[2];
         *
         * Calendar dataBase = new GregorianCalendar(1997, Calendar.OCTOBER, 7); Calendar vencimento = new GregorianCalendar(Integer.parseInt(ano),
         * Integer.parseInt(mes)-1, Integer.parseInt(dia)); long diferenca = vencimento.getTimeInMillis() - dataBase.getTimeInMillis();
         *
         * long diferencaDias = diferenca/(2460601000);
         */
    }

    private int valorSemPonto(double valor) {
        return (int) (valor * 100);
    }

    public int dataJuliana(Date vencimento) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(vencimento);

        int ano = gc.get(GregorianCalendar.YEAR);
        int dia = gc.get(GregorianCalendar.DAY_OF_YEAR);
        ano = Integer.toString(ano).charAt(3) - 48;
        return Integer.parseInt(Integer.toString(dia) + Integer.toString(ano));
    }

    /**
     * Completa um valor com uma sequencia de caracteres definida
     *
     * @param valor O valor a ser completado
     * @param tam O tamanho final da sequencia
     * @param caracter o caracter a ser colocado
     * @return
     */
    public String complete(String valor, int tam, String caracter) {
        if (valor.length() == tam) {
            return valor;
        }
        StringBuilder saida = new StringBuilder();
        for (int i = 0; i < tam - valor.length(); i++) {
            saida.append(caracter);
        }
        saida.append(valor);
        return saida.toString();

    }

    /**
     * Calcula os digitos verificadores do Nosso Numero / Codigo do Documento
     *
     * @param codigoDoSacado Código do Documento
     * @param carteira A carteira utilizada pelo banco
     *
     * @return O Nosso Numero / Codigo do Documento junto com os digitos
     * verificadores
     */
    public String comporNossoNumero(long codigoDoSacado, String carteira) {
        StringBuilder nossoNumeroComposto = new StringBuilder();
        nossoNumeroComposto.append(carteira).append("/");

        //Formata o nosso numero para 11 digitos
        String nossoNumeroParcial = this.complete(Long.toString(codigoDoSacado), 11, "0");
        nossoNumeroComposto.append(nossoNumeroParcial).append("-");

        //Efetua o calculo do DAC do numero
        nossoNumeroComposto.append(this.calculaDACdoBradesco(carteira.concat(nossoNumeroParcial)));

        //Retorna o nosso numero já formatado de acordo
        return nossoNumeroComposto.toString();
    }

    public static void main(String[] args) {
        NumberCodeGenerator ngc = new NumberCodeGenerator();
        String temp = "123";
        System.out.println(ngc.complete(temp, 15, "x"));
        GregorianCalendar gc = new GregorianCalendar(2000, Calendar.JULY, 05);
        System.out.println("Fator de vencimento = " + ngc.fatorVencimento(gc.getTime()));
        System.out.println("Data Juliana: " + ngc.dataJuliana(gc.getTime()));
        System.out.println("Modulo 11: " + ngc.calculaDAC("39992606014600000000304171.02092141230000093561"));

        System.out.println("Teste Real\n-------------------");
        ngc.setNumeroBanco(399); // default hsbc
        ngc.setNumeroMoeda(9); // default hsbc
        ngc.setVencimento(new GregorianCalendar(2009, 0, 10).getTime());
        ngc.setProcessamento(null);
        // ngc.setProcessamento(new GregorianCalendar(2008, 11, 17).getTime());
        ngc.setValor(935.61);
        ngc.setNossoNumero("000000004171"); // nao incluir os ultimos 3 digitos
        ngc.setAgencia(Config.AGENCIA);
        ngc.setConta(Config.CONTA);
        ngc.setCob(0); // default hsbc
        ngc.generate();
        System.out.println("Codigo Impresso: " + ngc.getCodigoImpresso());
        System.out.println("Codigo Barras:   " + ngc.getCodigoBarras());

        ngc.setNumeroBanco(399); // default hsbc
        ngc.setNumeroMoeda(9); // default hsbc
        ngc.setVencimento(new GregorianCalendar(2004, 11, 15).getTime());
        ngc.setProcessamento(new GregorianCalendar(2004, 11, 05).getTime());
        ngc.setValor(0);
        ngc.setNossoNumero("6940931037112"); // nao incluir os ultimos 3 digitos
        ngc.setAgencia(0);
        ngc.setConta(2201640);
        ngc.setCob(0); // default hsbc
        ngc.generate();
        System.out.println("Codigo Impresso: " + ngc.getCodigoImpresso());
        System.out.println("Codigo Barras:   " + ngc.getCodigoBarras());

    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getCarteira() {
        return carteira;
    }

    public void setCarteira(int carteira) {
        this.carteira = carteira;
    }

    public int getCob() {
        return cob;
    }

    public void setCob(int cob) {
        this.cob = cob;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoImpresso() {
        return codigoImpresso;
    }

    public void setCodigoImpresso(String codigoImpresso) {
        this.codigoImpresso = codigoImpresso;
    }

    public long getConta() {
        return conta;
    }

    public void setConta(long conta) {
        this.conta = conta;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public int getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public int getNumeroMoeda() {
        return numeroMoeda;
    }

    public void setNumeroMoeda(int numeroMoeda) {
        this.numeroMoeda = numeroMoeda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getProcessamento() {
        return processamento;
    }

    public void setProcessamento(Date processamento) {
        this.processamento = processamento;
    }
}
