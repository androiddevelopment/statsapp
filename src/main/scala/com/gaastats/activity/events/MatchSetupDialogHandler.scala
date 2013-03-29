package com.gaastats.activity.events

import com.gaastats.dao.CompetitionDao
import com.gaastats.domain.Match
import com.google.inject.Singleton
import com.gaastats.R
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import com.google.inject.Inject
import com.google.inject.Singleton
import com.gaastats.util.ResourceHelper
import android.view.View
import com.gaastats.dao.TeamDao

@Singleton
class MatchSetupDialogHandler {
  @Inject
  var competitionDao: CompetitionDao = null
  @Inject
  var teamDao: TeamDao = null
  @Inject
  var resourceHelper: ResourceHelper = null
  var parentView: View = null;
  
  def retrieveValuesAndSave(homeTeamSpinner: Spinner, awayTeamSpinner: Spinner, competitionSpinner: Spinner) {    
	  val competitionName = competitionSpinner.getSelectedItem().asInstanceOf[String]
	  val homeTeamName = homeTeamSpinner.getSelectedItem().asInstanceOf[String]
	  val awayTeamName = awayTeamSpinner.getSelectedItem().asInstanceOf[String]
	  val competition = competitionDao.retrieveByName(competitionName) 
	  val homeTeam = teamDao.retrieveByName(homeTeamName)
	  val awayTeam = teamDao.retrieveByName(awayTeamName)
	  val matchCreated = Match(competition, homeTeam, awayTeam, competition.lengthOfHalf, competition.lengthOfHalf)
	  
	  save(matchCreated)
  }
  
  def save(matchCreated: Match) = {}  
}