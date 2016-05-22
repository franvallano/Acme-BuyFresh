<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="actorRegisterForm">

	
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
		
		<acme:textbox code="register.phone" path="phone"/>
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
	
	<acme:submit name="save" code="register.save" />&nbsp; &nbsp; &nbsp; &nbsp; 
	
	<input type="button" name="cancel" class="btn btn-primary" value="<spring:message code="cancel" />" 
		onclick="javascript: window.history.back();" />
	
</form:form>

<br /><br />