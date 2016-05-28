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

<security:authorize access="hasRole('ADMINISTRATOR')">
	<form:form action="${requestURI}" modelAttribute="ingredient">
		<jstl:if test="${edit == true}">
			<form:hidden path="id"/>
			<form:hidden path="version"/>
		</jstl:if>
			<form:hidden path="deleted"/>
		
		<acme:textbox code="ingredient.name" path="name"/>
		<acme:textarea code="ingredient.description" path="description"/>
		<acme:textbox code="ingredient.picture" path="picture"/>
		<acme:textbox code="ingredient.metricUnit" path="metricUnit"/>
		<br />
		
		<h2><b><spring:message code="ingredient.allergens" /></b></h2>
		<form:select path="allergens" multiple="true">
			<form:options items="${allergens}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors cssClass="error" path="allergens" />
		<br />
		<br />
		
		
		<jstl:choose>
		<jstl:when test="${edit == true}">
			<acme:submit name="update" code="ingredient.save"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="ingredient.save"/>
		</jstl:otherwise>
		</jstl:choose>
	
	
		<acme:back code="ingredient.back" />
	</form:form>
</security:authorize>
