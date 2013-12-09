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
                                    <h:form id="form">
                                        <table border="0">
                                            <tr>
                                                <td colspan="3"><h2>Consulta de Servi&ccedil;o Utilizado </h2>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">Crit&eacute;rio:</td>
                                            </tr>
                                            <tr>
                                                <td>Apartamento:</td>
                                                <td>
                                                    <h:selectOneMenu id="codigoApartamento" value="#{consultarServicoUtilizado.codigoApartamento}"  styleClass="styled-select" required="true">
                                                        <f:selectItems value="#{combos.comboApartamento}" />
                                                    </h:selectOneMenu >
                                                </td>
                                                <td><h:message for="codigoApartamento" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td>Ano:</td>
                                                <td><h:selectOneMenu id="ano"  value="#{consultarServicoUtilizado.ano}"  styleClass="styled-select" required="true">
                                                        <f:selectItem itemValue="" itemLabel=""/>
                                                        <f:selectItem itemValue="2004" itemLabel="2004"/>
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
                                                        <f:selectItem itemValue="2016" itemLabel="2016"/>
                                                        <f:selectItem itemValue="2017" itemLabel="2017"/>
                                                        <f:selectItem itemValue="2018" itemLabel="2018"/>
                                                        <f:selectItem itemValue="2019" itemLabel="2019"/>
                                                        <f:selectItem itemValue="2020" itemLabel="2020"/>
                                                        <f:selectItem itemValue="2021" itemLabel="2021"/>
                                                        <f:selectItem itemValue="2022" itemLabel="2022"/>
                                                        <f:selectItem itemValue="2023" itemLabel="2023"/>
                                                        <f:selectItem itemValue="2024" itemLabel="2024"/>
                                                        <f:selectItem itemValue="2025" itemLabel="2025"/>
                                                    </h:selectOneMenu >

                                                </td>
                                                <td><h:message for="ano" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td>M&ecirc;s:</td>
                                                <td><h:selectOneMenu id="mes" value="#{consultarServicoUtilizado.mes}"  styleClass="styled-select"  required="true">
                                                        <f:selectItem itemValue="" itemLabel=""/>
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
                                                    </h:selectOneMenu >
                                                </td>
                                                <td><h:message for="mes" errorClass="erros"/></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><h:commandButton value="Consultar" action="#{consultarServicoUtilizado.doConsultar}"/></td>
                                            </tr>
                                            <tr>

                                                <td colspan="3">
                                                    <h:dataTable value="#{consultarServicoUtilizado.dados}" var="servicoUtilizado"
                                                                 headerClass="columnHeader"
                                                                 rowClasses="evenRow, oddRow" styleClass="styled-table">
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Nome" /></f:facet>
                                                            <h:outputText value="#{servicoUtilizado.servico.nomeServico}"/>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Data" /></f:facet>
                                                            <h:outputText value="#{servicoUtilizado.dataUtilizacao}">
                                                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                                                            </h:outputText>
                                                        </h:column>
                                                        <h:column>
                                                            <f:facet name="header"><h:outputText value="Valor" /></f:facet>
                                                            <h:outputText value="#{servicoUtilizado.servico.valorServico}"/>
                                                        </h:column>
                                                        <h:column>
                                                            <h:commandButton value="Editar" action="#{consultarServicoUtilizado.doEditar}" styleClass="botao" immediate="true"/>
                                                        </h:column>
                                                        <h:column>
                                                            <h:commandButton value="Excluir" action="#{consultarServicoUtilizado.doExcluir}" onclick="javascript: return confirm('Confirmar exclusão');" styleClass="botao" />
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
