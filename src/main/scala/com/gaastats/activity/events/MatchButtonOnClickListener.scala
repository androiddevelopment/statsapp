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
import com.gaastats.ui.helper.DialogFragmentHelper
import com.gaastats.ui.StatisticLauncherDialogFragment

class MatchButtonOnClickListener extends OnClickListener {
    @Inject
    var matchTimerService: MatchTimerService = null
    @Inject
    var matchStatsService: MatchStatsService = null
    @Inject
    var dialogFragmentHelper: DialogFragmentHelper = null
    

    def onClick(view: View) {
        var parentViewID = view.getParent().asInstanceOf[View].getId
        view.getId() match {
            case R.id.halfOrFullTime => matchTimerService.updateMatchStage
            case R.id.pauseOrResumeTimer => matchTimerService.pauseResumeMatch
            case R.id.goalButton => dialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(matchStatsService.matchInProgress, "Goal"), MatchCentreActivity.GoalDialog)
            case R.id.pointButton => dialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(matchStatsService.matchInProgress, "Point"), MatchCentreActivity.PointDialog)
            case R.id.missButton => dialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(matchStatsService.matchInProgress, "Miss"), MatchCentreActivity.StatisticLauncherDialog)
            case R.id.cardButton => dialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(matchStatsService.matchInProgress, "Card"), MatchCentreActivity.StatisticLauncherDialog)
            case R.id.kickoutButton => dialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(matchStatsService.matchInProgress, "Kickout"), MatchCentreActivity.StatisticLauncherDialog)
            case R.id.undoLast => matchStatsService.undoLast
        }

        
    }
}