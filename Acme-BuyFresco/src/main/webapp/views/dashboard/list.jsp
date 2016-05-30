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
		<fieldset class="panel panel-default">	
			<div class="panel-body">
			<b><spring:message code="dashboard.numUsers"/></b>
			<br/><br/>
			<acme:labelDetails code="dashboard.total" value="${numUser}"/>
			</div>
		</fieldset>
		<br/>
		
		<fieldset class="panel panel-default">	
			<div class="panel-body">
			<b><spring:message code="dashboard.subscriptionsActives"/></b>
			<br/><br/>
			<acme:labelDetails code="dashboard.total" value="${subscriptionsActives}"/>
			</div>
		</fieldset>
		<br/>
		
		<fieldset class="panel panel-default">	
			<div class="panel-body">
			<b><spring:message code="dashboard.ordersSent"/></b>
			<br/><br/>
			<acme:labelDetails code="dashboard.total" value="${ordersSent}"/>
			</div>
		</fieldset>
		<br/>	
		
		<fieldset class="panel panel-default">	
			<div class="panel-body">
			<b><spring:message code="dashboard.avgRatingSystem"/></b>
			<br/><br/>
			<acme:labelDetails code="dashboard.total" value="${avgRatingSystem}"/>
			</div>
		</fieldset>
		<br/>
			
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.subscriptionsLastMonth"/></b>
			<br/>
			<jstl:forEach var="subs" items="${subscriptionsLastMonth}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="creationMoment" value="${subs.creationMoment}" />
				<acme:labelDetails code="finishMoment" value="${subs.finishMoment}" />
				<acme:labelDetails code="price" value="${subs.price}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.subscriptionsLastWeek"/></b>
			<br/>
			<jstl:forEach var="subs" items="${subscriptionsLastWeek}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="creationMoment" value="${subs.creationMoment}" />
				<acme:labelDetails code="finishMoment" value="${subs.finishMoment}" />
				<acme:labelDetails code="price" value="${subs.price}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.menuInMoreOrders"/></b>
			<br/>
			<jstl:forEach var="menu" items="${menuInMoreOrders}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${menu.name}" />
				<acme:labelDetails code="startingMoment" value="${menu.startingMoment}" />
				<acme:labelDetails code="people" value="${menu.people}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.menuInLessOrders"/></b>
			<br/>
			<jstl:forEach var="menu" items="${menuInLessOrders}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${menu.name}" />
				<acme:labelDetails code="startingMoment" value="${menu.startingMoment}" />
				<acme:labelDetails code="people" value="${menu.people}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.usersMoreSubscriptions"/></b>
			<br/>
			<jstl:forEach var="user" items="${usersMoreSubscriptions}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${user.name}" />
				<acme:labelDetails code="surname" value="${user.surname}" />
				<acme:labelDetails code="email" value="${user.email}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.usersMoreAssessments"/></b>
			<br/>
			<jstl:forEach var="user" items="${usersMoreAssessments}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${user.name}" />
				<acme:labelDetails code="surname" value="${user.surname}" />
				<acme:labelDetails code="email" value="${user.email}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.allergenMoreUsers"/></b>
			<br/>
			<jstl:forEach var="all" items="${allergenMoreUsers}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${all.name}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.assessmentsRatingBigger"/></b>
			<br/>
			<jstl:forEach var="all" items="${assessmentsRatingBigger}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="text" value="${all.text}" />
				<acme:labelDetails code="rating" value="${all.rating}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.salesOrdersWithoutAssign"/></b>
			<br/>
			<jstl:forEach var="all" items="${salesOrdersWithoutAssign}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="creationMoment" value="${all.subscription.creationMoment}" />
				<acme:labelDetails code="name" value="${all.subscription.user.name}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.clerkMoreSalesOrders"/></b>
			<br/>
			<jstl:forEach var="user" items="${clerkMoreSalesOrders}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${user.name}" />
				<acme:labelDetails code="surname" value="${user.surname}" />
				<acme:labelDetails code="email" value="${user.email}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.clerkMoreSalesOrders"/></b>
			<br/>
			<jstl:forEach var="user" items="${clerkMoreSalesOrders}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${user.name}" />
				<acme:labelDetails code="surname" value="${user.surname}" />
				<acme:labelDetails code="email" value="${user.email}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
		
		<fieldset class="panel panel-default">
			<div class="panel-body">
			<b><spring:message code="dashboard.clerkLessSalesOrders"/></b>
			<br/>
			<jstl:forEach var="user" items="${clerkLessSalesOrders}" varStatus="rowIndex">
				<br/>
				<acme:labelDetails code="name" value="${user.name}" />
				<acme:labelDetails code="surname" value="${user.surname}" />
				<acme:labelDetails code="email" value="${user.email}" />
			</jstl:forEach>
			</div>
		</fieldset>
		<br/>
</security:authorize>