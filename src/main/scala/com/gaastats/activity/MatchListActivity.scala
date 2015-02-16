package com.gaastats.activity

import android.app.{ListActivity, Activity}
import android.content.{Context, Intent}
import android.os.Bundle
import android.widget.ListView
import com.gaastats.R
import com.gaastats.dao.MatchDao
import com.gaastats.domain.Match
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import roboguice.activity.RoboFragmentActivity

/**
 * Created by David on 2/15/2015.
 */
class MatchListActivity extends RoboFragmentActivity {
  @Inject
  var matchDao: MatchDao = null
  @Inject
  var resourceHelper: ResourceHelper = null

  override def onCreate(savedInstanceState: Bundle){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.match_list_view)
    resourceHelper.setActivity(this)
    findViewById(R.id.matchListView).asInstanceOf[ListView].setAdapter(new MatchListViewAdapter(matchDao, resourceHelper))
  }

  override def onResume() {
    super.onResume()
    resourceHelper.setActivity(this)
  }

  def startMatchCentreActivity(matchToStart: Match) = {
    val matchCentreActivity = new Intent(this.asInstanceOf[Context], classOf[MatchCentreActivity])
    matchCentreActivity.putExtra("matchID", matchToStart.id)
    startActivity(matchCentreActivity)
  }


}
