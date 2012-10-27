<%@ page import="geegees.model.RaceDay" %>



<div class="fieldcontain ${hasErrors(bean: raceDayInstance, field: 'raceDate', 'error')} required">
	<label for="raceDate">
		<g:message code="raceDay.raceDate.label" default="Race Date" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: raceDayInstance, field: 'races', 'error')} ">
	<label for="races">
		<g:message code="raceDay.races.label" default="Races" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${raceDayInstance?.races?}" var="r">
    <li><g:link controller="race" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="race" action="create" params="['raceDay.id': raceDayInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'race.label', default: 'Race')])}</g:link>
</li>
</ul>

</div>

