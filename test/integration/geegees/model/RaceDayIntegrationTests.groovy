package geegees.model

import static org.junit.Assert.*
import org.junit.*
import org.joda.time.LocalDate

class RaceDayIntegrationTests {

    @Test
    void saveRaceDay() {
        RaceDay raceDay = new RaceDay(raceDate: new LocalDate())
        Race race = new Race(venue: "venue", time: "12:00", numberOfRunners: 3)
        race.addToHorses(new Horse(name: "horse1", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse2", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse3", odds: "12/1"))
        raceDay.addToRaces(race)
        raceDay.save()
        assertEquals("wrong number of racedays", 1, RaceDay.findAll().size())
    }
}
