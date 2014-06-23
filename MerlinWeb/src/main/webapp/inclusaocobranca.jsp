<%@ taglib uri="WEB-INF/html_basic.tld" prefix="h"%>
<%@ taglib uri="WEB-INF/jsf_core.tld" prefix="f"%>
<%@ taglib uri="WEB-INF/c.tld" prefix="c"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java"
         errorPage="error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <f:view>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
            <title>:::Condom&iacute;nio MERLIN SUL:::</title>
            <link href="css/estilo.css" rel="stylesheet" type="text/css">
            <link href="css/buttons.css" rel="stylesheet" type="text/css">
            <style type="text/css">
                <!--
                #apDiv1 {
                    position:absolute;
                    left:261px;
                    top:256px;
                    width:197px;
                    height:87px;
                    z-index:1;
                }
                -->
            </style>
        </head>
        <body topmargin="0" leftmargin="0" background="imagens/fundoPagina.gif">
            <script type="text/javascript" src="js/item_menu.js"></script>
            <script type="text/javascript" src="js/menu_com.js"></script>
            <table width="100%" height="100%" border="0" cellpadding="0"
                   cellspacing="0">
                <tr>
                    <td valign="top">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="1%"><img src="imagens/top1.gif" width="440" height="82"></td>
                                <td width="98%" background="imagens/fundoAzul.gif"><img
                                        src="imagens/transp.gif" width="25" height="82"></td>
                                <td width="1%"><img src="imagens/top2.gif" width="315" height="82"></td>
                            </tr>
                        </table>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="1%" valign="top">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td><img src="imagens/top3.gif" width="164" height="42"></td>
                                        </tr>
                                        <tr>
                                            <td><img src="imagens/transp.gif" width="129" height="10"></td>
                                        </tr>
                                    </table>
                                <td width="99%">&nbsp; <!-- inicio do conteudo --> <h:form
                                        id="formTb">

                                        <table border="0" width=100%>
                                            <tr>
                                                <td colspan="3">
                                                    <h2>Gera&ccedil;&atilde;o de Cobran&ccedil;a</h2>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="input_formulario" width="130">Data de Vencimento:</td>
                                                <td><h:inputText id="dataVencimento"
                                                             value="#{gerarCobranca.dataVencimento}"
                                                             styleClass="caixadetexto" required="true" size="10"
                                                             maxlength="10">
                                                        <f:convertDateTime pattern="dd/MM/yyyy" />
                                                    </h:inputText></td>
                                                <td><h:message for="dataVencimento" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td>Valor da Multa:</td>
                                                <td><h:inputText id="valorMulta" value="#{gerarCobranca.multa}"
                                                             styleClass="caixadetexto" required="true" size="10"
                                                             maxlength="15">
                                                        <f:validateDoubleRange minimum="1" />

                                                    </h:inputText></td>
                                                <td><h:message for="valorMulta" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Gerar cobranças"
                                                                 action="#{gerarCobranca.doGerar}"/></td>
                                            </tr>
                                            <c:if test="${gerarCobranca.flagConfirma}">
                                                <tr>
                                                    <td colspan="4">Ja existe cobrança gerada para este mês. Gerar Novamente?<h:commandButton value="Confirmar"
                                                                     action="#{gerarCobranca.confirma}" styleClass="botao" /></td>

                                                </tr>
                                            </c:if>
                                            <c:if test="${gerarCobranca.temDados}">
                                                <tr>
                                                    <td colspan="4">Dados Gerados:
                                                        <h:commandButton value="Armazenar" action="#{gerarCobranca.doSalvar}"/></td>

                                                </tr>
                                                <tr>
                                                    <td colspan=3>
                                                        <DIV id=oDiv  ALIGN=left STYLE="height:100;
                                                             width:95%;padding:3;
                                                             overflow:clip;">
                                                            <h:dataTable value="#{gerarCobranca.dados}" var="cobranca"
                                                                         headerClass="columnHeader"
                                                                         rowClasses="evenRow, oddRow" styleClass="styled-table">
                                                                <h:column>
                                                                    <f:facet name="header"><h:outputText value="Condominio" /></f:facet>
                                                                    <h:outputText value="#{cobranca.apartamento.condominio.nomecondominio}"/>
                                                                </h:column>
                                                                <h:column>
                                                                    <f:facet name="header"><h:outputText value="Apartamento" /></f:facet>
                                                                    <h:outputText value="#{cobranca.apartamento.numeroapartamento}"/>
                                                                </h:column>
                                                                <h:column>
                                                                    <f:facet name="header"><h:outputText value="Vencimento" /></f:facet>
                                                                    <h:outputText value="#{cobranca.dataVencimento}">
                                                                        <f:convertDateTime pattern="dd/MM/yyyy"/>
                                                                    </h:outputText>
                                                                </h:column>
                                                                <h:column>
                                                                    <f:facet name="header"><h:outputText value="Valor" /></f:facet>
                                                                    <h:outputText value="#{cobranca.valorCobrado}" >
                                                                        <f:convertNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/>
                                                                    </h:outputText>
                                                                </h:column>
                                                            </h:dataTable>

                                                        </DIV>
                                                    </td>
                                                </tr>

                                            </c:if>
                                        </table>
                                    </h:form></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </body>
    </f:view>
</html>
