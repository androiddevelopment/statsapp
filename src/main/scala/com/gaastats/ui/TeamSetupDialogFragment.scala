package com.gaastats.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import com.gaastats.R
import com.gaastats.activity.events.TeamSetupDialogHandler

object TeamSetupDialogFragment extends BaseSetupDialogFragment {

  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    val dialogView = getActivity().getLayoutInflater.inflate(R.layout.team_setup, null)

    createDialog(dialogView, R.string.teamSetupDialogMessage, R.string.create, {
      val teamNameInput = dialogView.findViewById(R.id.teamNameInput).asInstanceOf[EditText]
      TeamSetupDialogHandler.retrieveValuesAndSave(teamNameInput)
    })
  }
}
