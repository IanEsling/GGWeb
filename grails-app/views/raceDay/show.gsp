<%@ page import="geegees.model.RaceDay" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'raceDay.label', default: 'RaceDay')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-raceDay" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-raceDay" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list raceDay">

        <g:if test="${raceDayInstance?.raceDate}">
            <li class="fieldcontain">
                <span id="raceDate-label" class="property-label"><g:message code="raceDay.raceDate.label"
                                                                            default="Race Date"/></span>

                <span class="property-value" aria-labelledby="raceDate-label"><g:fieldValue bean="${raceDayInstance}"
                                                                                            field="raceDate"/></span>

            </li>
        </g:if>

        <g:if test="${raceDayInstance?.races}">
            <li class="fieldcontain">
            %{--<span id="races-label" class="property-label"><g:message code="raceDay.races.label"--}%
            %{--default="Races"/></span>--}%

                <g:each in="${raceDayInstance.races}" var="raceInstance">
                %{--<span class="property-value" aria-labelledby="races-label"><g:link controller="race" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>--}%
                    <table>
                        <thead>
                        <tr><th>Venue</th>
                            <th>Time</th>
                            <th>Number Of Runners</th>
                            <th>Bettable</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><td>${raceInstance.venue}</td>
                            <td>${raceInstance.time}</td>
                            <td>${raceInstance.numberOfRunners}</td>
                            <td>${raceInstance.bettable}</td>
                        </tr>
                        </tbody>
                    </table>

                    <table>
                        <thead>
                        <tr><th>Name</th>
                            <th>Odds</th>
                            <th>Tips</th>
                            <th>Magic Number</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${raceInstance.horses}" var="horse">
                            <tr><td>${horse.name}</td>
                                <td>${horse.odds}</td>
                                <td>${horse.tips}</td>
                                <td>${horse.magicNumber}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${raceDayInstance?.id}"/>
            <g:link class="edit" action="edit" id="${raceDayInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
