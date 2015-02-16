package com.gaastats.domain

import java.util.Date

import org.apache.commons.lang.builder.ToStringBuilder
import org.joda.time.DateTime

import com.gaastats.domain.enums.MatchStageEnum
import com.gaastats.domain.enums.TeamType
import com.gaastats.domain.enums.TimerStatus
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

object Match {
    def apply(competition: Competition, homeTeam: Team, awayTeam: Team): Match = {
        val newMatch = new Match
        newMatch.competition = competition
        newMatch.homeTeam = homeTeam
        newMatch.awayTeam = awayTeam
        newMatch.timeElapsed = startDate
        newMatch.stage = MatchStageEnum.MatchNotStarted
        newMatch.timerStatus = TimerStatus.Paused
        return newMatch
    }
    
    private def startDate: Date = {
        def startDate = new DateTime(2013, 1, 1, 0, 0, 0)
        startDate.toDate()
    }
}

@DatabaseTable
class Match extends Serializable{
    @DatabaseField(foreign = true) var competition: Competition = null
    @DatabaseField(foreign = true) var homeTeam: Team = null
    @DatabaseField(foreign = true) var awayTeam: Team = null
    @DatabaseField private var timeElapsed: Date = null
    @DatabaseField var dateOfMatch: Date = new Date
    @DatabaseField(generatedId = true) var id: Int = 0
    var stage: MatchStageEnum = MatchStageEnum.MatchNotStarted
    var timerStatus: TimerStatus.Status = TimerStatus.Paused
    
    def updateMatchTime(minutesElapsed: Int, secondsElapsed: Int) {
        var matchTime = new DateTime(timeElapsed)
        matchTime = matchTime.withMinuteOfHour(minutesElapsed)
        matchTime = matchTime.withSecondOfMinute(secondsElapsed)
        timeElapsed = matchTime.toDate()
    }
    
    def matchTime: Date = {
        timeElapsed.clone().asInstanceOf[Date]
    }
    
    def minutesElapsed: Int = {
        val matchTime = new DateTime(timeElapsed)
        matchTime.minuteOfHour().get
    }
    
    def secondsElapsed: Int = {
        val matchTime = new DateTime(timeElapsed)
        matchTime.secondOfMinute().get
    }
    
     def getTeamNames = {
        Array[CharSequence](homeTeam.name, awayTeam.name)
    }
    
    def getTeamTypeForTeamName(name: String): TeamType = {
      val HomeTeam = homeTeam.name
        name match {
          case HomeTeam => TeamType.Home
          case _ => TeamType.Away
        }
    }
    
    override def toString = ToStringBuilder.reflectionToString(this)
}