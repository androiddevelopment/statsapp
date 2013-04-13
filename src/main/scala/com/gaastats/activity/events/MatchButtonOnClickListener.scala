package com.gaastats.activity.events

import android.view.View
import com.gaastats.R
import com.gaastats.activity.service.MatchTimerService
import com.google.inject.Inject
import com.gaastats.util.ResourceHelper
import android.view.View.OnClickListener
import com.gaastats.ui.GoalStatisticDialogFragment
import com.gaastats.activity.MatchCentreActivity

class MatchButtonOnClickListener extends OnClickListener {
    @Inject
    var matchTimerService: MatchTimerService = null
    @Inject
    var resourceHelper: ResourceHelper = null
    @Inject
    var goalStatisticDialogFragment: GoalStatisticDialogFragment = null

    def onClick(view: View) {
        var parentViewID = view.getParent().asInstanceOf[View].getId
        view.getId() match {
            case R.id.halfOrFullTime => matchTimerService.updateMatchStage
            case R.id.pauseOrResumeTimer => matchTimerService.pauseResumeMatch
            case R.id.goalButton => goalStatisticDialogFragment.show(resourceHelper.getFragmentManager(), MatchCentreActivity.GoalDialog)
        }
    }
}