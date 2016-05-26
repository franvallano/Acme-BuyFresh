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
	<form:form action="${requestURI}" modelAttribute="allergen">
		<jstl:if test="${edit == true}">
			<form:hidden path="id"/>
			<form:hidden path="version"/>
		</jstl:if>
		<form:hidden path="users"/>
		<form:hidden path="recipes"/>
		
		<acme:textbox code="allergen.name" path="name"/>
		<br />
		
		<br />
		<h2><b><spring:message code="ingredients" /></b></h2>
		<form:select path="allergenIngredients" multiple="true">
			<form:options items="${ingredients}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors cssClass="error" path="allergenIngredients" />
		<br />
		
		<br />
		<h2><b><spring:message code="substitutes" /></b></h2>
		<form:select path="substitutes" multiple="true">
			<form:options items="${substitutes}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors cssClass="error" path="substitutes" />
		<br />
		
		
		<jstl:choose>
		<jstl:when test="${edit == true}">
			<acme:submit name="update" code="allergen.save"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="allergen.save"/>
		</jstl:otherwise>
		</jstl:choose>
	
	
		<acme:back code="allergen.back" />
	</form:form>
</security:authorize>
