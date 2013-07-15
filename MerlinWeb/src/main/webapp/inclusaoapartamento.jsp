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
				<h:inputHidden id="codigoApartamento" value="#{incluirApartamento.codigoApartamento}"/>
        <table border="0">
          <tr>
            <td colspan="3"><h2>Inclus&atilde;o de Apartamento</h2></td>
          </tr>
          <tr>
            <td>Condominio:</td>
            <td>
				<h:selectOneMenu id="codigoCondominio" value="#{incluirApartamento.codigoCondominio}"  styleClass="selecao" required="true"> 
					<f:validateLongRange minimum="1"/>
				    <f:selectItems value="#{combos.comboCondominio}" />
				</h:selectOneMenu >
			</td>
            <td><h:message for="codigoCondominio" errorClass="erros"/></td>
          </tr>
          <tr>
            <td>Proprietario:</td>
            <td>
            	<h:selectOneMenu id="codigoProprietario" value="#{incluirApartamento.codigoProprietario}"  styleClass="selecao" required="true"> 
					<f:validateLongRange minimum="1"/>
				    <f:selectItems value="#{combos.comboProprietario}" />
				</h:selectOneMenu >
			 </td>
            <td><h:message for="codigoProprietario" errorClass="erros"/></td>
          </tr>
          <tr>
            <td>N&uacute;mero:</td>
            <td><h:inputText id="numeroApartamento" value="#{incluirApartamento.numeroApartamento}" styleClass="caixadetexto" required="true" size="5" maxlength="5" /></td>
            <td><h:message for="numeroApartamento" errorClass="erros"/></td>
          </tr>

          <tr>
            <td>Qtde. quartos:</td>
			<td><h:selectOneRadio id="qtdeQuarto" value="#{incluirApartamento.qtdeQuarto}" required="true">
				<f:selectItem itemValue="2" itemLabel="2 Quartos"/>
				<f:selectItem itemValue="3" itemLabel="3 Quartos"/>
				</h:selectOneRadio>
			</td>
            <td><h:message for="qtdeQuarto" errorClass="erros"/></td>
          </tr>

          <tr>
            <td colspan="3"><h:commandButton value="Salvar" action="#{incluirApartamento.doIncluir}" styleClass="botao" /></td>
          </tr>
          <tr>
            <td colspan="3"><h:outputText value="#{incluirApartamento.mensagem}" styleClass="mensagem"/></td>
          </tr>
          </h:form>          
        </table>
</body>
</f:view>
</html>
