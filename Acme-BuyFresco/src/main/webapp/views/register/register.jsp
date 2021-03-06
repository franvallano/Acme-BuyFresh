<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">	

	<!-- Esto es para llamar a la funcion cada vez que se cargue la web -->
	//$(document).ready(function () {
		//checkCreditCard();
	//});

	// Asi cargamos la funcion, ya que despues de las CSS lo de arriba no va bien
	window.onload = function(){checkCreditCard();};
	
	function checkCreditCard(){
		var checked = $("#checkBoxCreditCard");

		if(checked.is(':checked')) {
			$("#enableCreditCard").attr("disabled", false);
			$("#enableCreditCard").attr("enabled", true);
		} else {
			$("#enableCreditCard").attr("disabled", true);
		}
	}
		
</script>

<form:form action="${url}" modelAttribute="${userFormModel}" class="form-horizontal">

	<security:authorize access="hasRole('ADMINISTRATOR')">
		<acme:textbox code="register.username" path="username"/>
		<br/>
		
		<acme:password code="register.password" path="password"/>
		<br/>
		
		<acme:password code="register.rpassword" path="repeatedPass"/>
		<jstl:if test="${duplicate == false}">
			<font color="red">
				<b><spring:message code="register.duplicate" /></b>
			</font>
		</jstl:if>
		<br/>
		
		<acme:textbox code="register.name" path="name"/>
		<br/>
		
		<acme:textbox code="register.surname" path="surname"/>
		<br/>
		
		<acme:textbox code="register.email" path="email"/>
		<br/>
		
		<acme:textbox code="register.phone" path="phone"/>
		<br/>
		
	</security:authorize>
	
	<security:authorize access="isAnonymous()">
		<acme:textbox code="register.username" path="username"/>
		<br/>
		
		<acme:password code="register.password" path="password"/>
		<br/>
		
		<acme:password code="register.rpassword" path="repeatedPass"/>
		<jstl:if test="${duplicate == false}">
			<font color="red">
				<b><spring:message code="register.duplicate" /></b>
			</font>
		</jstl:if>
		<br/>
		
		<acme:textbox code="register.name" path="name"/>
		<br/>
		
		<acme:textbox code="register.surname" path="surname"/>
		<br/>
		
		<acme:textbox code="register.email" path="email"/>
		<br/>
		
		<acme:textbox code="register.phone" path="phone"/>
		<br/>
		
		<acme:textbox code="register.address" path="address"/>
		<br/>		

		<h4><b><spring:message code="allergens" /></b></h4>
		<form:select path="allergens" multiple="true">
			<form:options items="${allergens}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors cssClass="error" path="allergens" />
		<br />
		<br />
		<br />
	
		<jstl:choose>
			<jstl:when test="${checkBoxCreditCard == null || checkBoxCreditCard == true}">
				<div class="row">
				  <div class="col-lg-6">
				    <div class="input-group">
				    <span class="input-group-addon">
				    
					<form:checkbox  class="checkbox" id="checkBoxCreditCard" path="checkBoxCreditCard" onclick="checkCreditCard()"/> 
					</span>
					</div>
				  </div>
				</div>
			
			</jstl:when>
			<jstl:when test="${checkBoxCreditCard == false}">
			<div class="row">
				<div  class="checkbox col-lg-2">
				<spring:message code="useCreditCard" />
				<form:checkbox class="col-lg-5"  id="checkBoxCreditCard" path="checkBoxCreditCard" onclick="checkCreditCard()"/> 
				</div>
			</div>
			</jstl:when>
		</jstl:choose>
		
		
	
		<br/>
		
		<div class="panel panel-primary">
			<fieldset id="enableCreditCard">
			<div class="panel-heading"><legend><h3><spring:message code="creditCard" /></h3></legend></div>
				<div class="panel-body">
					<acme:textbox code="register.holdername" path="creditCard.holderName"/>
					<br/>
					
					<acme:textbox code="register.brandname" path="creditCard.brandName"/>
					<br/>
					
					<acme:textbox code="register.number" path="creditCard.number"/>
					<br/>
					
					<acme:textbox code="register.expirationmonth" path="creditCard.expirationMonth"/>
					<br/>
					
					<acme:textbox code="register.expirationyear" path="creditCard.expirationYear"/>
					<br/>
					
					<acme:textbox code="register.cvvcode" path="creditCard.cvv"/>
					<br/>
				</div>
			</fieldset>
		</div>
		<br/>
		<fieldset>
				<div>
				<h3><b><spring:message code="conditions" /></b></h3>
				<spring:message code="conditionsText" />
				<br/>
				
				<h3><b><spring:message code="delete" /></b></h3>
				<spring:message code="deleteText" />
				<br/>
				
			</div>
		</fieldset>
		
				
		<spring:message code="check" />
		<form:checkbox path="agree"/> 
		<jstl:if test="${agree == false}">
			<font color="red">
				<b><spring:message code="register.disagree" /></b>
			</font>
		</jstl:if><br /><br />
	</security:authorize>
	
	<acme:submit name="save" code="register.save" />&nbsp; &nbsp; &nbsp; &nbsp; 
	
	<input type="button" name="cancel" class="btn btn-primary" value="<spring:message code="cancel" />" 
		onclick="javascript: window.history.back();" />
	
</form:form>

<br /><br />