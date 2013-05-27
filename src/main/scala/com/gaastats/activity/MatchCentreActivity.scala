package com.gaastats.activity

import roboguice.activity.RoboFragmentActivity
import android.os.Bundle
import com.gaastats.R
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.gaastats.domain.enums.TeamType
import android.widget.TextView
import com.gaastats.dao.helper.StatisticHelper
import com.gaastats.activity.service.MatchStatsService
import com.gaastats.activity.events.MatchButtonOnClickListener
import com.gaastats.domain.Match
import com.gaastats.domain.enums.MatchStageEnum
import com.gaastats.activity.service.MatchTimerService
import com.gaastats.dao.MatchDao
import com.gaastats.dao.helper.DatabaseHelper
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import android.util.Log

object MatchCentreActivity {
    def GoalDialog = "goalDialog"
    def PointDialog = "pointDialog"
}

class MatchCentreActivity extends RoboFragmentActivity {
    @Inject
    var matchDao: MatchDao = null
    @Inject
    var resourceHelper: ResourceHelper = null
    @Inject
    var matchStatsService: MatchStatsService = null
    @Inject
    var matchTimerService: MatchTimerService = null
    @Inject
    var matchButtonOnClickListener: MatchButtonOnClickListener = null
    @Inject
    var databaseUtilsWrapper: DatabaseUtilsWrapper = null
    @Inject
    var statisticHelper: StatisticHelper = null
    var matchID: Int = 0
    var matchInProgress: Match = null

    override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_centre)
        for (buttonID <- Array(R.id.pauseOrResumeTimer, R.id.halfOrFullTime, R.id.undoLast)) findViewById(buttonID).setOnClickListener(matchButtonOnClickListener)
        setOnClickListenersForStatisticButtons(List(R.id.goalButton, R.id.pointButton))
        matchID = getIntent().getSerializableExtra("matchID").asInstanceOf[Int]

    }

    override def onStart {
        super.onStart
        matchInProgress = matchDao.retrieveMatchByID(matchID)
        matchStatsService.setMatchInProgress(matchInProgress)
        matchTimerService.setMatchInProgress(matchInProgress)
        resourceHelper.setActivity(this)
        matchTimerService.initializeViews
        setTitleTextViews
        statisticHelper.saveStatistics        
    }
    
    override def onResume {
        super.onResume
        for (teamType <- TeamType.allTypes) refreshTeamStatisticViews(teamType)
    }
    
    override def onPause {
        super.onPause
        matchDao.save(matchInProgress)
    }
    
    def refreshTeamStatisticViews(teamType: TeamType) {
        updateStatisticTextView(teamType, "Goal")
        updateStatisticTextView(teamType, "Point")
    }

    def updateStatisticTextView(teamType: TeamType, statisticName: String) {
        var teamScoreLayout = teamType.layoutID
        val teamScoreLayoutView = findViewById(teamScoreLayout)
        val statisticViewID = StatisticHelper.getStatisticView(statisticName)
        val statisticTextView = teamScoreLayoutView.findViewById(statisticViewID).asInstanceOf[TextView]
        statisticTextView.setText(matchStatsService.retrieveStatisticSum(statisticName, teamType).toString)
    }

    private def setTitleTextViews {
        val matchCentreTitle = matchInProgress.competition.name + ": " + matchInProgress.homeTeam.name + " v " + matchInProgress.awayTeam.name
        findViewById(R.id.matchCentreTitle).asInstanceOf[TextView].setText(matchCentreTitle)
        for (teamType <- TeamType.allTypes) {
            var teamNameView = findViewById(teamType.layoutID).findViewById(R.id.teamName).asInstanceOf[TextView]
            teamNameView.setText(teamType.getTeam(matchInProgress).name)
        }
    }
    
    private def setOnClickListenersForStatisticButtons(buttonIDsList: List[Int]) {
        for(buttonID <- buttonIDsList) {
            for (teamType <- TeamType.allTypes) {
                findViewById(teamType.layoutID).findViewById(buttonID).setOnClickListener(matchButtonOnClickListener)
            }
        }
    }

}