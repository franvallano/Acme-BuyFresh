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

<security:authorize access="hasRole('USER')">
	<form:form action="${requestURI}" modelAttribute="user">
		
		<form:hidden path="email"/>
		<form:hidden path="surname"/>
		<form:hidden path="name"/>
		<form:hidden path="userAccount"/>
		<form:hidden path="phone"/>
		<form:hidden path="address"/>
		<form:hidden path="deleted"/>
		<form:hidden path="creditCard"/>
		<form:hidden path="assessments"/>
		<form:hidden path="subcriptions"/>
		
		<br />
		<h2><b><spring:message code="allergens" /></b></h2>
		<form:select path="allergens" multiple="true">
			<form:options items="${allergens}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors cssClass="error" path="allergens" />
		<br />
		
		
		<acme:submit name="update" code="allergen.save"/>
		
	
		<acme:back code="allergen.back" />
	</form:form>
</security:authorize>