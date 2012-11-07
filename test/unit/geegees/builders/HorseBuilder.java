package geegees.builders;

import geegees.model.Horse;

public class HorseBuilder {

    String name;
    private String odds;
    private Integer tips = 0;
    private double decimalOdds;
    private double magicNumber;

    private HorseBuilder() {}

    public static HorseBuilder horseBuilder() {
        return new HorseBuilder();
    }

    public Horse build() {
        Horse horse = new Horse(name, odds);
        horse.setTips(tips);
        horse.setDecimalOdds(decimalOdds);
        horse.setMagicNumber(magicNumber);
        return horse;
    }

    public HorseBuilder decimalOdds(double decimalOdds) {
        this.decimalOdds = decimalOdds;
        return this;
    }

    public HorseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public HorseBuilder odds(String odds) {
        this.odds = odds;
        return this;
    }

    public HorseBuilder tips(Integer tips) {
        this.tips = tips;
        return this;
    }

    public HorseBuilder magicNumber(double magicNumber) {
        this.magicNumber = magicNumber;
        return this;
    }
}
