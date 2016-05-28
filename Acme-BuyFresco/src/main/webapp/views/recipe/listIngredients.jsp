<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="ingredients" pagesize="5" class="displaytag" requestURI="${requestURI}" id="ingredient">
	
	<acme:column code="ingredient.name" property="name"/>
	<acme:column code="ingredient.description" property="description"/>
	<acme:column code="ingredient.metricUnit" property="metricUnit"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">	
				
		<acme:column_ref code="ingredient.details" ref="ingredient/administrator/details.do?ingredientId=${ingredient.id}"/>
		
		<acme:column_ref code="ingredient.add" ref="recipe/administrator/add.do?ingredientId=${ingredient.id}&recipeId=${recipeId}"/>
			
	</security:authorize>
</display:table>

<acme:back code="ingredient.back" /> 
<input type="button" name="next" value="<spring:message code="recipe.next"/>" 
		onclick="javascript: window.location.replace('recipe/administrator/details.do')"/>
