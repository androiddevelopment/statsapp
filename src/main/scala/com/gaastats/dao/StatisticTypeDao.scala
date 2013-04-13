package com.gaastats.dao

import com.gaastats.domain.StatisticType

class StatisticTypeDao extends BaseDao {
    
	def retrieveByName(name: String): StatisticType = retrieveByPropertyValue("name", name)
	
	def retrieveParentStatistics: List[StatisticType] = retrieveByPropertyValue("childNames", null)
}