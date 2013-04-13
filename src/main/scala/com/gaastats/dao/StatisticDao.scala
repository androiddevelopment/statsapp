package com.gaastats.dao

import com.gaastats.domain.Statistic
import com.gaastats.domain.Team
import com.gaastats.domain.StatisticType
import com.gaastats.domain.Match
import scala.collection.JavaConversions._

class StatisticDao extends BaseDao[Statistic]{
	
    def retrieveAllStatistics(statisticType: StatisticType, team: Team, matchInProgress: Match): List[Statistic] = {
        val parameters = scala.collection.mutable.Map[String, Object]("statisticType" -> statisticType, "team" -> Team, "matchInProgress" -> matchInProgress, "isDeleted" -> Boolean)
        getDao().queryForFieldValues(parameters).toList
    }
}