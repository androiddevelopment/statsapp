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
import com.gaastats.dao.TeamDao
import com.gaastats.domain.Team

@Singleton
class TeamSetupDialogHandler() {
  @Inject
  var teamDao: TeamDao = null
  
  var parentView: View = null;

  def retrieveValuesAndSave(teamNameInput: EditText) {
    val teamName = teamNameInput.getText().toString()
    val team = new Team(teamName)

    save(team)
  }

  def save(team: Team) = teamDao.save(team)
}