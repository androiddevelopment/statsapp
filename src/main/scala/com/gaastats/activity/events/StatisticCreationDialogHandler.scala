package com.gaastats.activity.events

import com.google.inject.Inject
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.dao.StatisticDao
import com.gaastats.activity.service.MatchStatsService
import com.gaastats.domain.enums.TeamType

class StatisticCreationDialogHandler {
	@Inject
	var statisticTypeDao: StatisticTypeDao = null
	@Inject
	var matchStatsService: MatchStatsService = null
	
	def createStatistic(statisticName: String, statisticTypeIndex: Int, teamType: TeamType) {
	    var parentStatisticType = statisticTypeDao.retrieveStatisticTypeHierarchy(statisticName)
	    var statisticType = parentStatisticType.typeAtIndex(statisticTypeIndex)
	    matchStatsService.createNewStatistic(statisticType, teamType)
	}
	
}