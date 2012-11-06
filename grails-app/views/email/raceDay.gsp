<%@ page contentType="text/html" %>
<!DOCTYPE html>
<html>
<head>
    %{--<style>--}%
    %{--body {--}%
        %{--/*background-color: #90ee90;*/--}%
        %{--font-family: Helvetica, Arial, sans-serif;--}%
    %{--}--}%

    %{--h1, h2 {--}%
        %{--font-size: 24pt;--}%
        %{--text-align: center;--}%
        %{--width: 50%;--}%
        %{--margin: auto;--}%
    %{--}--}%

    %{--table {--}%
        %{--width: 50%;--}%
        %{--text-align: center;--}%
        %{--border-top: solid 2px black;--}%
        %{--margin: auto;--}%
    %{--}--}%

    %{--.race {--}%
        %{--text-align: right;--}%
        %{--width: 30%--}%
    %{--}--}%

    %{--.horses {--}%
        %{--text-align: center;--}%
        %{--width: 70%;--}%
    %{--}--}%

    %{--.magic-number {--}%
        %{--font-weight: bold;--}%
        %{--font-size: 14pt;--}%
    %{--}--}%

    %{--.time {--}%
        %{--font-size: 24pt;--}%
    %{--}--}%

    %{--.venue {--}%
        %{--font-size: 14pt;--}%
    %{--}--}%

    %{--.runners {--}%
        %{--font-size: 11pt;--}%
    %{--}--}%

    %{--hr {--}%
        %{--color: black;--}%
    %{--}--}%
    %{--</style>--}%

</head>

<body style="font-family: Helvetica, Arial, sans-serif;" bgcolor="#90ee90">

<h1 style="font-size: 24pt;text-align: center;width: 50%;margin: auto;">Les of Profit</h1>

<h2 style="font-size: 20pt;text-align: center;width: 50%;margin: auto;">Bettable races for ${raceDay.raceDate}:</h2>

<g:if test="${raceDay?.bettableRaces}">
    <g:each in="${raceDay.bettableRaces}" var="race">
        <table style="width: 50%;text-align: center;border-top: solid 2px black;margin: auto;">
            <tr>
                <td style="text-align: right;width: 30%">
                    <div>
                        <p style="font-size: 24pt;">${race.time}</p>

                        <p style="font-size: 14pt;">${race.venue}</p>

                        <p style="font-size: 11pt;">Number of runners: ${race.numberOfRunners}</p>
                    </div>
                </td>
                <td style="text-align: center;width: 70%;">
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