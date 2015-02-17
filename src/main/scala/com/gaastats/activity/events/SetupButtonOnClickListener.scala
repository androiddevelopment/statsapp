package com.gaastats.activity.events

import android.app.Activity
import android.view.View
import android.view.View.OnClickListener
import com.gaastats.R
import com.gaastats.activity.HomeScreenActivity
import com.gaastats.activity.service.ActivityStarterService
import com.gaastats.ui.{CompetitionSetupDialogFragment, MatchSetupDialogFragment, TeamSetupDialogFragment}
import com.gaastats.util.ResourceHelper

object SetupButtonOnClickListener extends OnClickListener {
  var activity: Activity = null

  def onClick(view: View) {
	  view.getId() match {
	    case R.id.competitionSetupButton => CompetitionSetupDialogFragment.show(activity.getFragmentManager, HomeScreenActivity.CompetitionDialog)
	    case R.id.matchSetupButton => MatchSetupDialogFragment.show(activity.getFragmentManager, HomeScreenActivity.MatchDialog)
	    case R.id.teamSetupButton => TeamSetupDialogFragment.show(activity.getFragmentManager, HomeScreenActivity.TeamDialog)
      case R.id.viewMatchesButton => ActivityStarterService.startMatchListViewActivity()
	  }
  }

}

