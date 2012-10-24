
<%@ page import="geegees.model.Race" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'race.label', default: 'Race')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-race" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-race" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list race">
			
				<g:if test="${raceInstance?.bettable}">
				<li class="fieldcontain">
					<span id="bettable-label" class="property-label"><g:message code="race.bettable.label" default="Bettable" /></span>
					
						<span class="property-value" aria-labelledby="bettable-label"><g:formatBoolean boolean="${raceInstance?.bettable}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${raceInstance?.horses}">
				<li class="fieldcontain">
					<span id="horses-label" class="property-label"><g:message code="race.horses.label" default="Horses" /></span>
					
						<g:each in="${raceInstance.horses}" var="h">
						<span class="property-value" aria-labelledby="horses-label"><g:link controller="horse" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${raceInstance?.numberOfRunners}">
				<li class="fieldcontain">
					<span id="numberOfRunners-label" class="property-label"><g:message code="race.numberOfRunners.label" default="Number Of Runners" /></span>
					
						<span class="property-value" aria-labelledby="numberOfRunners-label"><g:fieldValue bean="${raceInstance}" field="numberOfRunners"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${raceInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="race.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${raceInstance}" field="time"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${raceInstance?.venue}">
				<li class="fieldcontain">
					<span id="venue-label" class="property-label"><g:message code="race.venue.label" default="Venue" /></span>
					
						<span class="property-value" aria-labelledby="venue-label"><g:fieldValue bean="${raceInstance}" field="venue"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${raceInstance?.id}" />
					<g:link class="edit" action="edit" id="${raceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
