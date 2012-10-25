package geegees.model

import static org.junit.Assert.*
import org.junit.*

import static com.google.common.collect.Lists.newArrayList
import org.joda.time.LocalDate

class HorseIntegrationTests {

    @Test
    void shouldSaveHorsesWithRace() {
        Race race = new Race();
        race.venue = "My Venue"
        race.time = "10:10"
        race.numberOfRunners = 1
        Horse horse1 = new Horse(name: "myName", odds:"11/1")
        Horse horse2 = new Horse(name: "myOtherName", odds:"11/2")
        race.addToHorses(horse1)
        race.addToHorses(horse2)
        race.save(flush: true)

        assertNotNull("can't find my horse", Horse.findByName("myName"))
        assertNotNull("can't find my other horse", Horse.findByName("myOtherName"))
        Race savedRace = Race.findByVenue("My Venue")
        assertEquals("wrong number of horses in race", 2, savedRace.horses.size())
        assertEquals("wrong date for race", new LocalDate(), savedRace.getDate())
    }
}
