package geegees.model

class Horse {

    String name
    String odds
    Integer tips = 0
    double magicNumber
    double decimalOdds

    static belongsTo = [race: Race]

    static constraints = {
    }

    @Override
    String toString() {
        return name
    }
}
