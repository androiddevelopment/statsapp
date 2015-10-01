package com.gaastats.activity.service

import android.app.Activity
import android.widget.TextView
import com.gaastats.R
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.dao.CompetitionDao
import com.gaastats.domain.Match
import com.gaastats.domain.enums.{MatchStageEnum, TeamType, TimerStatus}
import com.gaastats.util.{Format, ResourceHelper}
import main.scala.com.gaastats.activity.service.MatchViewsService

import scala.Option

object MatchTimerService {
    var matchInProgress: Match = null
    var matchTimer: Option[MatchTimer] = None

    def updateMatchStage {
        matchInProgress.getStage match {
            case MatchStageEnum.MatchNotStarted | MatchStageEnum.HalfTime => startHalf
            case MatchStageEnum.FirstHalfInProgress | MatchStageEnum.SecondHalfInProgress => endHalf
        }
    }

    def pauseResumeMatch {
        matchTimer match {
          case Some(_) => pauseMatch
          case None => resumeMatch
        }
    }

    protected[service] def endHalf {
        matchInProgress.nextStage
        MatchViewsService.updateHalfButtonAndTextView
        pauseMatch
        updateTimeOnEndOfHalf
    }

    protected[service] def updateMatchTime(minutesElapsed: Int, secondsElapsed: Int) {
        matchInProgress.updateMatchTime(minutesElapsed, secondsElapsed)
        MatchViewsService.updateTimerViewWithMatchTime
    }

    private def updateTimeOnEndOfHalf {
        updateMatchTime(matchInProgress.getStage.product * getLengthOfHalf, 0)
    }

    private def startHalf {
        matchInProgress.nextStage
        MatchViewsService.updateHalfButtonAndTextView
        resumeMatch
    }

    def pauseMatch {
        matchTimer match {
          case Some(_) => matchTimer.get.cancel
          case None =>
        }
        matchTimer = None
        updateTimerStatus
        MatchViewsService.updateViewsVisibility
    }

    private def resumeMatch {
        matchTimer = Option(newMatchTimer(getLengthOfHalf, matchInProgress.minutesElapsed, matchInProgress.secondsElapsed))
        matchTimer.get.start
        updateTimerStatus
        MatchViewsService.updateViewsVisibility
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

}