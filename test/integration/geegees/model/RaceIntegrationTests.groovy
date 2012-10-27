package geegees.model

import static org.junit.Assert.*
import org.junit.*
import org.joda.time.LocalDate

class RaceIntegrationTests {

    @Test
    void getByDate() {
        RaceDay raceToday = new RaceDay(raceDate: new LocalDate())
        RaceDay raceYesterday = new RaceDay(raceDate: new LocalDate().minusDays(1))
        Race yesterday = new Race(numberOfRunners: 2, time: "12:00", venue: "yesterday")
        yesterday.addToHorses(new Horse(name: "yesterday fav", odds: "1/1"))
        yesterday.addToHorses(new Horse(name: "yesterday not fav", odds: "2/1"))
        Race today = new Race(numberOfRunners: 1, time: "12:00", venue: "today")
        today.addToHorses(new Horse(name: "today fav", odds: "1/1"))
        today.addToHorses(new Horse(name: "today not fav 1", odds: "2/1"))
        today.addToHorses(new Horse(name: "today not fav 2", odds: "3/1"))
        raceToday.addToRaces(today)
        raceYesterday.addToRaces(yesterday)
        raceToday.save(flush: true)
        raceYesterday.save(flush: true)
        assertEquals("wrong number of races for today", 1, RaceDay.findByRaceDate(new LocalDate()).races.size())
        assertEquals("wrong race today", "today", Race.findByRaceDay(raceToday).venue)
        assertEquals("wrong number of horses for race today", 3, Race.findByRaceDay(raceToday).horses.size())

        assertEquals("wrong number of races for yesterday", 1, RaceDay.findByRaceDate(new LocalDate().minusDays(1)).races.size())
        assertEquals("wrong race yesterday", "yesterday", Race.findByRaceDay(raceYesterday).venue)
        assertEquals("wrong number of horses for race yesterday", 2, Race.findByRaceDay(raceYesterday).horses.size())
    }
}
