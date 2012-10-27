package geegees.model

import org.jadira.usertype.dateandtime.joda.PersistentLocalDate
import org.joda.time.LocalDate

class RaceDay {

    LocalDate raceDate

    static hasMany = [races: Race]

    static mapping = {
        raceDate type: PersistentLocalDate
    }

    static constraints = {
        raceDate unique: true
    }
}
