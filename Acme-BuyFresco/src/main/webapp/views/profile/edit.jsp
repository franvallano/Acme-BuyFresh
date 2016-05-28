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

<script>	

	<!-- Esto es para llamar a la funcion cada vez que se cargue la web -->
	$(document).ready(function () {
		checkCreditCard();
	});
	
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
	
	<security:authorize access="hasRole('USER')">
		<form:form action="${url}" modelAttribute="${userForm}" >
			<jstl:if test="${edit == true}">
				<acme:textbox code="actor.username" path="username" readonly="true"/>
				<br/>
				<acme:textbox code="actor.name" path="name"/>
				<br/>
				<acme:textbox code="actor.surname" path="surname"/>
				<br/>
				<acme:textbox code="actor.email" path="email"/>
				<br/>
				<acme:textbox code="actor.phone" path="phone"/>
				<br/>
				<acme:textbox code="actor.address" path="address"/>
				<br/>
				
				<h4><b><spring:message code="allergens" /></b></h4>
				<form:select path="allergens" multiple="true">
					<form:options items="${allergens}" itemLabel="name" itemValue="id" />
				</form:select>
				<form:errors cssClass="error" path="allergens" />
				<br />
				<br />
				<br />
				
				<spring:message code="useCreditCard" />
			
				<jstl:choose>
					<jstl:when test="${checkBoxCreditCard == null || checkBoxCreditCard == true}">
						<form:checkbox id="checkBoxCreditCard" path="checkBoxCreditCard" onclick="checkCreditCard()"/> 
						
					</jstl:when>
					<jstl:when test="${checkBoxCreditCard == false}">
						<form:checkbox id="checkBoxCreditCard" path="checkBoxCreditCard" onclick="checkCreditCard()"/> 
						
					</jstl:when>
				</jstl:choose>
			
				<br/>
				
				<div class="showCreditCard">
					<fieldset id="enableCreditCard">
					<legend><h3><spring:message code="creditCard" /></h3></legend>
						<acme:textbox code="actor.holdername" path="creditCard.holderName"/>
						<br/>
						
						<acme:textbox code="actor.brandname" path="creditCard.brandName"/>
						<br/>
						
						<acme:textbox code="actor.number" path="creditCard.number"/>
						<br/>
						
						<acme:textbox code="actor.expirationmonth" path="creditCard.expirationMonth"/>
						<br/>
						
						<acme:textbox code="actor.expirationyear" path="creditCard.expirationYear"/>
						<br/>
						
						<acme:textbox code="actor.cvvcode" path="creditCard.cvv"/>
						<br/>
						
					</fieldset>
				</div>
				<br/>
			</jstl:if>
			
			<acme:submit name="save" code="actor.save"/>
			<input type="button" name="cancel" class="btn btn-primary" value="<spring:message code="cancel" />" 
				onclick="javascript: window.history.back();" />
		</form:form>
		
	</security:authorize>
	
	<security:authorize access="hasAnyRole('ADMINISTRATOR', 'CLERK')">
		<form:form action="${url}" modelAttribute="${userForm}" >
			<acme:textbox code="actor.username" path="username" readonly="true"/>
			<br/>
			<acme:textbox code="actor.name" path="name"/>
			<br/>
			<acme:textbox code="actor.surname" path="surname"/>
			<br/>
			<acme:textbox code="actor.email" path="email"/>
			<br/>
			<acme:textbox code="actor.phone" path="phone"/>
			<br/>
			
			<acme:submit name="save" code="actor.save"/>
			<input type="button" name="cancel" class="btn btn-primary" value="<spring:message code="cancel" />" 
				onclick="javascript: window.history.back();" />
		</form:form>
	</security:authorize>