package geegees.model

public class Horse {

    String name
    String odds
    Integer tips = 0
    double magicNumber
    double decimalOdds
    int finishingPosition

    static belongsTo = [race: Race]

    static constraints = {
    }

    public Horse(){}

    public Horse(String name, String odds) {
        this.name = name
        this.odds = odds
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horse horse = (Horse) o;

        if (name != null ? !name.equals(horse.name) : horse.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    String toString() {
        return name
    }
}
