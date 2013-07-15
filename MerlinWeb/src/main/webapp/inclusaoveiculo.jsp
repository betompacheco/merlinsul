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
				<h:inputHidden id="codigoVeiculo" value="#{incluirVeiculo.codigoVeiculo}" />        

        <table border="0">
          <tr>
            <td colspan="3"><h2>Inclus&atilde;o de Ve&iacute;culo</h2>
            </td>
          </tr>
          <tr>
            <td>Apartamento:</td>
            <td><h:selectOneMenu id="codigoApartamento" value="#{incluirVeiculo.codigoApartamento}"  styleClass="selecao" required="true"> 
					<f:selectItems value="#{combos.comboApartamento}" /> 	             
				</h:selectOneMenu ></td>
            <td><h:message for="codigoApartamento" errorClass="erros"/></td>
          </tr>
          <tr>
            <td>Tipo:</td>
            <td><h:selectOneMenu id="tipo" value="#{incluirVeiculo.tipoVeiculo}"  styleClass="selecao" required="true"> 
					<f:selectItem itemValue="" itemLabel=""/> 	             
					<f:selectItem itemValue="carro" itemLabel="carro"/> 
					<f:selectItem itemValue="moto" itemLabel="moto"/> 
				</h:selectOneMenu >
			</td>
            <td><h:message for="tipo" errorClass="erros"/></td>
          </tr>
          <tr>
            <td>Placa:</td>
            <td><h:inputText id="placa" value="#{incluirVeiculo.placaVeiculo}" styleClass="caixadetexto" required="true" size="7" maxlength="7" /></td>
            <td><h:message for="placa" errorClass="erros"/></td>
          </tr>
          <tr>
            <td>Modelo:</td>
            <td><h:inputText id="modelo" value="#{incluirVeiculo.modeloVeiculo}" styleClass="caixadetexto" required="true" size="15" maxlength="15" /></td>
            <td><h:message for="modelo" errorClass="erros"/></td>
          </tr>
          <tr>
            <td>Fabricante:</td>
            <td><h:selectOneMenu id="fabricante" value="#{incluirVeiculo.fabricanteVeiculo}"  styleClass="selecao" required="true"> 
					<f:selectItem itemValue="" itemLabel=""/> 	 
					<f:selectItem itemValue="Alfa Romeo" itemLabel="Alfa Romeo"/> 	 
					<f:selectItem itemValue="Asia" itemLabel="Asia"/> 	  
					<f:selectItem itemValue="Audi" itemLabel="Audi"/> 	  
					<f:selectItem itemValue="Bmw" itemLabel="Bmw"/> 	  
					<f:selectItem itemValue="Chevrolet" itemLabel="Chevrolet"/> 	  
					<f:selectItem itemValue="Chrysler" itemLabel="Chrysler"/> 	  
					<f:selectItem itemValue="Citroen" itemLabel="Citroen"/> 	  
					<f:selectItem itemValue="Daewoo" itemLabel="Daewoo"/> 	  
					<f:selectItem itemValue="Dodge" itemLabel="Dodge"/> 	  
					<f:selectItem itemValue="Fiat" itemLabel="Fiat"/> 	  
					<f:selectItem itemValue="Ford" itemLabel="Ford"/> 	  
					<f:selectItem itemValue="Gurgel" itemLabel="Gurgel"/> 	  
					<f:selectItem itemValue="Honda" itemLabel="Honda"/> 	  
					<f:selectItem itemValue="Hyundai" itemLabel="Hyundai"/> 	  
					<f:selectItem itemValue="Jaguar" itemLabel="Jaguar"/> 	  
					<f:selectItem itemValue="Jeep" itemLabel="Jeep"/> 	  
					<f:selectItem itemValue="Kia" itemLabel="Kia"/> 	  
					<f:selectItem itemValue="Lada" itemLabel="Lada"/> 	  
					<f:selectItem itemValue="Mazda" itemLabel="Mazda"/> 	  
					<f:selectItem itemValue="Mercedes" itemLabel="Mercedes"/> 	  
					<f:selectItem itemValue="Mitsubishi" itemLabel="Mitsubishi"/> 	  
					<f:selectItem itemValue="Nissan" itemLabel="Nissan"/> 	  
					<f:selectItem itemValue="OUTROS" itemLabel="OUTROS"/> 	  
					<f:selectItem itemValue="Peugeot" itemLabel="Peugeot"/> 	  
					<f:selectItem itemValue="Puma" itemLabel="Puma"/> 	  
					<f:selectItem itemValue="Renault" itemLabel="Renault"/> 	  
					<f:selectItem itemValue="Seat" itemLabel="Seat"/> 	  
					<f:selectItem itemValue="Subaru" itemLabel="Subaru"/> 	  
					<f:selectItem itemValue="Suzuki" itemLabel="Suzuki"/> 	  
					<f:selectItem itemValue="Toyota" itemLabel="Toyota"/> 	  
					<f:selectItem itemValue="Volkswagen" itemLabel="Volkswagen"/> 	  
					<f:selectItem itemValue="Volvo" itemLabel="Volvo"/> 	  
					<f:selectItem itemValue="Yamaha" itemLabel="Yamaha"/> 	  
				</h:selectOneMenu ></td>
            <td><h:message for="fabricante" errorClass="erros"/></td>
          </tr>
          <tr>
            <td colspan="3"><h:commandButton value="Salvar" action="#{incluirVeiculo.doIncluir}" styleClass="botao" /></td>
          </tr>
          <tr>
            <td colspan="3"><h:outputText styleClass="mensagem" value="#{incluirVeiculo.mensagem}"/></td>
          </tr>
          </h:form>
        </table>
      </td>
</body>
</f:view>
</html>
