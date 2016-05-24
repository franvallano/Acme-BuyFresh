<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="order">
	<security:authorize access="hasRole('CLERK')">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="substitutes"/>
		<form:hidden path="subscription"/>
		<form:hidden path="clerk"/>
		<form:hidden path="menu"/>
		<form:hidden path="address"/>
		<form:hidden path="notes"/>
		
		<acme:textbox code="order.shippingDate" path="shippingDate" />
		<br />
		<acme:textbox code="order.arrivalDate" path="arrivalDate" />
		<br />
		<acme:textbox code="order.sent" path="sent" />
		
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="substitutes"/>
		<form:hidden path="subscription"/>
		<form:hidden path="clerk"/>
		<form:hidden path="shippingDate"/>
		<form:hidden path="arrivalDate"/>
		<form:hidden path="sent"/>
		
		<acme:textbox code="order.address" path="address"/>
		<br />
		<acme:textbox code="order.notes" path="notes"/>
		<br />
		<acme:select items="${menus}" itemLabel="name" code="order.menu" path="menu"/>
		
	</security:authorize>
	
	<acme:submit code="order.save" name="update" />&nbsp;
		
	<acme:back code="order.cancel"/>
	<br />

</form:form>