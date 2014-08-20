<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
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
                                    <h:form>
                                        <table border="0">
                                            <tr>
                                                <td colspan="3"><h2>Gerar arquivo remessa </h2></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h:selectOneMenu id="codigoApartamento" value="#{gerarRemessa.codigoApartamento}"  styleClass="styled-select" rendered="false">
                                                        <f:selectItems value="#{combos.comboApartamento}" />
                                                    </h:selectOneMenu >
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Ano:</td>
                                                <td>
                                                    <h:inputText id="ano" value="#{gerarRemessa.ano}" size="4" maxlength="4" required="true">
                                                        <f:validator validatorId="yearValidator" />
                                                    </h:inputText>
                                                </td>
                                                <td><h:message for="ano" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td>M&ecirc;s:</td>
                                                <td><h:selectOneRadio id="mes" value="#{gerarRemessa.mes}" required="true" layout="pageDirection">
                                                        <f:selectItem itemValue="0" itemLabel="Janeiro"/>
                                                        <f:selectItem itemValue="1" itemLabel="Fevereiro"/>
                                                        <f:selectItem itemValue="2" itemLabel="Março"/>
                                                        <f:selectItem itemValue="3" itemLabel="Abril"/>
                                                        <f:selectItem itemValue="4" itemLabel="Maio"/>
                                                        <f:selectItem itemValue="5" itemLabel="Junho"/>
                                                        <f:selectItem itemValue="6" itemLabel="Julho"/>
                                                        <f:selectItem itemValue="7" itemLabel="Agosto"/>
                                                        <f:selectItem itemValue="8" itemLabel="Setembro"/>
                                                        <f:selectItem itemValue="9" itemLabel="Outubro"/>
                                                        <f:selectItem itemValue="10" itemLabel="Novembro"/>
                                                        <f:selectItem itemValue="11" itemLabel="Dezembro"/>
                                                    </h:selectOneRadio >
                                                </td>
                                                <td><h:message for="mes" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Gerar Remessa" action="#{gerarRemessa.doGerar}" styleClass="botao" /></td>
                                            </tr>
                                        </table>
                                    </h:form>
                                    <c:if test="${gerarRemessa.flagOk}">
                                        <form name="frameRemessa" action="remessaDownload" method="post" target="_blank">
                                            <input type="hidden" name="ano" value='<h:outputText value="#{gerarRemessa.ano}"/>'>
                                            <input type="hidden" name="mes" value='<h:outputText value="#{gerarRemessa.mes}"/>'>
                                        </form>
                                        <script>
                                            document.frameRemessa.submit();
                                        </script>
                                    </c:if>
                                </td>
                                </body>
                            </f:view>
                            </html>
