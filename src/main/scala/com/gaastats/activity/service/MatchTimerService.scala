package com.gaastats.activity.service

import android.app.Activity
import android.widget.TextView
import com.gaastats.R
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.dao.CompetitionDao
import com.gaastats.domain.Match
import com.gaastats.domain.enums.{MatchStageEnum, TeamType, TimerStatus}
import com.gaastats.util.{Format, ResourceHelper}

object MatchTimerService {
    var matchInProgress: Match = null
    var matchTimer: MatchTimer = null
    var activity: Activity = null

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
        val timerView = activity.findViewById(R.id.timer).asInstanceOf[TextView]
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
        var halfOrFullTime = activity.findViewById(R.id.halfOrFullTime).asInstanceOf[TextView]
        halfOrFullTime.setText(matchInProgress.stage.halfOrFullTimeButtonText)
        halfOrFullTime.setVisibility(matchInProgress.stage.halfOrFullTimeButtonVisibility)
    }

    private def updateViewsVisibility {
        var pauseOrResumeTimer = activity.findViewById(R.id.pauseOrResumeTimer).asInstanceOf[TextView]
        if(pauseOrResumeTimer != null) {
          pauseOrResumeTimer.setText(matchInProgress.timerStatus.toString)
          pauseOrResumeTimer.setVisibility(matchInProgress.stage.viewVisibility)
          TeamType.forAllTeamTypes { teamType =>
            MatchCentreActivity.matchStatisticViews.foreach(statisticView =>
              activity.findViewById(R.id.statisticsButtons)
                .findViewById(statisticView)
                .setVisibility(matchInProgress.stage.viewVisibility))
          }
        }
    }

    private def updateCurrentHalfTextView {
        var halfOrFullTime = activity.findViewById(R.id.currentHalf).asInstanceOf[TextView]
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
        val competition = CompetitionDao.retrieveByName(matchInProgress.competition.name)
        competition.lengthOfHalf
    }

    private def updateTimerStatus {
        matchInProgress.timerStatus = TimerStatus.apply(matchInProgress.timerStatus.id * -1)
    }

    private def newMatchTimer(totalNumberOfMinutes: Int, minutesElapsed: Int, secondsElapsed: Int): MatchTimer = {
        new MatchTimer(totalNumberOfMinutes * 60, minutesElapsed, secondsElapsed)
    }

    private def updateMatchStageEnum = matchInProgress.stage = matchInProgress.stage.next
}