package main.scala.com.gaastats.ui

import android.app.Dialog
import android.os.Bundle
import com.gaastats.R
import com.gaastats.activity.MatchListViewAdapter
import com.gaastats.domain.Match
import com.gaastats.ui.BaseSetupDialogFragment

/**
 * Created by David on 2/20/2015.
 */
class DeleteMatchDialogFragment(matchToDelete: Match, adapter: MatchListViewAdapter) extends BaseSetupDialogFragment{

  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    createDialog(null, R.string.deleteMatch, R.string.ok, {
      adapter.deleteItem(matchToDelete)
    })
  }
}
