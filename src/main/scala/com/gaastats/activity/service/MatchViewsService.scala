package main.scala.com.gaastats.activity.service

import android.app.Activity
import android.widget.TextView
import com.gaastats.R
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.domain.Match
import com.gaastats.domain.enums.TeamType
import com.gaastats.util.Format

/**
 * Created by David on 2/21/2015.
 */
object MatchViewsService {
  var matchInProgress: Match = null
  var activity: Activity = null

  def initializeViews {
    updateTimerViewWithMatchTime
    updateHalfOrFullTimeTextView
    updateViewsVisibility
    updateCurrentHalfTextView
  }

  def updateHalfButtonAndTextView {
    updateHalfOrFullTimeTextView
    updateCurrentHalfTextView
  }

  private def updateHalfOrFullTimeTextView {
    val halfOrFullTime = activity.findViewById(R.id.halfOrFullTime).asInstanceOf[TextView]
    halfOrFullTime.setText(matchInProgress.stage.halfOrFullTimeButtonText)
    halfOrFullTime.setVisibility(matchInProgress.stage.halfOrFullTimeButtonVisibility)
  }

  def updateViewsVisibility {
    val pauseOrResumeTimer = activity.findViewById(R.id.pauseOrResumeTimer).asInstanceOf[TextView]
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
    val halfOrFullTime = activity.findViewById(R.id.currentHalf).asInstanceOf[TextView]
    halfOrFullTime.setText(matchInProgress.stage.currentHalfLabelText)
  }

  def updateTimerViewWithMatchTime() {
    val timerView = activity.findViewById(R.id.timer).asInstanceOf[TextView]
    if (timerView != null) {
      timerView.setText(Format.formatInteger(matchInProgress.minutesElapsed) + ":" + Format.formatInteger(matchInProgress.secondsElapsed))
    }
  }



}
