<%@ page contentType="text/html" %>
<!DOCTYPE html>
<html>
<head>
    <style>
    body {
        background-color: #90ee90;
        font-family: Helvetica, Arial, sans-serif;
    }

    h1, h2 {
        font-size: 2em;
        text-align: center;
        width: 50%;
        margin: auto;
    }

    table {
        width: 50%;
        text-align: center;
        border-top: solid 2px black;
        margin: auto;
    }

    .race {
        text-align: right;
        width: 30%
    }

    .horses {
        text-align: center;
        width: 70%;
    }

    .magic-number {
        font-weight: bold;
        font-size: 1.25em;
    }

    .time {
        font-size: 2em;
    }

    .venue {
        font-size: 1.25em;
    }

    .runners {
        font-size: 0.9em;
    }

        hr {
            color: black;
        }
    </style>

</head>

<body>
<h1>Les's Layer of Profit</h1>
<h2>Bettable races for ${raceDay.raceDate}:</h2>

<g:if test="${raceDay?.bettableRaces}">
    <g:each in="${raceDay.bettableRaces}" var="race">
        <table>
            <tr>
                <td class="race">
                    <div>
                        <p class="time">${race.time}</p>

                        <p class="venue">${race.venue}</p>

                        <p class="runners">Number of runners: ${race.numberOfRunners}</p>
                    </div>
                </td>
                <td class="horses">
                    <div>
                        <g:each in="${race.horses}" var="horse">
                            <p>${horse.name} - ${horse.odds}</p>
                                <p class="magic-number">${horse.magicNumber}</p>
                        </g:each>
                    </div>
                </td>
            </tr>
        </table>
    </g:each>
</g:if>
</body>