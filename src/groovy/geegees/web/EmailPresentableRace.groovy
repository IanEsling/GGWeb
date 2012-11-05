package geegees.web

import geegees.model.Race
import geegees.model.Horse


class EmailPresentableRace {
    Race race
    double favouriteOdds

    EmailPresentableRace(Race race) {
        this.race = race
        this.favouriteOdds = race.horses.sort {h1, h2 ->
            h1.decimalOdds > h2.decimalOdds ? 1 :
            h1.decimalOdds < h2.decimalOdds ? -1 : 0
        }.get(0).decimalOdds
    }

    public Set<Horse> getHorses(){
        race.horses.findAll {
            it.decimalOdds == favouriteOdds
        }
    }
}
