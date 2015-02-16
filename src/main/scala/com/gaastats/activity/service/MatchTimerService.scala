package com.gaastats.activity.service

import com.gaastats.R
import com.gaastats.domain.Match
import com.gaastats.domain.enums.MatchStageEnum
import com.gaastats.domain.enums.TimerStatus
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.google.inject.Singleton
import com.j256.ormlite.table.DatabaseTable
import android.view.View
import android.widget.TextView
import java.text.DecimalFormat
import com.gaastats.dao.CompetitionDao
import com.gaastats.util.Format
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.domain.enums.TeamType

@Singleton
class MatchTimerService {
    var matchInProgress: Match = null
    var matchTimer: MatchTimer = null
    @Inject
    var resourceHelper: ResourceHelper = null
    @Inject
    var competitionDao: CompetitionDao = null

    def setMatchInProgress(matchInProgress: Match) {
        this.matchInProgress = matchInProgress
    }

    def initializeViews {
        updateTimerViewWithMatchTime
        updateHalfOrFullTimeTextView
        updateViewsVisibility
        updateCurrentHalfTextView
    }

    def updateMatchStage {
        matchInProgress.stage match {
            case MatchStageEnum.MatchNotStarted | MatchStageEnum.HalfTime => startHalf
            case MatchStageEnum.FirstHalfInProgress | MatchStageEnum.SecondHalfInProgress => endHalf
        }
    }

    def pauseResumeMatch {
        if (matchTimer != null) pauseMatch
        else resumeMatch
    }

    protected[service] def endHalf {
        updateMatchStageEnum
        updateHalfOrFullTimeTextView
        updateCurrentHalfTextView
        pauseMatch
        updateTimeOnEndOfHalf
    }

    protected[service] def updateMatchTime(minutesElapsed: Int, secondsElapsed: Int) {
        matchInProgress.updateMatchTime(minutesElapsed, secondsElapsed)
        updateTimerViewWithMatchTime
    }

    private def updateTimeOnEndOfHalf {
        updateMatchTime(matchInProgress.stage.product * getLengthOfHalf, 0)
    }

    private def updateTimerViewWithMatchTime() {
        val timerView = resourceHelper.findViewById(R.id.timer).asInstanceOf[TextView]
        if (timerView != null) {
          timerView.setText(Format.formatInteger(matchInProgress.minutesElapsed) + ":" + Format.formatInteger(matchInProgress.secondsElapsed))
        }
    }

    private def startHalf {
        updateMatchStageEnum
        updateHalfOrFullTimeTextView
        updateCurrentHalfTextView
        resumeMatch
    }

    private def updateHalfOrFullTimeTextView {
        var halfOrFullTime = resourceHelper.findViewById(R.id.halfOrFullTime).asInstanceOf[TextView]
        halfOrFullTime.setText(matchInProgress.stage.halfOrFullTimeButtonText)
        halfOrFullTime.setVisibility(matchInProgress.stage.halfOrFullTimeButtonVisibility)
    }

    private def updateViewsVisibility {
        var pauseOrResumeTimer = resourceHelper.findViewById(R.id.pauseOrResumeTimer).asInstanceOf[TextView]
        if(pauseOrResumeTimer != null) {
          pauseOrResumeTimer.setText(matchInProgress.timerStatus.toString)
          pauseOrResumeTimer.setVisibility(matchInProgress.stage.viewVisibility)
          TeamType.forAllTeamTypes { teamType =>
            MatchCentreActivity.matchStatisticViews.foreach(statisticView =>
              resourceHelper.findViewById(R.id.statisticsButtons)
                .findViewById(statisticView)
                .setVisibility(matchInProgress.stage.viewVisibility))
          }
        }
    }

    private def updateCurrentHalfTextView {
        var halfOrFullTime = resourceHelper.findViewById(R.id.currentHalf).asInstanceOf[TextView]
        halfOrFullTime.setText(matchInProgress.stage.currentHalfLabelText)
    }

    private def pauseMatch {
        if (matchTimer != null) {
            matchTimer.cancel()
        }
        matchTimer = null
        updateTimerStatus
        updateViewsVisibility
    }

    private def resumeMatch {
        matchTimer = newMatchTimer(getLengthOfHalf, matchInProgress.minutesElapsed, matchInProgress.secondsElapsed)
        matchTimer.start()
        updateTimerStatus
        updateViewsVisibility
    }

    private def getLengthOfHalf = {
        val competition = competitionDao.retrieveByName(matchInProgress.competition.name)
        competition.lengthOfHalf
    }

    private def updateTimerStatus {
        matchInProgress.timerStatus = TimerStatus.apply(matchInProgress.timerStatus.id * -1)
    }

    private def newMatchTimer(totalNumberOfMinutes: Int, minutesElapsed: Int, secondsElapsed: Int): MatchTimer = {
        new MatchTimer(totalNumberOfMinutes * 60, minutesElapsed, secondsElapsed, MatchTimerService.this)
    }

    private def updateMatchStageEnum = matchInProgress.stage = matchInProgress.stage.next
}