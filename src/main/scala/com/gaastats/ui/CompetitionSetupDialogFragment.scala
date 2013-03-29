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
import android.view.View

@Singleton
class CompetitionSetupDialogFragment extends BaseSetupDialogFragment {
  @Inject
  var competitionSetupDialogHandler: CompetitionSetupDialogHandler = null

  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    val dialogView = getActivity().getLayoutInflater.inflate(R.layout.competition_setup, null)

    createDialog(dialogView, R.string.competitionSetupDialogMessage, {
      val competitionNameInput = dialogView.findViewById(R.id.competitionNameInput).asInstanceOf[EditText]
      val numberOfPlayersInput = dialogView.findViewById(R.id.numberOfPlayersInput).asInstanceOf[EditText]
      val numberOfSubsInput = dialogView.findViewById(R.id.numberOfSubsInput).asInstanceOf[EditText]
      val lengthOfHalfInput = dialogView.findViewById(R.id.lengthOfHalfInput).asInstanceOf[EditText]
      competitionSetupDialogHandler.retrieveValuesAndSave(competitionNameInput, numberOfPlayersInput, numberOfSubsInput, lengthOfHalfInput)
    })
  }

  
}