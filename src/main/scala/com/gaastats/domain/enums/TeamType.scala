package com.gaastats.domain.enums

import com.gaastats.R
import com.gaastats.domain.Team
import com.gaastats.domain.Match

abstract class TeamType(val layoutID: Int) {
    
    def getTeam(matchInProgress: Match) = {
        this match {
            case TeamType.Home => matchInProgress.homeTeam
            case TeamType.Away => matchInProgress.awayTeam
        }
    }
}

object TeamType {
    case object Home extends TeamType(R.id.homeTeamScore)
    case object Away extends TeamType(R.id.awayTeamScore)
    
    private def allTypes = List(Home, Away)
    
    def getTypeForLayoutID(layoutID: Int) = {
        allTypes.find(teamType => teamType.layoutID.equals(layoutID)).get
    }
    
    def forAllTeamTypes(performTeamTypeAction: TeamType =>  Unit) =  allTypes.foreach(teamType => performTeamTypeAction(teamType))
}