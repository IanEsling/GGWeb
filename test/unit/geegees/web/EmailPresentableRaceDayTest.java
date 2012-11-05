package geegees.web;

import geegees.model.Horse;
import geegees.model.RaceDay;
import org.joda.time.LocalDate;
import org.junit.Test;

import static com.google.common.collect.Sets.newHashSet;
import static geegees.builders.HorseBuilder.horseBuilder;
import static geegees.builders.RaceBuilder.raceBuilder;
import static geegees.builders.RaceDayBuilder.raceDayBuilder;
import static org.junit.Assert.assertEquals;

public class EmailPresentableRaceDayTest {


    @Test
    public void shouldOnlyShowBettableRaces() {
        RaceDay raceDay = raceDayBuilder()
                .race(raceBuilder().bettable(true).qbuild())
                .race(raceBuilder().bettable(false).build())
                .raceDate(new LocalDate())
                .build();
        EmailPresentableRaceDay presentable = new EmailPresentableRaceDay(raceDay);
        assertEquals("wrong number of bettable races", 1, presentable.getBettableRaces().size());
    }

    @Test
    public void shouldOnlyShowFavourites() {
        RaceDay raceDay = raceDayBuilder()
                .race(raceBuilder()
                        .venue("Venue 1")
                        .bettable(true)
                        .horse(horseBuilder().decimalOdds(2.5).build())
                        .horse(horseBuilder().decimalOdds(1.5).build())
                        .horse(horseBuilder().decimalOdds(3.0).build())
                        .build())
                .build();
        EmailPresentableRaceDay presentable = new EmailPresentableRaceDay(raceDay);
        assertEquals("should only show favourite", 1, presentable.getBettableRaces().iterator().next().getHorses().size());
    }
}
