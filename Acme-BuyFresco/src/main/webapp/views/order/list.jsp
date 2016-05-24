<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<jstl:if test="${edit}">
		<jstl:choose>
		<jstl:when test="${sizecero}">
			<display:table name="order" id="order" pagesize="5" requestURI="${requestURI}" class="displaytag">
				
				<acme:column code="order.address" property="address"/>	
				<acme:column code="order.shippingDate" property="shippingDate"/>
				<acme:column code="order.arrivalDate" property="arrivalDate"/>	
				<acme:column code="order.notes" property="notes"/>	
				<acme:column code="order.sent" property="sent"/>
				
				<security:authorize access="hasRole('CLERK')">
					<acme:column_ref_condition code="order.edit" ref="order/clerk/edit.do?orderId=${order.id}" condition="${order.sent == false }"/>
				</security:authorize>
				
				<security:authorize access="hasRole('USER')">
					<acme:column_ref code="order.details" ref="order/user/details.do?orderId=${order.id}"/>
				
					<acme:column_ref_condition code="order.edit" ref="order/user/edit.do?orderId=${order.id}" condition="${order.sent == false }"/>
				</security:authorize>
				
			</display:table>
		</jstl:when>
		
		<jstl:otherwise>
			<h2><b><spring:message code="order.notFound" /></b></h2>
		</jstl:otherwise>
	</jstl:choose>
</jstl:if>

<jstl:if test="${edit == false}">
	<jstl:choose>
		<jstl:when test="${sizecero}">
			<display:table name="order" id="order" pagesize="5" requestURI="${requestURI}" class="displaytag">
			
				<acme:column code="order.address" property="address"/>	
				<acme:column code="order.shippingDate" property="shippingDate"/>
				<acme:column code="order.arrivalDate" property="arrivalDate"/>	
				<acme:column code="order.notes" property="notes"/>	
				<acme:column code="order.sent" property="sent"/>
				<acme:column_ref code="order.assign" ref="order/clerk/assign.do?orderId=${order.id}"/>
		
			</display:table>
		</jstl:when>
		
		<jstl:otherwise>
			<h2><b><spring:message code="order.notFound" /></b></h2>
		</jstl:otherwise>
	</jstl:choose>

</jstl:if>