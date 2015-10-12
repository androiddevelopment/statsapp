package com.gaastats.activity

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.gaastats.R
import com.gaastats.activity.events.MatchButtonOnClickListener
import com.gaastats.activity.service.{MatchStatsService, MatchStatsViewsService, MatchTimerService}
import com.gaastats.dao.MatchDao
import com.gaastats.dao.helper.StatisticHelper
import com.gaastats.domain.Match
import com.gaastats.domain.enums.TeamType
import com.gaastats.ui.helper.DialogFragmentHelper
import main.scala.com.gaastats.activity.service.MatchViewsService
import org.scaloid.common.SActivity

object MatchCentreActivity {
    def GoalDialog = "goalDialog"
    def PointDialog = "pointDialog"
    def StatisticLauncherDialog = "statisticLauncherDialog"
    def matchStatisticViews = List(R.id.goalButton, R.id.pointButton, R.id.missButton, R.id.cardButton, R.id.kickoutButton)
}

class MatchCentreActivity extends SActivity {
    var matchID: Int = 0
    var matchInProgress: Match = null


    override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        DialogFragmentHelper.fragmentManager = getFragmentManager
        setContentView(R.layout.match_centre)
        for (buttonID <- Array(R.id.pauseOrResumeTimer, R.id.halfOrFullTime, R.id.undoLast)) findViewById(buttonID).setOnClickListener(MatchButtonOnClickListener)
        setOnClickListenersForStatisticButtons(MatchCentreActivity.matchStatisticViews)
        matchID = getIntent().getSerializableExtra("matchID").asInstanceOf[Int]

    }

    override def onStart {
        super.onStart
        if (matchInProgress == null) {
            matchInProgress = MatchDao.retrieveMatchByID(matchID)
            MatchStatsService.matchInProgress = matchInProgress
            MatchTimerService.matchInProgress = matchInProgress
            MatchViewsService.matchInProgress = matchInProgress
        }
        setTitleTextViews
        StatisticHelper.saveStatistics
        MatchStatsViewsService.activity = this
        MatchViewsService.activity = this
    }

    override def onResume {
        super.onResume
        MatchViewsService.initializeViews
        TeamType forAllTeamTypes MatchStatsViewsService.refreshTeamStatisticViews
    }

    override def onDestroy {
      super.onDestroy
      MatchTimerService.pauseMatch
      MatchDao.save(matchInProgress)
    }

    private def setTitleTextViews {
        val matchCentreTitle = matchInProgress.competition.name + ": " + matchInProgress.homeTeam.name + " v " + matchInProgress.awayTeam.name
        find[TextView](R.id.matchCentreTitle).setText(matchCentreTitle)
        TeamType.forAllTeamTypes(setTextForTeam)
    }

    private def setOnClickListenersForStatisticButtons(buttonIDsList: List[Int]) {
        buttonIDsList.foreach(buttonID => findViewById(R.id.statisticsButtons).findViewById(buttonID).setOnClickListener(MatchButtonOnClickListener))
    }

    private def setTextForTeam(teamType: TeamType) {
        val teamNameView = findViewById(teamType.layoutID).findViewById(R.id.teamName).asInstanceOf[TextView]
        teamNameView.setText(teamType.getTeam(matchInProgress).name)
    }

}