package com.gaastats.activity

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import com.gaastats.R

/**
 * Created by David on 2/15/2015.
 */
class MatchListActivity extends Activity {

  override def onCreate(savedInstanceState: Bundle){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.match_list_view)
    findViewById(R.id.matchListView).asInstanceOf[ListView].setAdapter(MatchListViewAdapter)
  }

  override def onResume() {
    super.onResume()
  }

}
