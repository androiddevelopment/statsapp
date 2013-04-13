package com.gaastats.activity.events

import android.view.ViewGroup
import android.widget.TextView
import com.gaastats.R
import com.gaastats.domain.Competition
import android.widget.EditText
import com.google.inject.Inject
import com.gaastats.dao.CompetitionDao
import com.google.inject.Singleton
import com.gaastats.util.ResourceHelper
import android.view.View

@Singleton
class CompetitionSetupDialogHandler() {
  @Inject
  var competitionDao: CompetitionDao = null

  def retrieveValuesAndSave(competitionNameInput: EditText, numberOfPlayersInput: EditText, numberOfSubsInput: EditText, lengthOfHalfInput: EditText) {
    val competitionName = competitionNameInput.getText().toString()
    val numberOfPlayers = numberOfPlayersInput.getText().toString().toInt
    val numberOfSubs = numberOfSubsInput.getText().toString().toInt
    val lengthOfHalf = lengthOfHalfInput.getText().toString().toInt
    val competition = Competition(competitionName, numberOfPlayers, numberOfSubs, lengthOfHalf)

    save(competition)
  }
  
  def save(competition: Competition) = competitionDao.save(competition)
}