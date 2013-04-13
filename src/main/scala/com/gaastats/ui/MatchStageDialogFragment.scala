package com.gaastats.ui

import com.gaastats.activity.events.CompetitionSetupDialogHandler
import com.google.inject.Inject
import com.google.inject.Singleton
import com.gaastats.R
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import com.gaastats.util.ResourceHelper
import android.widget.EditText
import com.gaastats.activity.events.TeamSetupDialogHandler
import com.gaastats.activity.service.MatchTimerService

@Singleton
class MatchStageDialogFragment extends BaseSetupDialogFragment {
    @Inject
    var matchService: MatchTimerService = null

    override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
        createDialog(null, R.string.matchStageDialogMessage, R.string.ok, {
            matchService.updateMatchStage
        })
    }
}