package geegees.web;

import geegees.model.Horse;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static geegees.builders.HorseBuilder.horseBuilder;
import static org.junit.Assert.assertTrue;

public class EmailPresentableHorseTest {

    @Test
    public void shouldScaleMagicNumber(){
        Horse horse = horseBuilder().magicNumber(1.23456789).build();
        EmailPresentableHorse eHorse = new EmailPresentableHorse(horse);
        assertTrue("magic number not scaled properly: " + eHorse.getMagicNumber(),
                new BigDecimal(1.235).setScale(3, RoundingMode.HALF_UP).compareTo(eHorse.getMagicNumber()) == 0);
    }
}
