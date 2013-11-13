<%@ taglib uri="WEB-INF/html_basic.tld" prefix="h" %>
<%@ taglib uri="WEB-INF/jsf_core.tld" prefix="f" %>
<%@ taglib uri="WEB-INF/c.tld" prefix="c"%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="error.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <f:view>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
            <title>:::Condom&iacute;nio MERLIN SUL:::</title>
            <link href="css/estilo.css" rel="stylesheet" type="text/css">
            <link href="css/buttons.css" rel="stylesheet" type="text/css">
        </head>
        <body topmargin="0" leftmargin="0" background="imagens/fundoPagina.gif">
            <script type="text/javascript" src="js/item_menu.js"></script>
            <script type="text/javascript" src="js/menu_com.js"></script>
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="1%"><img src="imagens/top1.gif" width="440" height="82"></td>
                                <td width="98%" background="imagens/fundoAzul.gif"><img src="imagens/transp.gif" width="25" height="82"></td>
                                <td width="1%"><img src="imagens/top2.gif" width="315" height="82"></td>
                            </tr>
                        </table>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="1%" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td><img src="imagens/top3.gif" width="164" height="42"></td>
                                        </tr>
                                        <tr>
                                            <td><img src="imagens/transp.gif" width="129" height="10"></td>
                                        </tr>
                                    </table>
                                <td width="99%">&nbsp;
                                    <!-- inicio do conteudo -->
                                    <h:form id="form">
                                        <table border="0">
                                            <tr>
                                                <td colspan="3"><h2>Consulta de Cobran&ccedil;a </h2></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">Crit&eacute;rio:</td>
                                            </tr>
                                            <tr>
                                                <td>Apartamento:</td>
                                                <td>
                                                    <h:selectOneMenu id="codigoApartamento" value="#{consultarCobranca.codigoApartamento}"  styleClass="selecao" required="true">
                                                        <f:selectItems value="#{combos.comboApartamento}" />
                                                    </h:selectOneMenu >
                                                </td>
                                                <td><h:message for="codigoApartamento" errorClass="erros"/></td>
                                            </tr >
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Consultar" action="#{consultarCobranca.doConsultar}" styleClass="botao" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">
                                                    <h:dataTable value="#{consultarCobranca.dados}" var="cobranca"
                                                                 headerClass="columnHeader"
                                                                 rowClasses="evenRow, oddRow"  >
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Data Vencimento" /></f:facet>
                                                            <h:outputText value="#{cobranca.dataVencimento}">
                                                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                                                            </h:outputText>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Valor Cobrado" /></f:facet>
                                                            <h:outputText value="#{cobranca.valorCobrado}">
                                                                <f:convertNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/>
                                                            </h:outputText>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Multa" /></f:facet>
                                                            <h:outputText value="#{cobranca.valorMulta}">
                                                                <f:convertNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/>
                                                            </h:outputText>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Valor Pago" /></f:facet>
                                                            <h:outputText id="valorfalso" value="#{cobranca.valorPago}" rendered="#{cobranca.baixa}">
                                                                <f:convertNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/>
                                                            </h:outputText>

                                                            <h:inputText id="valorverdadeiro" value="#{cobranca.valorPago}" required="true" rendered="#{!cobranca.baixa}">
                                                                <f:convertNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/>
                                                            </h:inputText>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Data Pagamento" /></f:facet>
                                                            <h:outputText value="#{cobranca.dataPagamento}">
                                                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                                                            </h:outputText>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Baixa" /></f:facet>
                                                            <h:commandButton value="Baixar" action="#{consultarCobranca.doBaixar}" styleClass="botao" rendered="#{!cobranca.baixa}"/>
                                                        </h:column>

                                                    </h:dataTable>
                                                </td>
                                            </tr>
                                        </h:form>
                                    </table>
                                </td>

                                </body>
                            </f:view>
                            </html>
