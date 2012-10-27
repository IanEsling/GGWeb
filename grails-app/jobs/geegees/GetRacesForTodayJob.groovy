package geegees

import geegees.service.RacingPostRaceService

class GetRacesForTodayJob {

    RacingPostRaceService racingPostRaceService

    static triggers = {
        cron name: 'getRaces', cronExpression: "0 5 9 * * ?"
//        simple name: 'getRaces', startDelay: 60000, repeatInterval: 600000
    }

    def execute() {
        racingPostRaceService.saveRaces()
    }
}
