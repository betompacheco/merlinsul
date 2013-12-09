<%@ taglib uri="WEB-INF/html_basic.tld" prefix="h"%>
<%@ taglib uri="WEB-INF/jsf_core.tld" prefix="f"%>
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
                                <td width="1%"><img src="imagens/top1.gif" width="440"
                                                    height="82"></td>
                                <td width="98%" background="imagens/fundoAzul.gif"><img
                                        src="imagens/transp.gif" width="25" height="82"></td>
                                <td width="1%"><img src="imagens/top2.gif" width="315"
                                                    height="82"></td>
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
                                <td width="99%">&nbsp; <h:form id="formTb">
                                        <h:inputHidden id="codigoOrcamento"
                                                       value="#{incluirOrcamento.codigoOrcamento}" />
                                        <table border="0">
                                            <tr>
                                                <td colspan="3">
                                                    <h2>Inclus&atilde;o de Orcamento</h2>
                                                </td>
                                            <tr>
                                                <td>Condominio:</td>
                                                <td><h:selectOneMenu id="codigoCondominio"
                                                                 value="#{incluirOrcamento.codigoCondominio}"
                                                                 styleClass="styled-select" required="true">
                                                        <f:validateLongRange minimum="1" />
                                                        <f:selectItems value="#{combos.comboCondominio}" />
                                                    </h:selectOneMenu></td>
                                                <td><h:message for="codigoCondominio" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td>Ano:</td>
                                                <td><h:selectOneMenu id="ano"
                                                                 value="#{incluirOrcamento.ano}" styleClass="styled-select" required="true">
                                                        <f:selectItem itemValue="2009" itemLabel="2009" itemDescription="teste de descricao" />
                                                        <f:selectItem itemValue="2010" itemLabel="2010" />
                                                        <f:selectItem itemValue="2011" itemLabel="2011" />
                                                        <f:selectItem itemValue="2012" itemLabel="2012" />
                                                        <f:selectItem itemValue="2013" itemLabel="2013" />
                                                        <f:selectItem itemValue="2014" itemLabel="2014" />
                                                        <f:selectItem itemValue="2015" itemLabel="2015" />
                                                        <f:selectItem itemValue="2016" itemLabel="2016" />
                                                        <f:selectItem itemValue="2017" itemLabel="2017" />
                                                        <f:selectItem itemValue="2018" itemLabel="2018" />
                                                        <f:selectItem itemValue="2019" itemLabel="2019" />
                                                        <f:selectItem itemValue="2020" itemLabel="2020" />
                                                        <f:selectItem itemValue="2021" itemLabel="2021" />
                                                        <f:selectItem itemValue="2022" itemLabel="2022" />
                                                        <f:selectItem itemValue="2023" itemLabel="2023" />
                                                        <f:selectItem itemValue="2024" itemLabel="2024" />
                                                        <f:selectItem itemValue="2025" itemLabel="2025" />
                                                    </h:selectOneMenu></td>
                                                <td><h:message for="ano" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td>M&ecirc;s:</td>
                                                <td><h:selectOneMenu id="mes"
                                                                 value="#{incluirOrcamento.mes}" styleClass="styled-select"
                                                                 required="true">
                                                        <f:selectItem itemValue="0" itemLabel="Janeiro" />
                                                        <f:selectItem itemValue="1" itemLabel="Fevereiro" />
                                                        <f:selectItem itemValue="2" itemLabel="Março" />
                                                        <f:selectItem itemValue="3" itemLabel="Abril" />
                                                        <f:selectItem itemValue="4" itemLabel="Maio" />
                                                        <f:selectItem itemValue="5" itemLabel="Junho" />
                                                        <f:selectItem itemValue="6" itemLabel="Julho" />
                                                        <f:selectItem itemValue="7" itemLabel="Agosto" />
                                                        <f:selectItem itemValue="8" itemLabel="Setembro" />
                                                        <f:selectItem itemValue="9" itemLabel="Outubro" />
                                                        <f:selectItem itemValue="10" itemLabel="Novembro" />
                                                        <f:selectItem itemValue="11" itemLabel="Dezembro" />
                                                    </h:selectOneMenu></td>
                                                <td><h:message for="mes" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td>Servico:</td>
                                                <td><h:selectOneMenu id="codigoServico"
                                                                 value="#{incluirOrcamento.codigoServico}" styleClass="styled-select"
                                                                 onchange="">
                                                        <f:selectItem itemValue="1" itemLabel="Cota" />
                                                        <f:selectItem itemValue="2" itemLabel="Cota Parte Comum" />
                                                        <f:selectItem itemValue="3" itemLabel="Outro" />
                                                    </h:selectOneMenu> </td>
                                                <td><h:message for="codigoServico" errorClass="erros" /></td>
                                            </tr>
                                            <tr id="linhaDescricao">
                                                <td>Descricao Servico:</td>
                                                <td><h:inputText id="descricao"
                                                             value="#{incluirOrcamento.descricao}" styleClass="caixadetexto"
                                                             required="true" size="35" maxlength="50">
                                                    </h:inputText>
                                                    <script>
                                                        function mudaServico() {
                                                            obj = document.getElementById("formTb:codigoServico");
                                                            v = obj.value;
                                                            ob = document.getElementById("formTb:descricao");
                                                            if (v == 3) {
                                                                //ob.disabled=false;
                                                            } else {
                                                                //ob.disabled=true;
                                                                ob.value = (v == 1 ? "Cota" : "Cota Parte Comum");
                                                            }
                                                        }
                                                        mudaServico();
                                                    </script>
                                                </td>
                                                <td><h:message for="descricao" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td>Valor:</td>
                                                <td><h:inputText id="valorOrcamento"
                                                             value="#{incluirOrcamento.valorOrcamento}"
                                                             styleClass="caixadetexto" required="true" size="15"
                                                             maxlength="15">
                                                        <f:validateDoubleRange minimum="1" />
                                                        <f:convertNumber maxFractionDigits="2" minFractionDigits="2"
                                                                         groupingUsed="true" />
                                                    </h:inputText></td>
                                                <td><h:message for="valorOrcamento" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td>Fundo Reserva(%):</td>
                                                <td><h:inputText id="fundoReserva"
                                                             value="#{incluirOrcamento.fundoReserva}"
                                                             styleClass="caixadetexto" required="true" size="15"
                                                             maxlength="15">
                                                        <f:convertNumber maxFractionDigits="2" minFractionDigits="2"
                                                                         groupingUsed="true" />
                                                    </h:inputText></td>
                                                <td><h:message for="fundoReserva" errorClass="erros" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Salvar"
                                                                 action="#{incluirOrcamento.doIncluir}" styleClass="botao" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:outputText
                                                        value="#{incluirOrcamento.mensagem}" styleClass="mensagem" /></td>
                                            </tr>

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
