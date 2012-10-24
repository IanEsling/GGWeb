
<%@ page import="geegees.model.Horse" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'horse.label', default: 'Horse')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-horse" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-horse" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="decimalOdds" title="${message(code: 'horse.decimalOdds.label', default: 'Decimal Odds')}" />
					
						<g:sortableColumn property="magicNumber" title="${message(code: 'horse.magicNumber.label', default: 'Magic Number')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'horse.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="odds" title="${message(code: 'horse.odds.label', default: 'Odds')}" />
					
						<th><g:message code="horse.race.label" default="Race" /></th>
					
						<g:sortableColumn property="tips" title="${message(code: 'horse.tips.label', default: 'Tips')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${horseInstanceList}" status="i" var="horseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${horseInstance.id}">${fieldValue(bean: horseInstance, field: "decimalOdds")}</g:link></td>
					
						<td>${fieldValue(bean: horseInstance, field: "magicNumber")}</td>
					
						<td>${fieldValue(bean: horseInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: horseInstance, field: "odds")}</td>
					
						<td>${fieldValue(bean: horseInstance, field: "race")}</td>
					
						<td>${fieldValue(bean: horseInstance, field: "tips")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${horseInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
