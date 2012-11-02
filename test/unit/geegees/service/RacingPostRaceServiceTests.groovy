package geegees.service

import com.google.common.base.Function
import geegees.model.Horse
import geegees.model.Race
import geegees.model.RaceDay
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.mock.interceptor.StubFor
import org.joda.time.LocalDate
import org.jsoup.nodes.Document
import org.junit.Before
import org.junit.Test

import static com.google.common.collect.Lists.newArrayList
import static com.google.common.collect.Lists.transform
import static geegees.builders.BettingForecastBuilder.bettingForecastBuilder
import static geegees.builders.HorseBuilder.horseBuilder
import static geegees.builders.RaceBuilder.raceBuilder
import static geegees.builders.TipsDecoratorBuilder.tipsDecoratorBuilder

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RacingPostRaceService)
@Mock([RaceDay, Race, RaceBetAnalysisDecoratorService])
class RacingPostRaceServiceTests {

    def racingPostDocumentService
    Document doc1
    Document doc2

    @Before
    public void setUpDocService() {
        racingPostDocumentService = new StubFor(RacingPostDocumentService)
        doc1 = new Document("url1")
        doc2 = new Document("url2")
        racingPostDocumentService.demand.getRaceUrls(10) {
            newArrayList("url1", "url2")
        }

        racingPostDocumentService.demand.getRacePage(10) {url ->
            if (url.equals("url1")) return doc1
            if (url.equals("url2")) return doc2
        };

        racingPostDocumentService.demand.getRace(10) {doc ->
            if (doc == doc1)
                return raceBuilder().venue("Venue 1").time("time 1").numberOfRunners(3).build()
            if (doc == doc2)
                return raceBuilder().venue("Venue 2").time("time 2").numberOfRunners(2).build()
        }
    }

    @Test
    public void updateRacesWithHorses() {
        List<Horse> horses1 = newArrayList(horseBuilder().name("1").odds("1/1").build(), horseBuilder().name("4").odds("4/4").build(), horseBuilder().name("5").odds("5/5").build());
        List<Horse> horses2 = newArrayList(horseBuilder().name("2").odds("2/2").build(), horseBuilder().name("3").odds("3/3").build());
        Collection<Horse> tips1 = addTips(horses1);
        Collection<Horse> tips2 = addTips(horses2);

        racingPostDocumentService.demand.getBettingForecast(2) {doc ->
            bettingForecastBuilder().build()
        }

        racingPostDocumentService.demand.getTipsDecorator(2) {doc, horses ->
            tipsDecoratorBuilder().build()
        }
        racingPostDocumentService.demand.getBettingForecast(10) {doc ->
            if (doc == doc1)
                return bettingForecastBuilder().horses(horses1).build()
            if (doc == doc2)
                return bettingForecastBuilder().horses(horses2).build()
        }

        racingPostDocumentService.demand.getTipsDecorator(10) {doc, horses ->
            if (doc == doc1 && horses == horses1)
                return tipsDecoratorBuilder().horses(tips1).build()
            if (doc == doc2 && horses == horses2)
                return tipsDecoratorBuilder().horses(tips2).build()
        }
        service.racingPostDocumentService = racingPostDocumentService.proxyInstance()
        service.saveRaces()
        RaceDay raceDay = RaceDay.findByRaceDate(new LocalDate())
        assertEquals("no races", 2, raceDay.races?.size());
        Race race1 = getRaceByVenue(raceDay.races, "Venue 1");
        Race race2 = getRaceByVenue(raceDay.races, "Venue 2");
        assertEquals("wrong time for race1", "time 1", race1.getTime());
        assertEquals("wrong number of runners for race 1", 3, race1.getNumberOfRunners().intValue());
        assertEquals("wrong number of horses for race 1", 0, race1.getHorses().size());

        assertEquals("wrong time for race2", "time 2", race2.getTime());
        assertEquals("wrong number of runners for race 2", 2, race2.getNumberOfRunners().intValue());
        assertEquals("wrong number of horses for race 2", 0, race2.getHorses().size());

        service.saveRaces()
        assertEquals("wrong number of race days", 1, RaceDay.list().size())
        raceDay = RaceDay.findByRaceDate(new LocalDate())
        assertEquals("no races", 2, raceDay.races?.size());
        race1 = getRaceByVenue(raceDay.races, "Venue 1");
        race2 = getRaceByVenue(raceDay.races, "Venue 2");
        assertEquals("wrong time for race1", "time 1", race1.getTime());
        assertEquals("wrong number of runners for race 1", 3, race1.getNumberOfRunners().intValue());
        assertEquals("wrong number of horses for race 1", 3, race1.getHorses().size());

        assertEquals("wrong time for race2", "time 2", race2.getTime());
        assertEquals("wrong number of runners for race 2", 2, race2.getNumberOfRunners().intValue());
        assertEquals("wrong number of horses for race 2", 2, race2.getHorses().size());

    }

    @Test
    public void getBrandNewRaces() {
        List<Horse> horses1 = newArrayList(horseBuilder().name("1").odds("1/1").build(), horseBuilder().name("4").odds("4/4").build(), horseBuilder().name("5").odds("5/5").build());
        List<Horse> horses2 = newArrayList(horseBuilder().name("2").odds("2/2").build(), horseBuilder().name("3").odds("3/3").build());
        Collection<Horse> tips1 = addTips(horses1);
        Collection<Horse> tips2 = addTips(horses2);

        racingPostDocumentService.demand.getBettingForecast(10) {doc ->
            if (doc == doc1)
                return bettingForecastBuilder().horses(horses1).build()
            if (doc == doc2)
                return bettingForecastBuilder().horses(horses2).build()
        }

        racingPostDocumentService.demand.getTipsDecorator(10) {doc, horses ->
            if (doc == doc1 && horses == horses1)
                return tipsDecoratorBuilder().horses(tips1).build()
            if (doc == doc2 && horses == horses2)
                return tipsDecoratorBuilder().horses(tips2).build()
        }

        service.racingPostDocumentService = racingPostDocumentService.proxyInstance()
        service.saveRaces()
        RaceDay raceDay = RaceDay.findByRaceDate(new LocalDate())
        assertEquals("no races", 2, raceDay.races.size());
        Race race1 = getRaceByVenue(raceDay.races, "Venue 1");
        Race race2 = getRaceByVenue(raceDay.races, "Venue 2");
        assertEquals("wrong time for race1", "time 1", race1.getTime());
        assertEquals("wrong number of runners for race 1", 3, race1.getNumberOfRunners().intValue());
        assertEquals("wrong number of horses for race 1", 3, race1.getHorses().size());

        assertEquals("wrong time for race2", "time 2", race2.getTime());
        assertEquals("wrong number of runners for race 2", 2, race2.getNumberOfRunners().intValue());
        assertEquals("wrong number of horses for race 2", 2, race2.getHorses().size());
    }

    private Race getRaceByVenue(Collection<Race> races, String venue) {
        for (Race race : races) {
            if (race.getVenue().equals(venue)) {
                return race;
            }
        }
        fail("couldn't find race for venue " + venue + " in races " + races);
        return null;
    }

    private Collection<Horse> addTips(List<Horse> horses) {
        return transform(horses, new Function<Horse, Horse>() {
            @Override
            public Horse apply(Horse horse) {
                horse.setTips(Integer.valueOf(horse.getName()));
                return horse;
            }
        });
    }
}
