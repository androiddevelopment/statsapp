package com.gaastats.activity.events

import android.widget.EditText
import com.gaastats.dao.CompetitionDao
import com.gaastats.domain.Competition


object CompetitionSetupDialogHandler {

  def retrieveValuesAndSave(competitionNameInput: EditText, numberOfPlayersInput: EditText, numberOfSubsInput: EditText, lengthOfHalfInput: EditText) {
    val competitionName = competitionNameInput.getText().toString()
    val numberOfPlayers = numberOfPlayersInput.getText().toString().toInt
    val numberOfSubs = numberOfSubsInput.getText().toString().toInt
    val lengthOfHalf = lengthOfHalfInput.getText().toString().toInt
    val competition = Competition(competitionName, numberOfPlayers, numberOfSubs, lengthOfHalf)

    save(competition)
  }
  
  def save(competition: Competition) = CompetitionDao.save(competition)
}