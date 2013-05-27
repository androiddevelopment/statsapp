package com.gaastats.activity.events

import com.gaastats.R
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.activity.service.MatchStatsService
import com.gaastats.activity.service.MatchTimerService
import com.gaastats.ui.BaseStatisticTypeDialogFragment
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.Singleton

import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener

class MatchButtonOnClickListener extends OnClickListener {
    @Inject
    var matchTimerService: MatchTimerService = null
    @Inject
    var resourceHelper: ResourceHelper = null
    @Inject
    var matchStatsService: MatchStatsService = null
    @Inject
    var injector: Injector = null
    

    def onClick(view: View) {
        var parentViewID = view.getParent().asInstanceOf[View].getId
        view.getId() match {
            case R.id.halfOrFullTime => matchTimerService.updateMatchStage
            case R.id.pauseOrResumeTimer => matchTimerService.pauseResumeMatch
            case R.id.goalButton => showFragment(new BaseStatisticTypeDialogFragment("Goal"), MatchCentreActivity.GoalDialog, parentViewID)
            case R.id.pointButton => showFragment(new BaseStatisticTypeDialogFragment("Point"), MatchCentreActivity.PointDialog, parentViewID)
            case R.id.undoLast => matchStatsService.undoLast
        }

        def showFragment(fragment: DialogFragment, fragmentName: String, parentViewID: Int) {
            injector.injectMembers(fragment)
            fragment.setArguments(new Bundle)
            fragment.getArguments.putInt("parentViewID", parentViewID)
            fragment.show(resourceHelper.getFragmentManager(), fragmentName)
        }
    }
}