package geegees.service



import grails.test.mixin.*
import org.junit.Test
import geegees.model.Race
import geegees.model.Horse

import static geegees.builders.RaceBuilder.raceBuilder
import static geegees.builders.HorseBuilder.horseBuilder

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RaceBetAnalysisDecoratorService)
class RaceBetAnalysisDecoratorServiceTests {

    RaceBetAnalysisDecoratorService raceBetAnalysisDecoratorService = new RaceBetAnalysisDecoratorService();

    @Test
    public void shouldCalculateEvens() {
        Race race = raceBuilder()
                .venue("venue")
                .time("time")
                .numberOfRunners(10)
                .horse(horseBuilder().name("not-fav").odds("4/1").build())
                .horse(horseBuilder().name("fav").odds("Evs").tips(5).build())
                .horse(horseBuilder().name("not-fav-either").odds("5/1").build())
                .build();

        //when
        raceBetAnalysisDecoratorService.decorateRace(race);

        //then
        for (Horse horse : race.getHorses()) {
            if (horse.getName().equals("fav")) {
                assertEquals("wrong value for favourite", -2d, horse.getMagicNumber(), 0d);
            }
        }
    }

    @Test
    public void shouldCalculateValueForOneFavourite() {
        Race race = raceBuilder()
                .venue("venue")
                .time("time")
                .numberOfRunners(10)
                .horse(horseBuilder().name("not-fav").odds("4/1").build())
                .horse(horseBuilder().name("fav").odds("2/1").tips(5).build())
                .horse(horseBuilder().name("not-fav-either").odds("5/1").build())
                .build();

        //when
        raceBetAnalysisDecoratorService.decorateRace(race);

        //then
        for (Horse horse : race.getHorses()) {
            if (horse.getName().equals("fav")) {
                assertEquals("wrong value for favourite", -3d, horse.getMagicNumber(), 0d);
            }
        }
    }

    @Test
    public void shouldCalculateDifferenceInOddsPlusTipsForEachFavourite() {
        Race race = raceBuilder()
                .venue("venue")
                .time("time")
                .numberOfRunners(5)
                .horse(horseBuilder().name("not-fav").odds("4/1").build())
                .horse(horseBuilder().name("fav1").odds("2/1").tips(3).build())
                .horse(horseBuilder().name("fav2").odds("2/1").tips(4).build())
                .horse(horseBuilder().name("fav3").odds("2/1").tips(5).build())
                .horse(horseBuilder().name("not-fav-either").odds("5/1").build())
                .build();

        //when
        raceBetAnalysisDecoratorService.decorateRace(race);

        //then
        for (Horse horse : race.getHorses()) {
            if (horse.getName().equals("fav1")) {
                assertEquals("wrong value for favourite 1", -2d, horse.getMagicNumber(), 0d);
            }
            if (horse.getName().equals("fav2")) {
                assertEquals("wrong value for favourite 2", -1d, horse.getMagicNumber(), 0d);
            }
            if (horse.getName().equals("fav3")) {
                assertEquals("wrong value for favourite 3", 0d, horse.getMagicNumber(), 0d);
            }
        }
    }

    @Test
    public void shouldBeBettableIfFavouriteLessThan2To1() {
        //given
        Race race = raceBuilder()
                .venue("venue")
                .time("time")
                .numberOfRunners(5)
                .horse(horseBuilder().name("not-fav").odds("15/7").build())
                .horse(horseBuilder().name("fav").odds("8/7").build())
                .horse(horseBuilder().name("not-fav-either").odds("31/7").build())
                .build();

        //when
        raceBetAnalysisDecoratorService.decorateRace(race);

        //then
        assertTrue("race should be bettable", race.getBettable());
    }

    @Test
    public void shouldBeBettableIfFavourite2To1() {
        //given
        Race race = raceBuilder()
                .venue("venue")
                .time("time")
                .numberOfRunners(5)
                .horse(horseBuilder().name("not-fav").odds("9/1").build())
                .horse(horseBuilder().name("another-not-fav").odds("12/1").build())
                .horse(horseBuilder().name("fav").odds("2/1").build())
                .build();

        //when
        raceBetAnalysisDecoratorService.decorateRace(race);

        //then
        assertTrue("race should be bettable", race.getBettable());
    }

    @Test
    public void shouldNotBeBettableIfFavouriteMoreThan2To1() {
        //given
        Race race = raceBuilder()
                .venue("venue")
                .time("time")
                .numberOfRunners(5)
                .horse(horseBuilder().name("fav").odds("3/1").build())
                .horse(horseBuilder().name("not-fav").odds("5/2").build())
                .horse(horseBuilder().name("me-not-fav").odds("7/3").build())
                .build();

        //when
        raceBetAnalysisDecoratorService.decorateRace(race);

        //then
        assertFalse("race should not be bettable", race.getBettable());
    }
}
