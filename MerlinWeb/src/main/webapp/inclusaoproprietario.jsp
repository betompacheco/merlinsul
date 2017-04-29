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
									<h:inputHidden id="codigoProprietario"
										value="#{incluirProprietario.codigoproprietario}" />
									<table border="0">
										<tr>
											<td colspan="3"><h2>Inclus&atilde;o de Proprietario</h2>
											</td>
										</tr>
										<tr>
											<td>Nome:</td>
											<td><h:inputText id="nomeproprietario"
													value="#{incluirProprietario.nomeproprietario}"
													styleClass="caixadetexto" required="true" size="40"
													maxlength="40" /></td>
											<td><h:message for="nomeproprietario" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>sexo:</td>
											<td><h:selectOneRadio layout="lineDirection"
													value="#{incluirProprietario.sexo}" required="true">
													<f:selectItem itemValue="M" itemLabel="masculino" />
													<f:selectItem itemValue="F" itemLabel="feminino" />
												</h:selectOneRadio></td>
											<td><h:message for="nomeproprietario" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Identidade:</td>
											<td><h:inputText id="identidade"
													value="#{incluirProprietario.identidade}"
													styleClass="caixadetexto" required="true" size="16"
													maxlength="10" /></td>
											<td><h:message for="identidade" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>CPF / CNPJ:</td>
											<td><h:inputText id="cpfCnpj"
													value="#{incluirProprietario.cpfCnpj}"
													styleClass="caixadetexto" required="true" size="16"
													maxlength="14" />(somente os números)</td>
											<td><h:message for="cpfCnpj" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Profiss&atilde;o:</td>
											<td><h:inputText id="profissao"
													value="#{incluirProprietario.profissao}"
													styleClass="caixadetexto" required="true" size="30"
													maxlength="25" /></td>
											<td><h:message for="profissao" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Filia&ccedil;&atilde;o:</td>
											<td><h:inputText id="filiacao"
													value="#{incluirProprietario.filiacao}"
													styleClass="caixadetexto" required="true" size="30"
													maxlength="30" /></td>
											<td><h:message for="filiacao" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Telefone Residencial:</td>
											<td><h:inputText id="telResidencial"
													value="#{incluirProprietario.telResidencial}"
													styleClass="caixadetexto" size="14" maxlength="14" /></td>
											<td><h:message for="telResidencial" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Telefone Comercial:</td>
											<td><h:inputText id="telComercial"
													value="#{incluirProprietario.telComercial}"
													styleClass="caixadetexto" size="14" maxlength="14" /></td>
											<td><h:message for="telComercial" errorClass="erros" /></td>
										</tr>
										<tr>
											<td>Telefone Celular:</td>
											<td><h:inputText id="telCelular"
													value="#{incluirProprietario.telCelular}"
													styleClass="caixadetexto" size="14" maxlength="14" /></td>
											<td><h:message for="telCelular" errorClass="erros" /></td>
										</tr>
										<tr>
											<td colspan="3"><h:commandButton value="Salvar"
													action="#{incluirProprietario.doIncluir}"
													styleClass="botao" /></td>
										</tr>
										<tr>
											<td colspan="3"><h:outputText
													value="#{incluirProprietario.mensagem}" /></td>
										</tr>

									</table>
								</h:form>
							</td>
	</body>
</f:view>
</html>
