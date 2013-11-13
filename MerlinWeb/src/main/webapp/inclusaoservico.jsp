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
                    <td valign="top">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
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
                                <td width="99%">
                                    &nbsp;
                                    <!-- inicio do conteudo -->
                                    <h:form id="formTb" >
                                        <h:inputHidden id="codigoservico" value="#{incluirServico.codigoServico}"/>
                                        <table border="0">
                                            <tr>
                                                <td colspan="3"><h2>Inclus&atilde;o de Servi&ccedil;o</h2>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Nome:</td>
                                                <td><h:inputText id="nomeservico" value="#{incluirServico.nomeServico}" styleClass="caixadetexto" required="true" size="50" maxlength="50" /></td>
                                                <td><h:message for="nomeservico" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td>Valor:</td>
                                                <td><h:inputText id="valorservico" value="#{incluirServico.valorServico}" styleClass="caixadetexto" required="true" size="6" maxlength="8" >
                                                        <f:convertNumber maxFractionDigits="2" minFractionDigits="2"  />
                                                        <f:validateLongRange minimum="1"/>
                                                    </h:inputText>
                                                </td>
                                                <td><h:message for="valorservico" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td>Tipo:</td>
                                                <td><h:selectOneRadio id="tiposervico" layout="lineDirection" value="#{incluirServico.tipoServico}" required="true">
                                                        <f:selectItem itemValue="frequente" itemLabel="Frequente"/>
                                                        <f:selectItem itemValue="esporadico" itemLabel="Esporádico"/>
                                                    </h:selectOneRadio>
                                                </td>
                                                <td><h:message for="tiposervico" errorClass="erros"/></td>

                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Salvar" action="#{incluirServico.doIncluir}" styleClass="botao" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:outputText styleClass="mensagem" value="#{incluirServico.mensagem}"/></td>
                                            </tr>


                                        </table>
                                    </td>
                                </h:form>
                                </body>
                            </f:view>
                            </html>
