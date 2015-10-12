package com.gaastats.activity

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import com.gaastats.R
import com.gaastats.dao.MatchDao
import org.scaloid.common.SActivity

/**
 * Created by David on 2/15/2015.
 */
class MatchListActivity extends SActivity  {

  override def onCreate(savedInstanceState: Bundle){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.match_list_view)
    val listView = find[ListView](R.id.matchListView)
    listView.setAdapter(new MatchListViewAdapter(MatchDao.retrieveAllMatches(), this, listView))
  }

  override def onResume() {
    super.onResume()
  }

}
