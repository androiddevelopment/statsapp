package com.gaastats.activity.service

import android.app.Application
import android.content.{Context, Intent}
import com.gaastats.activity.{MatchListActivity, MatchCentreActivity}
import com.gaastats.domain.Match
import com.gaastats.util.ResourceHelper._

/**
 * Created by David on 2/16/2015.
 */
object ActivityStarterService {
  def startMatchCentreActivity(matchToStart: Match) = {
    val matchCentreActivity = new Intent(application.asInstanceOf[Context], classOf[MatchCentreActivity])
    matchCentreActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    matchCentreActivity.putExtra("matchID", matchToStart.id)
    start(matchCentreActivity)
  }

  def startMatchListViewActivity() = {
    val matchListViewActivity = new Intent(application.asInstanceOf[Context], classOf[MatchListActivity])
    matchListViewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    start(matchListViewActivity)
  }

  private def start(intent: Intent)(implicit application: Application) {
    application.startActivity(intent)
  }

}
