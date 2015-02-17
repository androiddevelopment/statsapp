package com.gaastats.activity.events

import android.view.View
import android.view.View.OnClickListener
import com.gaastats.R
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.activity.service.{MatchStatsService, MatchTimerService}
import com.gaastats.ui.StatisticLauncherDialogFragment
import com.gaastats.ui.helper.DialogFragmentHelper

object MatchButtonOnClickListener extends OnClickListener {

    def onClick(view: View) {
        var parentViewID = view.getParent().asInstanceOf[View].getId
        view.getId() match {
            case R.id.halfOrFullTime => MatchTimerService.updateMatchStage
            case R.id.pauseOrResumeTimer => MatchTimerService.pauseResumeMatch
            case R.id.goalButton => DialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(MatchStatsService.matchInProgress, "Goal"), MatchCentreActivity.GoalDialog)
            case R.id.pointButton => DialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(MatchStatsService.matchInProgress, "Point"), MatchCentreActivity.PointDialog)
            case R.id.missButton => DialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(MatchStatsService.matchInProgress, "Miss"), MatchCentreActivity.StatisticLauncherDialog)
            case R.id.cardButton => DialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(MatchStatsService.matchInProgress, "Card"), MatchCentreActivity.StatisticLauncherDialog)
            case R.id.kickoutButton => DialogFragmentHelper.showFragment(new StatisticLauncherDialogFragment(MatchStatsService.matchInProgress, "Kickout"), MatchCentreActivity.StatisticLauncherDialog)
            case R.id.undoLast => MatchStatsService.undoLast
        }

        
    }
}