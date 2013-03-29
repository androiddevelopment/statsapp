package com.gaastats.domain

import org.apache.commons.lang.builder.ToStringBuilder

import com.j256.ormlite.field.DatabaseField

object Match {
    def apply(competition: Competition, homeTeam: Team, awayTeam: Team, firstHalfTimeLeft: Integer, secondHalfTimeLeft: Integer): Match = {
        val newMatch = new Match
        newMatch.competition = competition
        newMatch.homeTeam = homeTeam
        newMatch.awayTeam = awayTeam
        newMatch.firstHalfTimeLeft = firstHalfTimeLeft
        newMatch.secondHalfTimeLeft = secondHalfTimeLeft
        return newMatch
    }
}

class Match {
    @DatabaseField(foreign = true) var competition: Competition = null
    @DatabaseField(foreign = true) var homeTeam: Team = null
    @DatabaseField(foreign = true) var awayTeam: Team = null
    @DatabaseField var firstHalfTimeLeft: Integer = null
    @DatabaseField var secondHalfTimeLeft: Integer = null
    @DatabaseField(generatedId = true, index = true) var id: Int = 0

    override def toString = ToStringBuilder.reflectionToString(this)
}