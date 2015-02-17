package com.gaastats.activity.service

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.gaastats.dao.helper.StatisticHelper
import com.gaastats.domain.enums.TeamType

/**
 * Created by David on 2/16/2015.
 */
object MatchStatsViewsService {
  var activity: Activity = null

  def refreshTeamStatisticViews(teamType: TeamType) {
    updateStatisticTextView(teamType, "Goal")
    updateStatisticTextView(teamType, "Point")
    updateStatisticTextView(teamType, "Miss")
    updateStatisticTextView(teamType, "Kickout")
    updateStatisticTextView(teamType, "Yellow Card")
    updateStatisticTextView(teamType, "Red Card")
    updateStatisticTextView(teamType, "Black Card")
  }

  private def updateStatisticTextView(teamType: TeamType, statisticName: String) {
    val teamScoreLayout = teamType.layoutID
    val teamScoreLayoutView = activity.findViewById(teamScoreLayout)
    val statisticViewID = StatisticHelper.getStatisticView(statisticName)
    val statisticTextView = teamScoreLayoutView.findViewById(statisticViewID).asInstanceOf[TextView]
    statisticTextView.setText(MatchStatsService.retrieveStatisticSum(statisticName, teamType).toString)
  }


}
