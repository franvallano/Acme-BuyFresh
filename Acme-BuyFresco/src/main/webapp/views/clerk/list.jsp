<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<display:table name="clerks" id="clerksRow" requestURI="${requestURI}" pagesize="10" class="table table-striped">
	
		<security:authorize access="hasRole('ADMINISTRATOR')">
		
			<spring:message code="clerk.username" var="usernameHeader" />
			<display:column property="userAccount.username" title="${usernameHeader}" sortable="true" />
			
			<spring:message code="clerk.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}" sortable="true" />
			
			<spring:message code="clerk.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" sortable="true" />
			
			<spring:message code="clerk.email" var="emailHeader" />
			<display:column property="email" title="${emailHeader}" sortable="true" />
			
			<spring:message code="clerk.deleted" var="deletedHeader" />
			<display:column property="deleted" title="${deletedHeader}" sortable="true" />
			
			<jstl:if test="${clerksRow.deleted == true}">
				<display:column>
					<a href="clerk/administrator/deactivate.do?clerkId=${clerksRow.id}" onclick="return confirm('<spring:message code="clerk.confirm.deactivate" />')">
						<spring:message code="clerk.deactivate"/> </a>
				</display:column>
			</jstl:if>
			
			<jstl:if test="${clerksRow.deleted == false}">
				<display:column>
					<a href="clerk/administrator/activate.do?clerkId=${clerksRow.id}" onclick="return confirm('<spring:message code="clerk.confirm.activate" />')">
						<spring:message code="clerk.activate"/> </a>
				</display:column>
			</jstl:if>

		</security:authorize>
	
	</display:table>