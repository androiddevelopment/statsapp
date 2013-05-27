package com.gaastats.activity.events

import com.gaastats.R
import com.gaastats.activity.HomeScreenActivity
import com.gaastats.ui.CompetitionSetupDialogFragment
import com.gaastats.ui.MatchSetupDialogFragment
import com.gaastats.ui.TeamSetupDialogFragment
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.google.inject.Singleton

import android.view.View
import android.view.View.OnClickListener

class SetupButtonOnClickListener extends OnClickListener {
	@Inject
	var competitionSetupDialogFragment: CompetitionSetupDialogFragment = null
	@Inject
	var matchSetupDialogFragment: MatchSetupDialogFragment = null
	@Inject
	var teamSetupDialogFragment: TeamSetupDialogFragment = null
	@Inject
	var resourceHelper: ResourceHelper = null
	
  def onClick(view: View) {
	  view.getId() match {
	    case R.id.competitionSetupButton => competitionSetupDialogFragment.show(resourceHelper.getFragmentManager(), HomeScreenActivity.CompetitionDialog)
	    case R.id.matchSetupButton => matchSetupDialogFragment.show(resourceHelper.getFragmentManager(), HomeScreenActivity.MatchDialog)
	    case R.id.teamSetupButton => teamSetupDialogFragment.show(resourceHelper.getFragmentManager(), HomeScreenActivity.TeamDialog)
	  }
  }

}

