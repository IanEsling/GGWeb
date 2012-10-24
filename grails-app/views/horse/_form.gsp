<%@ page import="geegees.model.Horse" %>



<div class="fieldcontain ${hasErrors(bean: horseInstance, field: 'decimalOdds', 'error')} required">
	<label for="decimalOdds">
		<g:message code="horse.decimalOdds.label" default="Decimal Odds" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="decimalOdds" value="${fieldValue(bean: horseInstance, field: 'decimalOdds')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: horseInstance, field: 'magicNumber', 'error')} required">
	<label for="magicNumber">
		<g:message code="horse.magicNumber.label" default="Magic Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="magicNumber" value="${fieldValue(bean: horseInstance, field: 'magicNumber')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: horseInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="horse.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${horseInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: horseInstance, field: 'odds', 'error')} ">
	<label for="odds">
		<g:message code="horse.odds.label" default="Odds" />
		
	</label>
	<g:textField name="odds" value="${horseInstance?.odds}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: horseInstance, field: 'race', 'error')} required">
	<label for="race">
		<g:message code="horse.race.label" default="Race" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="race" name="race.id" from="${geegees.model.Race.list()}" optionKey="id" required="" value="${horseInstance?.race?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: horseInstance, field: 'tips', 'error')} required">
	<label for="tips">
		<g:message code="horse.tips.label" default="Tips" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tips" type="number" value="${horseInstance.tips}" required=""/>
</div>

