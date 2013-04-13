package com.gaastats.activity.service

import com.gaastats.domain.Match
import com.gaastats.domain.enums.MatchStage
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.google.inject.Singleton
import com.gaastats.R
import android.widget.TextView
import com.gaastats.domain.enums.TimerStatus
import android.view.View
import java.util.Date

@Singleton
class MatchTimerService {
    var matchInProgress: Match = null
    var matchTimer: MatchTimer = null
    @Inject
    var resourceHelper: ResourceHelper = null

    def updateMatchStage {
        var currentMatchStage = MatchStage.apply(matchInProgress.stage.id)
        incrementMatchStage
        currentMatchStage match {
            case MatchStage.MatchNotStarted | MatchStage.HalfTime => startHalf
            case MatchStage.FirstHalfInProgress | MatchStage.SecondHalfInProgress => endHalf
        }
    }

    def pauseResumeMatch {
        if (matchTimer != null) pauseMatch
        else resumeMatch
    }
    
    protected[service] def endHalf {
        updateHalfOrFullTimeTextView
        pauseMatch
    }

    protected[service] def updateMatchTime(minutesElapsed: Int, secondsElapsed: Int) {
        matchInProgress.updateMatchTime(minutesElapsed, secondsElapsed)
        var timerView = resourceHelper.findViewById(R.id.timer).asInstanceOf[TextView]
        timerView.setText(minutesElapsed + ":" + secondsElapsed)
    }

    private def startHalf {
        updateHalfOrFullTimeTextView
        resumeMatch
    }

    private def incrementMatchStage {
        matchInProgress.stage = MatchStage.apply(matchInProgress.stage.id + 1)
    }

    private def updateHalfOrFullTimeTextView {
        var halfOrFullTime = resourceHelper.findViewById(R.id.halfOrFullTime).asInstanceOf[TextView]
        halfOrFullTime.setText(matchInProgress.stage.toString)
    }

    private def updatePauseOrResumeTimerView {
        var pauseOrResumeTimer = resourceHelper.findViewById(R.id.pauseOrResumeTimer).asInstanceOf[TextView]
        pauseOrResumeTimer.setText(matchInProgress.timerStatus.toString)
        if (matchInProgress.stage.equals(MatchStage.FullTime)) pauseOrResumeTimer.setVisibility(View.INVISIBLE)
    }

    private def pauseMatch {
        matchTimer.cancel()
        matchTimer = null
        updateTimerStatus
        updatePauseOrResumeTimerView
    }

    private def resumeMatch {
        matchTimer = newMatchTimer(matchInProgress.competition.lengthOfHalf, matchInProgress.minutesElapsed, matchInProgress.secondsElapsed)
        matchTimer.start()
        updateTimerStatus
        updatePauseOrResumeTimerView
    }

    private def updateTimerStatus {
        matchInProgress.timerStatus = TimerStatus.apply(matchInProgress.timerStatus.id * -1)
    }

    private def newMatchTimer(totalNumberOfMinutes: Int, minutesElapsed: Int, secondsElapsed: Int): MatchTimer = {
        var halfOrFullTimeView = resourceHelper.findViewById(R.id.halfOrFullTime).asInstanceOf[TextView]
        new MatchTimer(totalNumberOfMinutes * 60, 0, 0, MatchTimerService.this)
    }
}