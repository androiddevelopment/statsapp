package com.gaastats.dao

import com.gaastats.domain.Statistic
import com.gaastats.domain.Team
import com.gaastats.domain.StatisticType
import com.gaastats.domain.Match
import scala.collection.JavaConversions._

object StatisticDao extends BaseDao[Statistic]{
	
    def retrieveAllStatistics(statisticType: StatisticType, team: Team, matchInProgress: Match): List[Statistic] = {
        // We need to use a Java map here as there are no conversions from Scala map to Java
        val parameters = new java.util.HashMap[String, Object]()
        val statisticTypeID: java.lang.Integer = statisticType.id
        parameters.put("statisticType_id", statisticTypeID)
        parameters.put("team_id", team.name)
        val matchInProgressID: java.lang.Integer = matchInProgress.id
        parameters.put("matchOccurred_id", matchInProgressID)
        parameters.put("isDeleted", java.lang.Boolean.FALSE)
        getDao().queryForFieldValues(parameters).toList
    }
}