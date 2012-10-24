<%@ page import="geegees.model.Race" %>



<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'bettable', 'error')} ">
	<label for="bettable">
		<g:message code="race.bettable.label" default="Bettable" />
		
	</label>
	<g:checkBox name="bettable" value="${raceInstance?.bettable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'horses', 'error')} ">
	<label for="horses">
		<g:message code="race.horses.label" default="Horses" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${raceInstance?.horses?}" var="h">
    <li><g:link controller="horse" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="horse" action="create" params="['race.id': raceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'horse.label', default: 'Horse')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'numberOfRunners', 'error')} required">
	<label for="numberOfRunners">
		<g:message code="race.numberOfRunners.label" default="Number Of Runners" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfRunners" type="number" value="${raceInstance.numberOfRunners}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'time', 'error')} ">
	<label for="time">
		<g:message code="race.time.label" default="Time" />
		
	</label>
	<g:textField name="time" value="${raceInstance?.time}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'venue', 'error')} ">
	<label for="venue">
		<g:message code="race.venue.label" default="Venue" />
		
	</label>
	<g:textField name="venue" value="${raceInstance?.venue}"/>
</div>

