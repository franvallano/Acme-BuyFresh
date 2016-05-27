<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-BuyFresco Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.menu" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="menu/administrator/list.do"><spring:message code="master.page.menu.list" /></a></li>
					<li><a href="menu/administrator/create.do"><spring:message code="master.page.menu.create" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.allergens" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="allergen/administrator/list.do"><spring:message code="master.page.allergen.list" /></a></li>
					<li><a href="allergen/administrator/create.do"><spring:message code="master.page.allergen.create" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv" href="assessment/list.do"><spring:message code="master.page.assessmentList" /></a></li>
			<li><a class="fNiv" href="clerk/register.do"><spring:message code="master.page.registerClerk" /></a></li>
			<li>
				<a class="dropdown-toggle" data-toggle="dropdown" href=""> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li class="arrow"></li>	
					<li><a href="profile/actor/edit.do"><spring:message code="master.page.viewProfile" /></a></li>
					<li><a href="profile/actor/changePassword.do"><spring:message code="master.page.changePassword" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CLERK')">
					
			<li><a class="fNiv"><spring:message	code="master.page.order" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="order/clerk/listWithoutClerk.do"><spring:message code="master.page.order.listWithoutClerk" /></a></li>
					<li><a href="order/clerk/myOrders.do"><spring:message code="master.page.order.myOrders" /></a></li>					
				</ul>
			</li>
			<li>
				<a class="dropdown-toggle" data-toggle="dropdown" href=""> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li class="arrow"></li>	
					<li><a href="profile/actor/edit.do"><spring:message code="master.page.viewProfile" /></a></li>
					<li><a href="profile/actor/changePassword.do"><spring:message code="master.page.changePassword" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message	code="master.page.subscriptions" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="subscription/user/list.do"><spring:message code="master.page.subscriptionList" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.assessments" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="assessment/user/list.do"><spring:message code="master.page.myAssessments" /></a></li>					
				</ul>
			</li>			
			<li><a class="fNiv"><spring:message	code="master.page.order" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="order/user/myOrders.do"><spring:message code="master.page.order.myOrders" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.menu" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="menu/user/activeMenus.do"><spring:message code="master.page.menu.activeMenu" /></a></li>					
				</ul>
			</li>
			<li>
				<a class="dropdown-toggle" data-toggle="dropdown" href=""> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li class="arrow"></li>	
					<li><a href="profile/user/edit.do"><spring:message code="master.page.viewProfile" /></a></li>
					<li><a href="profile/user/changePassword.do"><spring:message code="master.page.changePassword" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="menu/activeMenus.do"><spring:message code="master.page.menu.activeMenu" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="register/user/register.do"><spring:message code="master.page.register" /></a></li>
			<li><a class="fNiv" href="assessment/list.do"><spring:message code="master.page.assessmentList" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

