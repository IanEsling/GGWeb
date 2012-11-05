package geegees.web

import geegees.model.RaceDay
import geegees.model.Race
import org.joda.time.LocalDate
import geegees.model.Horse

import static com.google.common.collect.Lists.newArrayList

class EmailPresentableRaceDay {

    RaceDay raceDay

    EmailPresentableRaceDay(RaceDay raceDay) {
        this.raceDay = raceDay
    }

    public LocalDate getRaceDate(){
        return raceDay.raceDate
    }

    public List<EmailPresentableRace> getBettableRaces(){
        List<EmailPresentableRace> races = newArrayList(raceDay.races.findAll {race ->
            race.bettable
        }.collect {race ->
            new EmailPresentableRace(race)
        })
        Collections.sort(races)
        return races
    }
}
