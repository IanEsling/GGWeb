package geegees.service

import geegees.model.Horse
import geegees.model.Race

import org.jsoup.nodes.Document
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static com.google.common.collect.Lists.newArrayList

public class RacingPostRaceService {

    Logger logger = LoggerFactory.getLogger(RacingPostRaceService.class);
    RacingPostDocumentService racingPostDocumentService
    RaceBetAnalysisDecoratorService raceBetAnalysisDecoratorService

    public Collection<Race> getRaces() {
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
            races.add(race);
        }
        Collections.sort(races);
        return races;
    }

}
