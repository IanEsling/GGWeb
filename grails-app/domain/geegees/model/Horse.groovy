package geegees.model

public class Horse implements Serializable{

    String name
    String odds
    Integer tips = 0
    double magicNumber
    double decimalOdds
    int finishingPosition

    static belongsTo = [race: Race]

    static mapping = {
        id composite: ['race', 'name']
    }

    static constraints = {
    }

    public Horse(){}

    public Horse(String name, String odds) {
        this.name = name
        this.odds = odds
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Horse horse = (Horse) o

        if (name != horse.name) return false
        if (race != horse.race) return false

        return true
    }

    int hashCode() {
        int result
        result = name.hashCode()
        result = 31 * result + (race != null ? race.hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return name
    }
}
