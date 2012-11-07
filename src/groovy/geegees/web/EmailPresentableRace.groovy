package geegees.web

import geegees.model.Horse
import geegees.model.Race

class EmailPresentableRace implements Comparable<EmailPresentableRace> {
    Race race
    double favouriteOdds
    double lowestMagicNumber

    EmailPresentableRace(Race race) {
        this.race = race
        this.favouriteOdds = race.horses.sort {h1, h2 ->
            h1.decimalOdds > h2.decimalOdds ? 1 :
                h1.decimalOdds < h2.decimalOdds ? -1 : 0
        }.get(0).decimalOdds
        println "getting lowest magic number for race $race.horses"
        this.lowestMagicNumber = race.horses.findAll {
            it.magicNumber != 0
        }.sort {h1, h2 ->
            h1.magicNumber > h2.magicNumber ? -1 :
                h1.magicNumber < h2.magicNumber ? 1 : 0
        }.get(0).magicNumber

        println "lowest magic number $lowestMagicNumber"
    }

    public Set<EmailPresentableHorse> getHorses() {
        race.horses.findAll {
            it.decimalOdds == favouriteOdds
        }.collect {
            new EmailPresentableHorse(it)
        }
    }

    String getVenue() {
        return race.venue
    }

    String getTime() {
        return race.getTime()
    }

    Integer getNumberOfRunners() {
        return race.numberOfRunners
    }

    @Override
    int compareTo(EmailPresentableRace race) {
        return this.lowestMagicNumber.compareTo(race.lowestMagicNumber)
    }
}
