<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="details">
	
	<h2><spring:message code="order.ingredients"/></h2>
	<display:table name="formated" id="row" pagesize="40" requestURI="${requestURI}" class="displaytag">
		<acme:column code="order.ingredient.name" property="[0]"/>
		<acme:column code="order.ingredient.quantity" property="[1]"/>
		<acme:column code="order.ingredient.metricUnit" property="[2]"/>
	</display:table>
	


	<br>
	<acme:back code="order.back" />
	
	
	
</div>