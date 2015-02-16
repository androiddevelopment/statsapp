package com.gaastats.ui

import com.gaastats.activity.service.MatchStatsService
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.domain.enums.TeamType
import com.google.inject.Inject
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.domain.Match
import com.gaastats.ui.helper.DialogFragmentHelper

class StatisticLauncherDialogFragment extends DialogFragment {
    @Inject
    var dialogFragmentHelper: DialogFragmentHelper = null
    var matchInProgress: Match = null
    var statisticName: String = null

    def this(matchInProgress: Match, statisticName: String) = {
        this
        this.matchInProgress = matchInProgress
        this.statisticName = statisticName
    }

    def createDialog(): Dialog = {
        val builder = new AlertDialog.Builder(getActivity())
        val teamNames = matchInProgress.getTeamNames
        builder.setItems(teamNames, new DialogInterface.OnClickListener() {
            def onClick(dialog: DialogInterface, which: Int) {
                val teamNameSelected = teamNames(which).toString()
                dialogFragmentHelper.showFragment(new BaseStatisticTypeDialogFragment(statisticName), MatchCentreActivity.StatisticLauncherDialog, matchInProgress.getTeamTypeForTeamName(teamNameSelected).layoutID)
                dismiss()
            }
        })

        builder.create()
    }
    
    override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
        createDialog
    }
    
}