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


<display:table name="subscriptions" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<acme:column code="subscription.price" property="price"/>
	
	<acme:column code="subscription.finishMoment" property="finishMoment"/>
	
	<acme:column code="subscription.creationMoment" property="creationMoment"/>
	
	<acme:column code="subscription.renewal" property="renewal"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">	
		<acme:column_ref code="subscription.renewal" ref="subscription/user/renewal.do?subscriptionId=${row.id}"/>
	</security:authorize>
</display:table>

<acme:back code="subscription.back" />