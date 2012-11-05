package geegees.web

import geegees.model.RaceDay
import geegees.model.Race


class EmailPresentableRaceDay {

    RaceDay raceDay

    EmailPresentableRaceDay(RaceDay raceDay) {
        this.raceDay = raceDay
    }

    public Set<Race> getBettableRaces(){
        return raceDay.races.findAll {race ->
            race.bettable
        }.each {race ->
            new EmailPresentableRace(race)
        }
    }
}
