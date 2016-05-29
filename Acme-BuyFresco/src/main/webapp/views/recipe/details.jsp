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
	
	<h2>${recipe.name}</h2>
	<acme:labelDetails code="recipe.elaboration" value="${recipe.elaboration}"/>
	
	<acme:labelDetails code="recipe.time" value="${recipe.time}"/>	
	
	
	
	
	<h2><spring:message code="recipe.ingredients"/></h2>
	<display:table name="ingredients" pagesize="5" class="displaytag" requestURI="${requestURI}" id="ingredient">
		<acme:column code="ingredient.name" property="[0].name"/>
		<acme:column code="quantity.value" property="[1]"/>
		<acme:column code="ingredient.metricUnit" property="[0].metricUnit"/>
		<acme:column_ref code="ingredient.details" ref="ingredient/details.do?ingredientId=${ingredient[0].id}"/>
		
	</display:table>
	
		

	<acme:back code="recipe.back"/>
	
	
	
</div>