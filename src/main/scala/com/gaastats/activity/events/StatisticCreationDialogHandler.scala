package com.gaastats.activity.events

import com.gaastats.activity.service.MatchStatsService
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.domain.enums.TeamType

object StatisticCreationDialogHandler {

	def createStatistic(statisticName: String, statisticTypeIndex: Int, teamType: TeamType) {
	    var parentStatisticType = StatisticTypeDao.retrieveStatisticTypeHierarchy(statisticName)
	    var statisticType = parentStatisticType.typeAtIndex(statisticTypeIndex)
	    MatchStatsService.createNewStatistic(statisticType, teamType)
	}
	
}