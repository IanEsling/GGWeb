package geegees.web

import geegees.model.Horse


class EmailPresentableHorse {

    Horse horse

    EmailPresentableHorse(Horse horse) {
        this.horse = horse
    }

    String getName() {
        return horse.name
    }

    String getOdds() {
        return horse.odds
    }

    BigDecimal getMagicNumber() {
        return new BigDecimal(horse.magicNumber).setScale(3)
    }
}
