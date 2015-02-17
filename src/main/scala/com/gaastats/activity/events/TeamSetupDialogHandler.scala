package com.gaastats.activity.events

import android.widget.EditText
import com.gaastats.dao.TeamDao
import com.gaastats.domain.Team


object TeamSetupDialogHandler {

  def retrieveValuesAndSave(teamNameInput: EditText) {
    val teamName = teamNameInput.getText().toString()
    val team = Team(teamName)
    save(team)
  }

  def save(team: Team) = TeamDao.save(team)
}