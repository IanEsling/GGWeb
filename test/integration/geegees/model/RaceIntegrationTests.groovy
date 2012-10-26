package geegees.model

import static org.junit.Assert.*
import org.junit.*
import org.joda.time.LocalDate

class RaceIntegrationTests {

    @Test
    void getByDate() {
        Race yesterday = new Race(numberOfRunners: 2, time: "12:00", venue: "yesterday", date: new LocalDate().minusDays(1))
        yesterday.addToHorses(new Horse(name: "yesterday fav", odds: "1/1"))
        yesterday.addToHorses(new Horse(name: "yesterday not fav", odds: "2/1"))
        Race today = new Race(numberOfRunners: 1, time: "12:00", venue: "today")
        today.addToHorses(new Horse(name: "today fav", odds: "1/1"))
        today.addToHorses(new Horse(name: "today not fav 1", odds: "2/1"))
        today.addToHorses(new Horse(name: "today not fav 2", odds: "3/1"))
        yesterday.save(flush: true)
        today.save(flush: true)
        assertEquals("wrong number of races for today", 1, Race.findAllByDate(new LocalDate()).size())
        assertEquals("wrong race today", "today", Race.findByDate(new LocalDate()).venue)
        assertEquals("wrong number of horses for race today", 3, Race.findByDate(new LocalDate()).horses.size())
        assertEquals("wrong number of races for yesterday", 1, Race.findAllByDate(new LocalDate().minusDays(1)).size())
        assertEquals("wrong race yesterday", "yesterday", Race.findByDate(new LocalDate().minusDays(1)).venue)
        assertEquals("wrong number of horses for race yesterday", 2, Race.findByDate(new LocalDate().minusDays(1)).horses.size())
    }

    @Test
    void getListOfDistinctDates(){
        Race yesterday1 = new Race(numberOfRunners: 2, time: "12:00", venue: "yesterday", date: new LocalDate().minusDays(1))
        yesterday1.addToHorses(new Horse(name: "yesterday1 fav", odds: "1/1"))
        yesterday1.addToHorses(new Horse(name: "yesterday1 not fav", odds: "2/1"))
        Race yesterday2 = new Race(numberOfRunners: 4, time: "13:00", venue: "yesterday", date: new LocalDate().minusDays(1))
        yesterday2.addToHorses(new Horse(name: "yesterday2 fav", odds: "3/1"))
        yesterday2.addToHorses(new Horse(name: "yesterday2 not fav1", odds: "4/1"))
        yesterday2.addToHorses(new Horse(name: "yesterday2 not fav2", odds: "5/1"))
        yesterday2.addToHorses(new Horse(name: "yesterday2 not fav3", odds: "6/1"))

        Race today = new Race(numberOfRunners: 1, time: "12:00", venue: "today")
        today.addToHorses(new Horse(name: "today fav", odds: "1/1"))
        today.addToHorses(new Horse(name: "today not fav 1", odds: "2/1"))
        today.addToHorses(new Horse(name: "today not fav 2", odds: "3/1"))
        yesterday1.save(flush: true)
        yesterday2.save(flush: true)
        today.save(flush: true)
        assertEquals("wrong number of races for today", 1, Race.findAllByDate(new LocalDate()).size())
        assertEquals("wrong number of races for yesterday", 2, Race.findAllByDate(new LocalDate().minusDays(1)).size())
        def races = Race.createCriteria().list {
            projections {
                groupProperty "date"
            }
        }
        assertEquals("wrong number of dates", 2, races.size())
    }
}
