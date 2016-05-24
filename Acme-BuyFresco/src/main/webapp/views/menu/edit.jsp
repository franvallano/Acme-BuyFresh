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
	<form:hidden path="ingredients"/>
	<form:hidden path="creationMoment"/>

	<acme:textbox code="menu.name" path="name" />
	<br />
		<acme:textbox code="menu.startingMoment" path="startingMoment" />
	<br />
		<acme:textbox code="menu.duration" path="duration" />
	<br />
		<acme:textbox code="menu.people" path="people" />
	<br />
		<acme:textbox code="menu.type" path="type" />
	<br />
	
	<acme:select items="${recipes}" itemLabel="name" multiple ="true" code="menu.recipes" path="recipes"/>
	
	<acme:submit code="menu.save" name="update" />&nbsp;
		
	
	<acme:cancel url="menu/administrator/list.do" code="menu.cancel"/>
	<br />

</form:form>