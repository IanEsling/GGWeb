
<%@ page import="geegees.model.RaceDay" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'raceDay.label', default: 'RaceDay')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-raceDay" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-raceDay" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="raceDate" title="${message(code: 'raceDay.raceDate.label', default: 'Race Date')}" />

                        <td>Number of Races</td>

					</tr>
				</thead>
				<tbody>
				<g:each in="${raceDayInstanceList}" status="i" var="raceDayInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${raceDayInstance.id}">${fieldValue(bean: raceDayInstance, field: "raceDate")}</g:link></td>
					    <td>${raceDayInstance.races.size()}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${raceDayInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
