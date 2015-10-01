package com.gaastats.activity.events

import com.gaastats.activity.service.MatchStatsService
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.domain.enums.TeamType

object StatisticCreationDialogHandler {

	def createStatistic(statisticName: String, statisticTypeIndex: Int, teamType: TeamType) {
	    val parentStatisticType = StatisticTypeDao.retrieveStatisticTypeHierarchy(statisticName)
	    val statisticType = parentStatisticType.typeAtIndex(statisticTypeIndex)
	    MatchStatsService.createNewStatistic(statisticType, teamType)
	}
	
}