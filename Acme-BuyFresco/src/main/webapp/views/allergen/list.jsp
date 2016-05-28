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

<security:authorize access="hasRole('ADMINISTRATOR')">
	<acme:textURL URL="allergen/administrator/create.do" code="allergen.create"/>
</security:authorize>

<security:authorize access="hasRole('USER')">
	<acme:textURL URL="allergen/user/select.do" code="allergen.select"/>
</security:authorize>

<display:table name="allergens" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<acme:column code="allergen.name" property="name"/>
	
	<acme:column code="substitutes" property="substitutes"/>
	
	<acme:column code="ingredients" property="allergenIngredients"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">	
		<acme:column_ref code="allergen.edit" ref="allergen/administrator/edit.do?allergenId=${row.id}"/>
	</security:authorize>
</display:table>

<acme:back code="allergen.back" />