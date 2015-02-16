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
    createStatisticType("Goal", List(goalFromPlay, goalFromSetPlay), R.id.teamGoals)
    private val pointFromPlay = createStatisticType("Point from play", Nil, 0)
    private val pointFromSetPlay = createStatisticType("Point from penalty/free", Nil, 0)
    createStatisticType("Point", List(pointFromPlay, pointFromSetPlay), R.id.teamPoints)
    private val wide = createStatisticType("Wide", Nil, 0)
    private val save = createStatisticType("Save", Nil, 0)
    private val post = createStatisticType("Post", Nil, 0)
    createStatisticType("Miss", List(wide, save, post), R.id.teamMisses)
    private val fortyFiveOrSixtyFive = createStatisticType("Forty five or sixty five", Nil, 0)
    private val foul = createStatisticType("Foul", Nil, 0)
    private val penalty = createStatisticType("Penalty", Nil, 0)
    createStatisticType("Free", List(foul, penalty, fortyFiveOrSixtyFive), 0)
    private val yellowCard = createStatisticType("Yellow Card", Nil, R.id.teamYellowCards)
    private val redCard = createStatisticType("Red Card", Nil, R.id.teamRedCards)
    private val blackCard = createStatisticType("Black Card", Nil, R.id.teamBlackCards)
    createStatisticType("Card", List(redCard, yellowCard, blackCard), 0)
    private val kickoutWonClean = createStatisticType("Kickout won clean", Nil, 0)
    private val kickoutWonOnBreak = createStatisticType("Kickout won on break", Nil, 0)
    private val homeKickout = createStatisticType("Home kickout", List(kickoutWonClean, kickoutWonOnBreak, foul), 0)
    private val awayKickout = createStatisticType("Away kickout", List(kickoutWonClean, kickoutWonOnBreak, foul), 0)
    createStatisticType("Kickout",  List(homeKickout, awayKickout), R.id.teamKickouts)
    
    
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