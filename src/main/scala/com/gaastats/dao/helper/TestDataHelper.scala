package com.gaastats.dao.helper

import com.gaastats.dao.{CompetitionDao, TeamDao}
import com.gaastats.domain.{Competition, Team}


object TestDataHelper {

    def saveTestData {
        val teamCork = Team("Cork")
        TeamDao.save(teamCork)
        val teamTones = Team("Wolfe Tones")
        TeamDao.save(teamTones)
        val competition = Competition("O Brien Cup", 13, 5, 20)
        CompetitionDao.save(competition)
    }
}