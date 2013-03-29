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

@Singleton
class TeamSetupDialogFragment extends BaseSetupDialogFragment {
  @Inject
  var teamSetupDialogHandler: TeamSetupDialogHandler = null

  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    val dialogView = getActivity().getLayoutInflater.inflate(R.layout.team_setup, null)

    createDialog(dialogView, R.string.teamSetupDialogMessage, {
      val teamNameInput = dialogView.findViewById(R.id.teamNameInput).asInstanceOf[EditText]
      teamSetupDialogHandler.retrieveValuesAndSave(teamNameInput)
    })
  }
}