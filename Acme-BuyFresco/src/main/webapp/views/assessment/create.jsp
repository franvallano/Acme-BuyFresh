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

	<form:form action="${requestURI}" modelAttribute="assessment">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="user"/>
		<form:hidden path="moment"/>
		
		<acme:textbox code="assessment.rating" path="rating"/>
		<br />
		<acme:textbox code="assessment.text" path="text"/>
		<br />
			
		<acme:submit name="save" code="assessment.save"/>
		
		<acme:back code="assessment.back"/>
	</form:form>

</security:authorize>
