package com.gaastats.dao

import com.gaastats.domain.StatisticType
import scala.collection.JavaConversions._
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import com.google.inject.Inject
import scala.collection.mutable.ListBuffer

class StatisticTypeDao extends BaseDao[StatisticType] {
    @Inject
    val databaseUtilsWrapper: DatabaseUtilsWrapper = null
    val statisticTypeQueryByName = "SELECT * FROM STATISTICTYPE st WHERE st.name = ?"
    val statisticTypeQueryByID = "SELECT * FROM STATISTICTYPE st WHERE st.id = ?"

    def statisticsAreLoaded = {
        val statisticsAreLoaded = getDao.iterator.hasNext
        statisticsAreLoaded
    }
        
    def retrieveStatisticTypeHierarchy(name: String): StatisticType = {
        val statisticTypeResultSet = databaseUtilsWrapper.getDatabaseHelper().executeQuery(statisticTypeQueryByName, List(name))

        createHierarchy(statisticTypeResultSet)
    }

    private def createHierarchy(statisticTypeResultSet: List[Map[String, Any]]): StatisticType = {
        val parentStatistic = createStatisticType(statisticTypeResultSet.head)
        val childStatistics = ListBuffer[StatisticType]()
        
        for (statisticTypeRow <- statisticTypeResultSet) {
            val childStatisticID = statisticTypeRow("childStatistic_id").asInstanceOf[String]
            if (childStatisticID != null) {
                val childStatisticResultSet = databaseUtilsWrapper.getDatabaseHelper().executeQuery(statisticTypeQueryByID, List(childStatisticID))
                val childStatistic = createHierarchy(childStatisticResultSet)
                childStatistics += childStatistic
            }
        }
        parentStatistic.childStatistics = childStatistics.toList
        return parentStatistic
    }

    private def createStatisticType(statisticAttributes: Map[String, Any]): StatisticType = {
        createStatisticType(getStatisticTypeName(statisticAttributes), getStatisticTypeID(statisticAttributes))
    }

    private def createStatisticType(name: String, id: Int) = {
        StatisticType(name, id)
    }
    
    private def getStatisticTypeName(statisticAttributes: Map[String, Any]) = statisticAttributes("name").asInstanceOf[String]
    
    private def getStatisticTypeID(statisticAttributes: Map[String, Any]) = statisticAttributes("id").asInstanceOf[String].toInt 
}