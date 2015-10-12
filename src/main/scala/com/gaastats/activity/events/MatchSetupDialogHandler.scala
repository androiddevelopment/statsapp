package com.gaastats.activity.events

import android.widget.Spinner
import com.gaastats.activity.service.ActivityStarterService
import com.gaastats.dao.{CompetitionDao, MatchDao, TeamDao}
import com.gaastats.domain.Match

object MatchSetupDialogHandler {

    def retrieveValuesAndSave(homeTeamSpinner: Spinner, awayTeamSpinner: Spinner, competitionSpinner: Spinner) {
        val competitionName = competitionSpinner.getSelectedItem().asInstanceOf[String]
        val homeTeamName = homeTeamSpinner.getSelectedItem().asInstanceOf[String]
        val awayTeamName = awayTeamSpinner.getSelectedItem().asInstanceOf[String]
        val competition = CompetitionDao.retrieveByName(competitionName)
        val homeTeam = TeamDao.retrieveByName(homeTeamName)
        val awayTeam = TeamDao.retrieveByName(awayTeamName)
        val matchCreated = Match(competition, homeTeam, awayTeam)

        save(matchCreated)
    }

    def save(matchCreated: Match) = {
        MatchDao.save(matchCreated)
        ActivityStarterService.startMatchCentreActivity(matchCreated)
    }
}