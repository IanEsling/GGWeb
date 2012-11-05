package geegees.builders;

import geegees.model.Race;
import geegees.model.RaceDay;
import org.joda.time.LocalDate;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class RaceDayBuilder {

    Set<Race> races = newHashSet();
    LocalDate raceDate;

    private RaceDayBuilder() {}

    public static RaceDayBuilder raceDayBuilder() {
        return new RaceDayBuilder();
    }

    public RaceDay build() {
        RaceDay raceDay = new RaceDay();
        raceDay.setRaceDate(raceDate);
        raceDay.setRaces(races);
        return raceDay;
    }

    public RaceDayBuilder race(Race race) {
        races.add(race);
        return this;
    }

    public RaceDayBuilder raceDate(LocalDate date) {
        this.raceDate = date;
        return this;
    }

}
