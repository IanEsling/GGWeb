package geegees.model

public class Horse {

    String name
    String odds
    Integer tips = 0
    double magicNumber
    double decimalOdds

    static belongsTo = [race: Race]

    static constraints = {
    }

    public Horse(){}

    public Horse(String name, String odds) {
        this.name = name
        this.odds = odds
    }

    @Override
    String toString() {
        return name
    }
}
