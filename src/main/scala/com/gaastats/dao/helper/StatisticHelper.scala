package com.gaastats.dao.helper

import com.gaastats.R
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.domain.StatisticType
import com.google.inject.Inject
import com.j256.ormlite.table.DatabaseTable
import com.google.inject.Singleton
import android.util.Log
import scala.collection.mutable.LinkedHashMap

object StatisticHelper {
    val defaultStatistics: scala.collection.mutable.Map[StatisticType, Int] = LinkedHashMap()
    private val goalFromPlay = createStatisticType("Goal from play", Nil, 0)
    private val goalFromSetPlay = createStatisticType("Goal from penalty/free", Nil, 0)
    private val goal = createStatisticType("Goal", List(goalFromPlay, goalFromSetPlay), R.id.teamGoals)
    private val pointFromPlay = createStatisticType("Point from play", Nil, 0)
    private val pointFromSetPlay = createStatisticType("Point from penalty/free", Nil, 0)
    private val point = createStatisticType("Point", List(pointFromPlay, pointFromSetPlay), R.id.teamPoints)
    private val wide = createStatisticType("Wide", Nil, 0)
    createStatisticType("Wide", List(wide), 0)
    private val fortyFiveOrSixtyFive = createStatisticType("Forty five or sixty five", Nil, 0)
    private val foul = createStatisticType("Foul", Nil, 0)
    private val penalty = createStatisticType("Penalty", Nil, 0)
    private val free = createStatisticType("Free", List(foul, penalty, fortyFiveOrSixtyFive), 0)

    def getStatisticType(statisticName: String): StatisticType = {
        defaultStatistics.keys.filter(key => key.name.equals(statisticName)).head
    }

    def getStatisticView(statisticName: String): Int = {
        defaultStatistics(getStatisticType(statisticName))
    }

    private def createStatisticType(name: String, childStatistics: List[StatisticType], labelID: Int): StatisticType = {
        var statisticType: StatisticType = null
        if (childStatistics.isEmpty) {
            statisticType = StatisticType(name)
            defaultStatistics(statisticType) = labelID
        } else {
            for (childStatistic <- childStatistics) {
                statisticType = StatisticType(name, childStatistic)
                defaultStatistics(statisticType) = labelID
            }
        }
        statisticType // We are only interested in the returned object if there is one element in the list
    }
}

class StatisticHelper {
    @Inject
    var statisticTypeDao: StatisticTypeDao = null

    def saveStatistics {
        if (!statisticTypeDao.statisticsAreLoaded) {
            for (statisticType <- StatisticHelper.defaultStatistics.keys) {
                Log.v(this.getClass().toString(), "Saving statistic type " + statisticType)
                statisticTypeDao.save(statisticType)
            }
        }
    }
}