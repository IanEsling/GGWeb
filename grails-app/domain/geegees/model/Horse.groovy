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
    }

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
