package geegees.model

class Race {

    String venue
    String time
    Integer numberOfRunners
    Collection<Horse> horses
    boolean bettable = false

    static hasMany = [horses:Horse]

    static constraints = {
    }

    @Override
    String toString() {
        return "$time at $venue"
    }
}
