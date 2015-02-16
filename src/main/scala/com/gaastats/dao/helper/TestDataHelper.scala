package com.gaastats.dao.helper

import com.gaastats.domain.Competition
import com.gaastats.domain.Match
import com.gaastats.domain.Team
import com.google.inject.Singleton
import com.google.inject.Inject
import com.gaastats.dao.TeamDao
import com.gaastats.dao.MatchDao
import com.gaastats.dao.CompetitionDao

@Singleton
class TestDataHelper {
    @Inject
    var teamDao: TeamDao = null
    @Inject
    var matchDao: MatchDao = null
    @Inject
    var competitionDao: CompetitionDao = null

    def saveTestData {
        val teamCork = Team("Cork")
        teamDao.save(teamCork)
        val teamTones = Team("Wolfe Tones")
        teamDao.save(teamTones)
        val competition = Competition("O Brien Cup", 13, 5, 20)
        competitionDao.save(competition)
    }
}