package geegees.model

import org.joda.time.LocalDate
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class HorseIntegrationTests {

    @Test
    void shouldSaveHorsesWithRace() {
        Race race = new Race();
        race.venue = "My Venue"
        race.time = "10:10"
        race.numberOfRunners = 1
        Horse horse1 = new Horse(name: "myName", odds: "11/1")
        Horse horse2 = new Horse(name: "myOtherName", odds: "11/2")
        race.addToHorses(horse1)
        race.addToHorses(horse2)
        race.save(flush: true)

        assertNotNull("can't find my horse", Horse.findByName("myName"))
        assertNotNull("can't find my other horse", Horse.findByName("myOtherName"))
        Race savedRace = Race.findByVenue("My Venue")
        assertEquals("wrong number of horses in race", 2, savedRace.horses.size())
        assertEquals("wrong raceDate for race", new LocalDate(), savedRace.getRaceDate())
    }

    @Test
    void shouldUpdateFinishingPosition() {
        Race race = new Race(venue: "Venue", time: "12:00", numberOfRunners: 3)
        race.addToHorses(new Horse(name: "horse3", odds: "1/1", tips: 3))
        race.addToHorses(new Horse(name: "horse1", odds: "2/1", tips: 2))
        race.addToHorses(new Horse(name: "horse2", odds: "3/1", tips: 1))
        race.save()
        Horse.findAllByRace(race).each {Horse horse ->
            horse.finishingPosition = horse.tips
            horse.save()
        }
        Race.findByVenue("Venue").horses.each {Horse horse ->
            assertEquals("wrong finishing position for $horse.name", horse.tips, horse.finishingPosition)
        }
    }
}
