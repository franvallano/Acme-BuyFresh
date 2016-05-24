<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

	<display:table name="menu" id="menu" pagesize="5" requestURI="${requestURI}" class="displaytag">
		
		<acme:column code="menu.name" property="name"/>	
		<acme:column code="menu.creationMoment" property="creationMoment"/>
		<acme:column code="menu.startingMoment" property="startingMoment"/>	
		<acme:column code="menu.duration" property="duration"/>	
		<acme:column code="menu.people" property="people"/>	
		<acme:column code="menu.type" property="type"/>	
		<acme:column code="menu.deleted" property="deleted"/>	
		<acme:column code="menu.creationMoment" property="creationMoment"/>
		<acme:column_ref code="menu.recipes" ref="recipe/administrator/list.do?menuId=${menu.id}"/>
		<acme:column_ref code="menu.edit" ref="menu/administrator/edit.do?menuId=${menu.id}"/>
		<acme:column_ref_ConfirmDelete code="menu.delete" codeConfirm="menu.confirm.delete" ref="menu/administrator/delete.do?menuId=${menu.id}"/>
	
		
	</display:table>