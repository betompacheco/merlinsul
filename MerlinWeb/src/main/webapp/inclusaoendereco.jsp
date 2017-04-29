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
				<td valign="top"><table width="100%" border="0" cellpadding="0"
						cellspacing="0">
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
							<td width="1%" valign="top"><table width="100%" border="0"
									cellpadding="0" cellspacing="0">
									<tr>
										<td><img src="imagens/top3.gif" width="164" height="42"></td>
									</tr>
									<tr>
										<td><img src="imagens/transp.gif" width="129" height="10"></td>
									</tr>
								</table>
							<td width="99%">&nbsp; <!-- inicio do conteudo --> <h:form
									id="formTb">
									<h:inputHidden id="codigoendereco"
										value="#{incluirEndereco.codigoendereco}" />
									<table border="0">
										<tr>
											<td colspan="3"><h2>Inclus&atilde;o de
													Endere&ccedil;o</h2></td>
										</tr>
										<tr>
											<td>Apartamento:</td>
											<td><h:selectOneMenu id="codigoapartamento"
													value="#{incluirEndereco.codigoapartamento}"
													styleClass="styled-select" required="true">
													<f:selectItems value="#{combos.comboApartamento}" />

												</h:selectOneMenu></td>
											<td><h:message for="codigoapartamento"
													errorClass="erros" /></td>
										<tr>
											<td>Logradouro:</td>
											<td><h:inputText id="logradouro"
													value="#{incluirEndereco.logradouro}"
													styleClass="caixadetexto" required="true" size="30"
													maxlength="30">
												</h:inputText>
											<td><h:message for="logradouro" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Complemento:</td>
											<td><h:inputText id="complemento"
													value="#{incluirEndereco.complemento}"
													styleClass="caixadetexto" required="true" size="15"
													maxlength="15">
												</h:inputText>
											<td><h:message for="complemento" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Bairro:</td>
											<td><h:inputText id="bairro"
													value="#{incluirEndereco.bairro}" styleClass="caixadetexto"
													required="true" size="25" maxlength="25">
												</h:inputText>
											<td><h:message for="bairro" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Cidade:</td>
											<td><h:inputText id="cidade"
													value="#{incluirEndereco.cidade}" styleClass="caixadetexto"
													required="true" size="30" maxlength="30">
												</h:inputText>
											<td><h:message for="cidade" errorClass="erros" /></td>
										<tr>
											<td>UF:</td>
											<td><h:selectOneMenu id="uf"
													value="#{incluirEndereco.uf}" styleClass="styled-select"
													required="true">
													<f:selectItem itemValue="" itemLabel="" />
													<f:selectItem itemValue="Acre" itemLabel="Acre" />
													<f:selectItem itemValue="Alagoas" itemLabel="Alagoas" />
													<f:selectItem itemValue="Amapa" itemLabel="Amapa" />
													<f:selectItem itemValue="Amazonas" itemLabel="Amazonas" />
													<f:selectItem itemValue="Bahia" itemLabel="Bahia" />
													<f:selectItem itemValue="Ceara" itemLabel="Ceara" />
													<f:selectItem itemValue="Distrito Federal"
														itemLabel="Distrito Federal" />
													<f:selectItem itemValue="Espirito Santo"
														itemLabel="Espirito Santo" />
													<f:selectItem itemValue="Goias" itemLabel="Goias" />
													<f:selectItem itemValue="Maranhao" itemLabel="Maranhao" />
													<f:selectItem itemValue="Mato Grosso"
														itemLabel="Mato Grosso" />
													<f:selectItem itemValue="Mato Grosso do Sul"
														itemLabel="Mato Grosso do Sul" />
													<f:selectItem itemValue="Minas Gerais"
														itemLabel="Minas Gerais" />
													<f:selectItem itemValue="Para" itemLabel="Para" />
													<f:selectItem itemValue="Paraiba" itemLabel="Paraiba" />
													<f:selectItem itemValue="Parana" itemLabel="Parana" />
													<f:selectItem itemValue="Pernambuco" itemLabel="Pernambuco" />
													<f:selectItem itemValue="Piaui" itemLabel="Piaui" />
													<f:selectItem itemValue="Rio de Janeiro"
														itemLabel="Rio de Janeiro" />
													<f:selectItem itemValue="Rio Grande do Norte"
														itemLabel="Rio Grande do Norte" />
													<f:selectItem itemValue="Rio Grande do Sul"
														itemLabel="Rio Grande do Sul" />
													<f:selectItem itemValue="Rondonia" itemLabel="Rondonia" />
													<f:selectItem itemValue="Roraima" itemLabel="Roraima" />
													<f:selectItem itemValue="Santa Catarina"
														itemLabel="Santa Catarina" />
													<f:selectItem itemValue="Sao Paulo" itemLabel="Sao Paulo" />
													<f:selectItem itemValue="Sergipe" itemLabel="Sergipe" />
													<f:selectItem itemValue="Tocantis" itemLabel="Tocantis" />
												</h:selectOneMenu></td>
											<td><h:message for="uf" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>CEP (somente números):</td>
											<td><h:inputText id="cep" value="#{incluirEndereco.cep}"
													styleClass="caixadetexto" required="true" size="8"
													maxlength="8">
												</h:inputText></td>
											<td><h:message for="cep" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Endereço Cobrança?</td>
											<td><h:selectBooleanCheckbox id="enderecocobranca"
													value="#{incluirEndereco.enderecoCobranca}"
													styleClass="caixabooleano" /></td>
											<td><h:message for="enderecocobranca" errorClass="erros" /></td>
										</tr>
										<tr>
											<td colspan="3"><h:commandButton value="Salvar"
													action="#{incluirEndereco.doIncluir}" styleClass="botao" /></td>
										</tr>
										<tr>
											<td colspan="3"><h:outputText
													value="#{incluirEndereco.mensagem}" styleClass="mensagem" /></td>
										</tr>
										</h:form>
									</table></td>
	</body>
</f:view>
</html>
