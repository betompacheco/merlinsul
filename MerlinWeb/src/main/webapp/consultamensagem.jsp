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
	</head>
	<body topmargin="0" leftmargin="0" background="imagens/fundoPagina.gif">
		<script type="text/javascript" src="js/item_menu.js"></script>
		<script type="text/javascript" src="js/menu_com.js"></script>
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign="top"><table width="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="1%"><img src="imagens/top1.gif" width="440"
								height="82">
							</td>
							<td width="98%" background="imagens/fundoAzul.gif"><img
								src="imagens/transp.gif" width="25" height="82">
							</td>
							<td width="1%"><img src="imagens/top2.gif" width="315"
								height="82">
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="1%" valign="top"><table width="100%" border="0"
									cellpadding="0" cellspacing="0">
									<tr>
										<td><img src="imagens/top3.gif" width="164" height="42">
										</td>
									</tr>
									<tr>
										<td><img src="imagens/transp.gif" width="129" height="10">
										</td>
									</tr>
								</table>
							<td width="99%">&nbsp; <!-- inicio do conteudo --> <h:form
									id="form">
									<table border="0">
										<tr>
											<td colspan="3"><h2>Consulta de Mensagem</h2>
											</td>
										</tr>
										<tr>
											<td>Crit&eacute;rio:</td>
											<td><h:inputText id="criterio"
													value="#{consultarMensagem.criterio}"
													styleClass="caixadetexto" size="50" maxlength="50" />
											</td>
											<td><h:message for="criterio" errorClass="erros" />
											</td>
										</tr>
										<tr>
											<td colspan="3"><h:commandButton value="Consultar"
													action="#{consultarMensagem.doConsultar}"
													styleClass="botao" />
											</td>
										</tr>
										<tr>
											<td colspan="3"><h:dataTable
													value="#{consultarMensagem.dados}" var="mensagem"
													headerClass="columnHeader" rowClasses="evenRow, oddRow"
													width="60%">
													<h:column>
														<f:facet name="header">
															<h:outputText value="Codigo" />
														</f:facet>
														<h:outputText value="#{mensagem.codigoMensagem}" />
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Texto" />
														</f:facet>
														<h:outputText value="#{mensagem.textoMensagem}" />
													</h:column>
													<h:column>
														<h:commandButton value="Editar"
															action="#{consultarMensagem.doEditar}" styleClass="botao"
															immediate="true" />
													</h:column>
													<h:column>
														<h:commandButton value="Excluir"
															action="#{consultarMensagem.doExcluir}"
															onclick="javascript: return confirm('Confirmar exclusão');"
															styleClass="botao" />
													</h:column>

												</h:dataTable></td>
										</tr>

									</table>
								</h:form></td>
	</body>
</f:view>
</html>
