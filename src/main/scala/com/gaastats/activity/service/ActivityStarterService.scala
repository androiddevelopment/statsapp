package com.gaastats.activity.service

import android.app.Application
import android.content.{Context, Intent}
import com.gaastats.activity.{MatchListActivity, MatchCentreActivity}
import com.gaastats.domain.Match
import com.gaastats.util.ResourceHelper._
import org.scaloid.common.{SIntent, SContext}

/**
 * Created by David on 2/16/2015.
 */
object ActivityStarterService {
  def startMatchCentreActivity(matchToStart: Match) = {
    val matchCentreActivity = SIntent[MatchCentreActivity]
    matchCentreActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    matchCentreActivity.putExtra("matchID", matchToStart.id)
    start(matchCentreActivity)
  }

  def startMatchListViewActivity() = {
    val matchListViewActivity = SIntent[MatchListActivity]
    matchListViewActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    start(matchListViewActivity)
  }

  private def start(intent: Intent)(implicit application: Application) {
    application.startActivity(intent)
  }

}
