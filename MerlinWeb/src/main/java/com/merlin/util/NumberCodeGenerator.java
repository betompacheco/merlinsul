package com.merlin.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NumberCodeGenerator {

    private static final Logger logger = Logger.getLogger(NumberCodeGenerator.class.getName());

    private int numeroBanco;
    private int numeroMoeda = 9;
    private Date vencimento;
    private double valor;
    private int agencia;
    private long conta;
    private String carteira;
    private int cob;
    private Date processamento;
    private String nossoNumero;
    private String codigoImpresso;
    private String codigoBarras;

    public void generate() {
        codigoBarras = criarCodigoBarras();
        int dac = calculaDAC(codigoBarras);
        // Inclui o Digito verificador do codigo de barras
        codigoBarras = codigoBarras.substring(0, 4) + Integer.toString(dac) + codigoBarras.substring(4);

        //Calcula a linha digitavel
        codigoImpresso = criaLinhaDigitavel(Integer.toString(dac), codigoBarras);
        logger.log(Level.INFO, "Codigo Impresso.................: {0}", codigoImpresso);
        codigoImpresso = formataLinhaDigitavel(codigoImpresso);
        logger.log(Level.INFO, "Codigo Impresso Formatado.......: {0}", codigoImpresso);
        logger.log(Level.INFO, "Codigo de Barras................: {0}", codigoBarras);
        logger.log(Level.INFO, "Tamanho do Codigo de Barras.....: {0}", codigoBarras.length());
    }

    /**
     * Monta a linha do boleto em formato digitavel
     *
     * @param DAC O digito de auto conferencia do codigo de barras
     * @return
     */
    private String criaLinhaDigitavel(String DAC, String codigoBarrasSemDAC) {
        StringBuilder codigo = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        // Adiciona o primeiro bloco
        tmp.append(Utilitario.complete(Integer.toString(numeroBanco), 3, "0")); // 01 a 03, Identificacao do Banco
        tmp.append(Utilitario.complete(Integer.toString(numeroMoeda), 1, "0")); // 04 a 04, Código da Moeda (Real = 9)
        tmp.append(codigoBarrasSemDAC.substring(19, 24));// Cinco primeiras posicoes do campo livre
        tmp.append(calculaDigitoVerificadorBloco(tmp.toString())); // Digito de verificacao do primeiro bloco
        codigo.append(tmp);

        // Adiciona o segundo bloco
        tmp = new StringBuilder();
        tmp.append(codigoBarrasSemDAC.substring(24, 34));// Posicoes 6 a 15 do campo livre
        tmp.append(calculaDigitoVerificadorBloco(tmp.toString())); // Digito de verificacao do segundo bloco
        codigo.append(tmp);

        // Adiciona o terceiro bloco
        tmp = new StringBuilder();
        tmp.append(codigoBarrasSemDAC.substring(34, 44));// Posicoes 16 a 25 do campo livre
        tmp.append(calculaDigitoVerificadorBloco(tmp.toString())); // Digito de verificacao do terceiro bloco
        codigo.append(tmp);

        // Adiciona o quarto bloco, que é o digito verificador do codigo de barras. O codigo de barras tem que ser calculado antes.
        codigo.append(DAC);

        // Adiciona o quinto bloco
        tmp = new StringBuilder();
        tmp.append(Utilitario.complete(Long.toString(fatorVencimento(vencimento)), 4, "0"));// Adiciona o fator de vencimento

        //Adiciona o valor do documento
        int valor = valorSemPonto(this.valor);
        if (valor == 0) {
            tmp.append(Utilitario.complete("", 10, "0"));
        } else {
            tmp.append(Utilitario.complete(Integer.toString(valor), 10, "0"));
        }
        codigo.append(tmp);

        return codigo.toString();
    }

    /**
     * Representa a linha digitavel formatada
     *
     * 23790.03102 40031.772003 28009.527905 7 10010000000000
     *
     * @param digitavel A linha digitavel sem formatacao
     * @return A linha digitavel formatada
     */
    private String formataLinhaDigitavel(String digitavel) {
        StringBuilder retorno = new StringBuilder(0);
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

    private String criarCodigoBarras() {
        StringBuilder codigo = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        // Adiciona o numero do banco
        tmp.append(Utilitario.complete(Integer.toString(numeroBanco), 3, "0"));
        // Adiciona o tipo de moeda
        tmp.append(Utilitario.complete(Integer.toString(numeroMoeda), 1, "0"));

        // Adiciona o fator de Vencimento
        tmp.append(Utilitario.complete(Long.toString(fatorVencimento(vencimento)), 4, "0"));

        // Adiciona o valor do Documento
        int valor = valorSemPonto(this.valor);
        if (valor == 0) {
            tmp.append(Utilitario.complete("", 10, "0"));
        } else {
            tmp.append(Utilitario.complete(Integer.toString(valor), 10, "0"));
        }

        //Adiciona a agencia
        tmp.append(Utilitario.complete(Long.toString(agencia), 4, "0"));

        //Adiciona a carteira
        tmp.append(Utilitario.complete(carteira, 2, "0"));

        //Adiciona o nosso numero
        String codigodocumento = Utilitario.complete(nossoNumero, 11, "0");
        tmp.append(codigodocumento);

        //Adiciona a conta
        String codigocedente = Utilitario.complete(Long.toString(conta), 7, "0");
        tmp.append(codigocedente);

        // Preenche o restante com zeros
        tmp.append(Utilitario.complete("", 1, "0"));

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

    // Calcula o Digito de Autoconferência (DAC) do Bradesco
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
        String nossoNumeroParcial = Utilitario.complete(Long.toString(codigoDoSacado), 11, "0");
        nossoNumeroComposto.append(nossoNumeroParcial).append("-");

        //Efetua o calculo do DAC do numero
        nossoNumeroComposto.append(this.calculaDACdoBradesco(carteira.concat(nossoNumeroParcial)));

        //Retorna o nosso numero já formatado de acordo
        return nossoNumeroComposto.toString();
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
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
