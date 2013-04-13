package com.gaastats.dao.helper

import com.gaastats.domain.Statistic
import com.gaastats.domain.StatisticType
import com.gaastats.R

object StatisticHelper {
    val defaultStatistics = Map[StatisticType, Int]()
    val goalFromPlay = createStatisticType("Goal from play", null, 0)
    val goalFromSetPlay = createStatisticType("Goal from penalty/free", null, 0)
    val goal = createStatisticType("Goal", List(goalFromPlay, goalFromSetPlay), R.id.teamGoals)
    val pointFromPlay = createStatisticType("Point from play", null, 0)
    val pointFromSetPlay = createStatisticType("Point from penalty/free", null, 0)
    val point = createStatisticType("Point", List(pointFromPlay, pointFromSetPlay), R.id.teamPoints)
    val wide = createStatisticType("Wide", null, 0)
    createStatisticType("Wide", List(wide), 0)
    val fortyFiveOrSixtyFive = createStatisticType("Forty five or sixty five", null, 0)
    val foul = createStatisticType("Foul", null, 0)
    val penalty = createStatisticType("Penalty", null, 0)
    val free = createStatisticType("Free", List(foul, penalty, fortyFiveOrSixtyFive), 0)

    def createStatisticType(name: String, childStatistics: List[StatisticType], labelID: Int): StatisticType = {
        val statisticType = StatisticType(name, childStatistics)
        defaultStatistics(statisticType) -> labelID
        statisticType
    }
}