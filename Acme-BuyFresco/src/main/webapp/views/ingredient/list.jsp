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
	<acme:textURL URL="ingredient/administrator/create.do" code="ingredient.create"/>
</security:authorize>

<display:table name="ingredients" pagesize="5" class="displaytag" requestURI="${requestURI}" id="ingredient">
	
	<acme:column code="ingredient.name" property="name"/>
	<acme:column code="ingredient.description" property="description"/>
	<acme:column code="ingredient.metricUnit" property="metricUnit"/>
	
	<acme:column_ref code="ingredient.details" ref="ingredient/details.do?ingredientId=${ingredient.id}"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">	
		<acme:column_ref code="ingredient.edit" ref="ingredient/administrator/edit.do?ingredientId=${ingredient.id}"/>
		
		<jstl:choose>
			<jstl:when test="${!ingredient.deleted }">
				<display:column>
					<acme:column_ref_ConfirmDelete code="ingredient.delete" ref="ingredient/administrator/delete.do?ingredientId=${ingredient.id}" codeConfirm="ingredient.confirm.delete"/>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<display:column>
					<spring:message code="ingredient.isDeleted"/>
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
		
	</security:authorize>
</display:table>

<acme:back code="ingredient.back" />