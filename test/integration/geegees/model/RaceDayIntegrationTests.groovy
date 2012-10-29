package geegees.model

import static org.junit.Assert.*
import org.junit.*
import org.joda.time.LocalDate

class RaceDayIntegrationTests {

    @Test
    void saveRaceDay() {
        //given
        RaceDay raceDay = new RaceDay(raceDate: new LocalDate())
        Race race = new Race(venue: "venue", time: "12:00", numberOfRunners: 3)
        race.addToHorses(new Horse(name: "horse1", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse2", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse3", odds: "12/1"))
        raceDay.addToRaces(race)

        //when
        raceDay.save()

        //then
        assertEquals("wrong number of racedays", 1, RaceDay.findAll().size())
    }

    @Test
    void addHorsesToARace() {
        //given
        RaceDay raceDay = new RaceDay(raceDate: new LocalDate())
        Race race = new Race(venue: "venue", time: "12:00", numberOfRunners: 3)
        raceDay.addToRaces(race)
        raceDay.save()
        assertEquals("wrong number of racedays", 1, RaceDay.findAll().size())
        raceDay = RaceDay.findByRaceDate(new LocalDate())
        assertNotNull("couldn't load race day", raceDay)
        assertEquals("wrong number of races", 1, raceDay.races.size())
        assertNull("no horses should be at the races yet", raceDay.races.first().horses)

        //when
        race.addToHorses(new Horse(name: "horse1", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse2", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse3", odds: "12/1"))
        raceDay.save()

        //then
        assertEquals("should have horses at the races", 3, raceDay.races.first().horses.size())
    }
}


