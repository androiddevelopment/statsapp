package com.gaastats.ui

import com.gaastats.activity.events.MatchSetupDialogHandler
import com.gaastats.dao.CompetitionDao
import com.google.inject.Inject
import com.google.inject.Singleton
import com.gaastats.R
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.gaastats.util.ResourceHelper
import android.widget.EditText
import android.view.View
import com.gaastats.dao.TeamDao
import scala.collection.JavaConversions._

@Singleton
class MatchSetupDialogFragment extends BaseSetupDialogFragment {
  @Inject
  var matchSetupDialogHandler: MatchSetupDialogHandler = null
  @Inject
  var competitionDao: CompetitionDao = null
  @Inject
  var teamDao: TeamDao = null
  var dialogView: View = null
    
  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
    dialogView = getActivity().getLayoutInflater.inflate(R.layout.match_setup, null)
    
    createDialog(dialogView, R.string.matchSetupDialogMessage, R.string.start, {
          val homeTeamNameInput = dialogView.findViewById(R.id.homeTeamSpinner).asInstanceOf[Spinner]
          val awayTeamNameInput = dialogView.findViewById(R.id.awayTeamSpinner).asInstanceOf[Spinner]
          val competitionSpinner = dialogView.findViewById(R.id.competitionSpinner).asInstanceOf[Spinner]
          matchSetupDialogHandler.retrieveValuesAndSave(homeTeamNameInput, awayTeamNameInput, competitionSpinner)
          
    })
  }

  override def onStart() {
    super.onStart
    populateSpinner(competitionDao.retrieveAllNames, R.id.competitionSpinner)
    var teamNames = teamDao.retrieveAllNames
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