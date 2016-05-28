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
	
	<h2>${ingredient.name}</h2>
	<acme:labelDetails code="ingredient.description" value="${ingredient.description}"/>
	
	<acme:labelDetails code="ingredient.metricUnit" value="${ingredient.metricUnit}"/>	
	
	<img  height="120" width="120" src="${ingredient.picture}">
	<br>
	
	<h2><spring:message code="ingredient.allergens"/></h2>
	<display:table name="allergens" pagesize="5" class="displaytag" requestURI="${requestURI}" id="allergen">
		<acme:column code="ingredient.name" property="name"/>
	</display:table>
	
		
	<security:authorize access="hasRole('ADMINISTRATOR')">	
		<a href="ingredient/administrator/edit.do?ingredientId=${ingredient.id}"><spring:message code="ingredient.edit" /></a>		
	</security:authorize>	
	

	<acme:back code="ingredient.back" />
	
	
	
</div>