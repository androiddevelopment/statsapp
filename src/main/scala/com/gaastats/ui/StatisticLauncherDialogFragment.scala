package com.gaastats.ui

import android.app.{AlertDialog, Dialog, DialogFragment}
import android.content.DialogInterface
import android.os.Bundle
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.domain.Match
import com.gaastats.ui.helper.DialogFragmentHelper

class StatisticLauncherDialogFragment extends DialogFragment {
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
                DialogFragmentHelper.showFragment(new BaseStatisticTypeDialogFragment(statisticName), MatchCentreActivity.StatisticLauncherDialog, matchInProgress.getTeamTypeForTeamName(teamNameSelected).layoutID)
                dismiss()
            }
        })

        builder.create()
    }
    
    override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
        createDialog
    }
    
}