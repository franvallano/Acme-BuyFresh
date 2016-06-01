<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="menu">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="orders"/>
	<form:hidden path="creationMoment"/>

	<acme:textbox code="menu.name" path="name" />
	<br />
		<acme:textbox code="menu.startingMoment" path="startingMoment" />
	<br />
		<acme:textbox code="menu.finishMoment" path="finishMoment" />
	<br />
		<acme:textbox code="menu.people" path="people" />
	<br />
		<acme:textbox code="menu.type" path="type" />
	<br />
		<acme:textbox code="menu.price" path="price" />
	<br />
	
	<h2><b><spring:message code="menu.recipes" /></b></h2>
	<form:select path="recipes" multiple="true">
		<form:options items="${recipes}" itemLabel="name" itemValue="id" />
	</form:select>
	<form:errors cssClass="error" path="recipes" />
	<br /><br />
	
	
	<acme:submit code="menu.save" name="save" />&nbsp;
		
	<acme:back code="menu.cancel"/>
	
	<br />

</form:form>