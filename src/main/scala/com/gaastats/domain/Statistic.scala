package com.gaastats.domain

import java.util.Date
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

object Statistic {
    def apply(matchOccurred: Match, team: Team, matchTime: Date, statisticType: StatisticType): Statistic = {
        val statistic = new Statistic()
        statistic.matchOccurred = matchOccurred
        statistic.team = team
        statistic.matchTime = matchTime
        statistic.statisticType = statisticType        
        return statistic
    }
}

@DatabaseTable
class Statistic {
	@DatabaseField(foreign = true) var matchOccurred: Match = null
	@DatabaseField(foreign = true) var team: Team = null
	@DatabaseField var matchTime: Date = null
	@DatabaseField(generatedId = true) var id: Int = 0
	@DatabaseField(foreign = true) var statisticType: StatisticType = null
	@DatabaseField var isDeleted: java.lang.Boolean = false
	
	def markDeleted = isDeleted = true
}