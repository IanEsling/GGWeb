package geegees.service

import geegees.model.Horse
import geegees.model.Race
import geegees.model.RaceDay
import org.joda.time.LocalDate
import org.jsoup.nodes.Document
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static com.google.common.collect.Lists.newArrayList

public class RacingPostRaceService {

    Logger logger = LoggerFactory.getLogger(RacingPostRaceService.class);
    RacingPostDocumentService racingPostDocumentService
    RaceBetAnalysisDecoratorService raceBetAnalysisDecoratorService

    private Collection<Race> getRaces(RaceHandler handler) {
        List<Race> races = newArrayList();
        Collection<String> raceUrls = racingPostDocumentService.getRaceUrls();
        int i = 1;
        for (String url : raceUrls) {
            logger.info(i + " of " + raceUrls.size());
            i++;
            Document racePage = racingPostDocumentService.getRacePage(url);
            Race race = racingPostDocumentService.getRace(racePage);
            racingPostDocumentService.getTipsDecorator(racePage,
                    racingPostDocumentService.getBettingForecast(racePage).getHorses())
                    .getHorses().each {Horse horse ->
                race.addToHorses(horse)
            }
            raceBetAnalysisDecoratorService.decorateRace(race)
            handler.handleRace(race)
        }
        Collections.sort(races);
        return races;
    }

    public void saveRaces() {
        RaceDay raceDay = RaceDay.findByRaceDate(new LocalDate())
        if (raceDay == null) {
            logger.info("it's a new race day!")
            raceDay = new RaceDay(raceDate: new LocalDate())
        } else {
            logger.info("using existing race day.")
        }

        getRaces(new RaceHandler() {
            @Override
            void handleRace(Race race) {
                if (!raceDay.races?.find {
                    it.venue == race.venue &&
                            it.time == race.time
                }) {
                    logger.info("adding $race to race day...")
                    raceDay.addToRaces(race)
                } else {
                    Race raceDayRace = raceDay.races.find {
                        it.venue == race.venue &&
                                it.time == race.time
                    }
                    if (raceDayRace.horses?.isEmpty() && !race.horses?.isEmpty()) {
                        logger.info("adding horses from $race to existing race.")
                        raceDayRace.horses = race.horses
                    } else {
                        logger.info("no horses found in this race either.")
                    }
                }
            }
        })
        raceDay.save(flush: true)
    }

    public Collection<Race> getRaces() {
        final Collection<Race> races = newArrayList()
        getRaces(new RaceHandler() {
            @Override
            void handleRace(Race race) {
                races.add(race)
            }
        })

        return races;
    }

    public interface RaceHandler {
        void handleRace(Race race);
    }
}
