<%@ page contentType="text/html;charset=UTF-8" %>
<h1>Les's Layer of Profit for ${raceDay.raceDate}: </h1>

<g:if test="${raceDay?.races}">
        <g:each in="${raceDay.races}" var="race">
            <table>
                <thead>
                <tr><th>Venue</th>
                    <th>Time</th>
                    <th>Number Of Runners</th>
                </tr>
                </thead>
                <tbody>
                <tr><td>${race.venue}</td>
                    <td>${race.time}</td>
                    <td>${race.numberOfRunners}</td>
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
                <g:each in="${race.horses}" var="horse">
                    <tr><td>${horse.name}</td>
                        <td>${horse.odds}</td>
                        <td>${horse.tips}</td>
                        <td>${horse.magicNumber}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:each>
</g:if>