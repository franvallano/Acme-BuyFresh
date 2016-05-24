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
	<form:hidden path="substitutes"/>
	<form:hidden path="subscription"/>
	<form:hidden path="clerk"/>
	<form:hidden path="address"/>
	<form:hidden path="notes"/>

	<acme:textbox code="order.shippingDate" path="shippingDate" />
	<br />
		<acme:textbox code="order.arrivalDate" path="arrivalDate" />

	
	<acme:submit code="order.save" name="update" />&nbsp;
		
	
	<acme:cancel url="order/clerk/myOrders.do" code="order.cancel"/>
	<br />

</form:form>