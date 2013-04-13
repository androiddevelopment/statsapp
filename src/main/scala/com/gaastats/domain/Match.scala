package com.gaastats.domain

import org.apache.commons.lang.builder.ToStringBuilder
import com.j256.ormlite.field.DatabaseField
import java.util.Date
import org.joda.time.DateTime
import com.gaastats.domain.enums.MatchStage
import com.gaastats.domain.enums.TimerStatus

object Match {
    def apply(competition: Competition, homeTeam: Team, awayTeam: Team): Match = {
        val newMatch = new Match
        newMatch.competition = competition
        newMatch.homeTeam = homeTeam
        newMatch.awayTeam = awayTeam
        newMatch.timeElapsed = startDate
        newMatch.stage = MatchStage.MatchNotStarted
        newMatch.timerStatus = TimerStatus.Paused
        return newMatch
    }
    
    private def startDate: Date = {
        def startDate = new DateTime(1, 1, 1, 0, 0, 0)
        startDate.toDate()
    }
}

class Match {
    @DatabaseField(foreign = true) var competition: Competition = null
    @DatabaseField(foreign = true) var homeTeam: Team = null
    @DatabaseField(foreign = true) var awayTeam: Team = null
    @DatabaseField private var timeElapsed: Date = null
    @DatabaseField var dateOfMatch: Date = new Date
    @DatabaseField(generatedId = true, id = true) var id: Int = 0
    var stage: MatchStage.Stage = null 
    var timerStatus: TimerStatus.Status = null
    
    def updateMatchTime(minutesElapsed: Int, secondsElapsed: Int) {
        var matchTime = new DateTime(timeElapsed)
        matchTime.withMinuteOfHour(minutesElapsed)
        matchTime.withSecondOfMinute(secondsElapsed)
        timeElapsed = matchTime.toDate()
    }
    
    def matchTime: Date = {
        timeElapsed.clone().asInstanceOf[Date]
    }
    
    def minutesElapsed: Int = {
        var matchTime = new DateTime(timeElapsed)
        matchTime.minuteOfHour().toString().toInt
    }
    
    def secondsElapsed: Int = {
        var matchTime = new DateTime(timeElapsed)
        matchTime.secondOfMinute().toString().toInt
    }
    
    override def toString = ToStringBuilder.reflectionToString(this)
}