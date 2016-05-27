<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="order">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="menu"/>
	<form:hidden path="sent"/>
	<form:hidden path="shippingDate"/>
	<form:hidden path="totalPrice"/>

	<acme:textbox code="order.address" path="address" />
	<br />
		<acme:textbox code="order.notes" path="notes" />
	<br />
		
	<acme:submit code="order.save" name="save" />&nbsp;
		
	<acme:back code="order.cancel"/>
	
	<br />

</form:form>