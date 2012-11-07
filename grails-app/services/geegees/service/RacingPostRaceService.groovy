package geegees.service

import geegees.model.Horse
import geegees.model.Race
import geegees.model.RaceDay
import org.joda.time.LocalDate
import org.jsoup.nodes.Document

import static com.google.common.collect.Lists.newArrayList
import grails.plugin.mail.MailService
import geegees.web.EmailPresentableRaceDay

public class RacingPostRaceService {

    MailService mailService
    RacingPostDocumentService racingPostDocumentService
    RaceBetAnalysisDecoratorService raceBetAnalysisDecoratorService

    private Collection<Race> getRaces(RaceHandler handler) {
        List<Race> races = newArrayList();
        Collection<String> raceUrls = racingPostDocumentService.getRaceUrls();
        int i = 1;
        for (String url : raceUrls) {
            log.info(i + " of " + raceUrls.size());
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
        boolean sendMail = false
        RaceDay raceDay = RaceDay.findByRaceDate(new LocalDate())
        if (raceDay == null) {
            log.info("it's a new race day!")
            raceDay = new RaceDay(raceDate: new LocalDate())
        } else {
            log.info("using existing race day.")
        }

        getRaces(new RaceHandler() {
            @Override
            void handleRace(Race race) {
                if (!raceDay.races?.find {
                    it.venue == race.venue &&
                            it.time == race.time
                }) {
                    log.info("adding $race to race day...")
                    raceDay.addToRaces(race)
                    sendMail = true
                } else {
                    Race raceDayRace = raceDay.races.find {
                        it.venue == race.venue &&
                                it.time == race.time
                    }
                    if (raceDayRace.horses?.isEmpty() && race.horses != null && !race.horses?.isEmpty()) {
                        log.info("adding horses from $race to existing race.")
                        raceDayRace.horses = race.horses
                        sendMail = true
                    }
                }
            }
        })
        raceDay.save(flush: true)
        if (sendMail) {
            mailService.sendMail {
                to "ian.esling@gmail.com", "aliciales@esling.me.uk"
                from "GeeGees@GeeGees.com"
                subject "Email From GeeGees!"
                body (view: "/email/raceDay", model:[raceDay: new EmailPresentableRaceDay(raceDay)])
            }
        }
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
