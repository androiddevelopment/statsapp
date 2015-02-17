package com.gaastats.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, Spinner}
import com.gaastats.R
import com.gaastats.activity.events.MatchSetupDialogHandler
import com.gaastats.dao.{CompetitionDao, TeamDao}

import scala.collection.JavaConversions._

object MatchSetupDialogFragment extends BaseSetupDialogFragment {
  var dialogView: View = null
    
  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    dialogView = getActivity().getLayoutInflater.inflate(R.layout.match_setup, null)
    
    createDialog(dialogView, R.string.matchSetupDialogMessage, R.string.start, {
          val homeTeamNameInput = dialogView.findViewById(R.id.homeTeamSpinner).asInstanceOf[Spinner]
          val awayTeamNameInput = dialogView.findViewById(R.id.awayTeamSpinner).asInstanceOf[Spinner]
          val competitionSpinner = dialogView.findViewById(R.id.competitionSpinner).asInstanceOf[Spinner]
          MatchSetupDialogHandler.retrieveValuesAndSave(homeTeamNameInput, awayTeamNameInput, competitionSpinner)
          
    })
  }

  override def onStart() {
    super.onStart
    populateSpinner(CompetitionDao.retrieveAllNames, R.id.competitionSpinner)
    val teamNames = TeamDao.retrieveAllNames
    populateSpinner(teamNames, R.id.homeTeamSpinner)
    populateSpinner(teamNames, R.id.awayTeamSpinner)
  }
  
  private def populateSpinner(contentList: java.util.List[String], spinnerID: Int) {
    val spinner = dialogView.findViewById(spinnerID).asInstanceOf[Spinner]
    val adapter: ArrayAdapter[String] = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, contentList)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.setAdapter(adapter)    
  }
}