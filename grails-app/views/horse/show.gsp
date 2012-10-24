
<%@ page import="geegees.model.Horse" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'horse.label', default: 'Horse')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-horse" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-horse" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list horse">
			
				<g:if test="${horseInstance?.decimalOdds}">
				<li class="fieldcontain">
					<span id="decimalOdds-label" class="property-label"><g:message code="horse.decimalOdds.label" default="Decimal Odds" /></span>
					
						<span class="property-value" aria-labelledby="decimalOdds-label"><g:fieldValue bean="${horseInstance}" field="decimalOdds"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${horseInstance?.magicNumber}">
				<li class="fieldcontain">
					<span id="magicNumber-label" class="property-label"><g:message code="horse.magicNumber.label" default="Magic Number" /></span>
					
						<span class="property-value" aria-labelledby="magicNumber-label"><g:fieldValue bean="${horseInstance}" field="magicNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${horseInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="horse.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${horseInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${horseInstance?.odds}">
				<li class="fieldcontain">
					<span id="odds-label" class="property-label"><g:message code="horse.odds.label" default="Odds" /></span>
					
						<span class="property-value" aria-labelledby="odds-label"><g:fieldValue bean="${horseInstance}" field="odds"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${horseInstance?.race}">
				<li class="fieldcontain">
					<span id="race-label" class="property-label"><g:message code="horse.race.label" default="Race" /></span>
					
						<span class="property-value" aria-labelledby="race-label"><g:link controller="race" action="show" id="${horseInstance?.race?.id}">${horseInstance?.race?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${horseInstance?.tips}">
				<li class="fieldcontain">
					<span id="tips-label" class="property-label"><g:message code="horse.tips.label" default="Tips" /></span>
					
						<span class="property-value" aria-labelledby="tips-label"><g:fieldValue bean="${horseInstance}" field="tips"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${horseInstance?.id}" />
					<g:link class="edit" action="edit" id="${horseInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
