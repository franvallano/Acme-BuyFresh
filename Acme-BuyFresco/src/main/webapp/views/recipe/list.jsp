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

<display:table name="recipes" pagesize="5" class="displaytag" requestURI="${requestURI}" id="recipe">
	
	<acme:column code="recipe.name" property="name"/>
	<acme:column code="recipe.time" property="time"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">	
		<acme:column_ref code="recipe.edit" ref="recipe/administrator/edit.do?recipeId=${recipe.id}"/>
	</security:authorize>
		
	<acme:column_ref code="recipe.details" ref="recipe/details.do?recipeId=${recipe.id}"/>

</display:table>

<acme:back code="ingredient.back" />