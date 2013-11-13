<%@ taglib uri="WEB-INF/html_basic.tld" prefix="h" %>
<%@ taglib uri="WEB-INF/jsf_core.tld" prefix="f" %>
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
                                    <h:form id="formTb" >

                                        <table border="0">
                                            <tr>
                                                <td colspan="3"><h2>Emitir Boleto Banc&aacute;rio</h2></td>
                                            </tr>
                                            <tr>
                                                <td>Mes/Ano:</td>
                                                <td>
                                                    <h:selectOneMenu id="mes" value="#{relatorioBoleto.mes}"  styleClass="selecao" required="true">
                                                        <f:selectItem itemValue="1" itemLabel="Janeiro"/>
                                                        <f:selectItem itemValue="2" itemLabel="Fevereiro"/>
                                                        <f:selectItem itemValue="3" itemLabel="Março"/>
                                                        <f:selectItem itemValue="4" itemLabel="Abril"/>
                                                        <f:selectItem itemValue="5" itemLabel="Maio"/>
                                                        <f:selectItem itemValue="6" itemLabel="Junho"/>
                                                        <f:selectItem itemValue="7" itemLabel="Julho"/>
                                                        <f:selectItem itemValue="8" itemLabel="Agosto"/>
                                                        <f:selectItem itemValue="9" itemLabel="Setembro"/>
                                                        <f:selectItem itemValue="10" itemLabel="Outubro"/>
                                                        <f:selectItem itemValue="11" itemLabel="Novembro"/>
                                                        <f:selectItem itemValue="12" itemLabel="Dezembro"/>
                                                    </h:selectOneMenu>
                                                    /
                                                    <h:selectOneMenu id="ano" value="#{relatorioBoleto.ano}"  styleClass="selecao" required="true">
                                                        <f:selectItem itemValue="2005" itemLabel="2005"/>
                                                        <f:selectItem itemValue="2006" itemLabel="2006"/>
                                                        <f:selectItem itemValue="2007" itemLabel="2007"/>
                                                        <f:selectItem itemValue="2008" itemLabel="2008"/>
                                                        <f:selectItem itemValue="2009" itemLabel="2009"/>
                                                        <f:selectItem itemValue="2010" itemLabel="2010"/>
                                                        <f:selectItem itemValue="2011" itemLabel="2011"/>
                                                        <f:selectItem itemValue="2012" itemLabel="2012"/>
                                                        <f:selectItem itemValue="2013" itemLabel="2013"/>
                                                        <f:selectItem itemValue="2014" itemLabel="2014"/>
                                                        <f:selectItem itemValue="2015" itemLabel="2015"/>
                                                        <f:selectItem itemValue="2017" itemLabel="2017"/>
                                                        <f:selectItem itemValue="2018" itemLabel="2018"/>
                                                        <f:selectItem itemValue="2019" itemLabel="2019"/>
                                                        <f:selectItem itemValue="2020" itemLabel="2020"/>
                                                        <f:selectItem itemValue="2021" itemLabel="2021"/>
                                                        <f:selectItem itemValue="2022" itemLabel="2022"/>
                                                        <f:selectItem itemValue="2023" itemLabel="2023"/>
                                                        <f:selectItem itemValue="2024" itemLabel="2024"/>
                                                        <f:selectItem itemValue="2025" itemLabel="2025"/>
                                                        <f:selectItem itemValue="2026" itemLabel="2026"/>
                                                        <f:selectItem itemValue="2027" itemLabel="2027"/>
                                                        <f:selectItem itemValue="2028" itemLabel="2028"/>
                                                        <f:selectItem itemValue="2029" itemLabel="2029"/>
                                                        <f:selectItem itemValue="2030" itemLabel="2030"/>
                                                    </h:selectOneMenu>
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>qtde. Apartamento:</td>
                                                <td><h:inputText id="qtd" value="#{incluirCondominio.qtd}" styleClass="caixadetexto" required="true" size="3" maxlength="3" > <f:convertNumber maxFractionDigits="0" minFractionDigits="0" integerOnly="true" /> <f:validateLongRange minimum="1"/> </h:inputText> </td>
                                                <td><h:message for="qtd" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Salvar" action="#{incluirCondominio.doIncluir}" styleClass="botao" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:outputText value="#{incluirCondominio.mensagem}"/></td>
                                            </tr>
                                        </h:form>

                                    </table></td>
                                </body>
                            </f:view>
                            </html>
