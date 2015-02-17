package com.gaastats.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import com.gaastats.R
import com.gaastats.activity.events.CompetitionSetupDialogHandler

object CompetitionSetupDialogFragment extends BaseSetupDialogFragment {

  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    val dialogView = getActivity().getLayoutInflater.inflate(R.layout.competition_setup, null)

    createDialog(dialogView, R.string.competitionSetupDialogMessage, R.string.create, {
      val competitionNameInput = dialogView.findViewById(R.id.competitionNameInput).asInstanceOf[EditText]
      val numberOfPlayersInput = dialogView.findViewById(R.id.numberOfPlayersInput).asInstanceOf[EditText]
      val numberOfSubsInput = dialogView.findViewById(R.id.numberOfSubsInput).asInstanceOf[EditText]
      val lengthOfHalfInput = dialogView.findViewById(R.id.lengthOfHalfInput).asInstanceOf[EditText]
      CompetitionSetupDialogHandler.retrieveValuesAndSave(competitionNameInput, numberOfPlayersInput, numberOfSubsInput, lengthOfHalfInput)
    })
  }

  
}