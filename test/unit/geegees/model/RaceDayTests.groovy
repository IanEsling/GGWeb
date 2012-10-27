package geegees.model



import grails.test.mixin.*
import org.junit.*
import org.joda.time.LocalDate
import geegees.builders.RaceBuilder

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(RaceDay)
class RaceDayTests {

    @Test
    void saveRaceDay() {
        RaceDay raceDay = new RaceDay(raceDate: new LocalDate())
        Race race = new Race(venue: "venue", time: "12:00", numberOfRunners: 3)
        race.addToHorses(new Horse(name: "horse1", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse2", odds: "12/1"))
        race.addToHorses(new Horse(name: "horse3", odds: "12/1"))
        raceDay.addToRaces(race)
        raceDay.save()
        assertEquals("wrong number of racedays", 1, RaceDay.findAll())
    }
}
