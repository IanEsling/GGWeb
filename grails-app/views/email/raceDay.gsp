<%@ page contentType="text/html" %>
<!DOCTYPE html>
<html>
<head>
</head>

<body style="font-family: Helvetica, Arial, sans-serif; background-color: #90ee90">
<div style="text-align: center"> <img src="http://geegees.heroku.com/geegees/images/dad.jpg"/> </div>
<h1 style="font-size: 24pt; padding-top: 10px; text-align: center;width: 80%;margin: auto;">Les of Profit</h1>

<h2 style="font-size: 20pt;text-align: center;width: 80%;margin: auto;">Bettable races for <joda:format style="L-" value="${raceDay.raceDate}"/>:</h2>

<g:if test="${raceDay?.bettableRaces}">
    <g:each in="${raceDay.bettableRaces}" var="race">
        <table style="width: 80%;text-align: center;border-top: solid 2px black;margin: auto;">
            <tr>
                <td style="text-align: right;width: 50%">
                    <div>
                        <p style="font-size: 24pt;">${race.time}</p>

                        <p style="font-size: 14pt;">${race.venue}</p>

                        <p style="font-size: 11pt;">Number of runners: ${race.numberOfRunners}</p>
                    </div>
                </td>
                <td style="text-align: center;width: 50%;">
                    <div>
                        <g:each in="${race.horses}" var="horse">
                            <p>${horse.name} - ${horse.odds}</p>

                            <p style="font-weight: bold;font-size: 14pt;">${horse.magicNumber}</p>
                        </g:each>
                    </div>
                </td>
            </tr>
        </table>
    </g:each>
</g:if>
</body>