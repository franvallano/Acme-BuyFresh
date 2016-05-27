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
	

	<acme:labelDetails code="order.address" value="${order.address}"/>
	<acme:labelDetails code="order.shippingDate" value="${order.shippingDate}"/>
	
	<acme:labelDetails code="order.arrivalDate" value="${order.arrivalDate}"/>
	<acme:labelDetails code="order.notes" value="${order.notes}"/>
	<acme:labelDetails code="order.sent" value="${order.sent}"/>
	<acme:labelDetails code="order.totalPrice" value="${order.totalPrice}"/>
	
	<br>
	
	<h2><spring:message code="order.menu"/></h2>
		
		<acme:labelDetails code="order.menu.name" value="${menu.name}"/>	
		<acme:labelDetails code="order.menu.finishMoment" value="${menu.finishMoment}"/>	
		<acme:labelDetails code="order.menu.people" value="${menu.people}"/>	
		<acme:labelDetails code="order.menu.type" value="${menu.type}"/>	
		
	<br>
	
	<h2><spring:message code="order.substitutes"/></h2>
	<display:table name="substitute" id="substitute" pagesize="5" requestURI="${requestURI}" class="displaytag">
		
		<acme:column code="order.substitutes.name" property="name"/>	
		<acme:column code="order.substitutes.metricUnit" property="metricUnit"/>
		<acme:column code="order.substitutes.quantity" property="quantity"/>	
		<acme:column code="order.substitutes.recipeName" property="recipeName"/>	
		
	</display:table>


	<jstl:if test="${order.arrivalDate != null}">
		
		<h2><acme:textURL URL="assessment/user/create.do" code="assess"/></h2>
	
	</jstl:if>


	<br>
	<acme:back code="order.back" />
	
	
	
</div>