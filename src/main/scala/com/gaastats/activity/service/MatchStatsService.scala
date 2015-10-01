package com.gaastats.activity.service

import com.gaastats.activity.MatchCentreActivity
import com.gaastats.dao.{StatisticDao, StatisticTypeDao}
import com.gaastats.domain.enums.TeamType
import com.gaastats.domain.{Match, Statistic, StatisticType, Team}
import com.gaastats.util.{Format, ResourceHelper}

import scala.collection.mutable.Stack

object MatchStatsService {
    var matchInProgress: Match = null
    var undoStack: Stack[Statistic] = Stack()

    def createNewStatistic(statisticType: StatisticType, teamType: TeamType) {
        val statistic = Statistic(matchInProgress, teamType.getTeam(matchInProgress), matchInProgress.matchTime, statisticType)
        undoStack.push(statistic)
        saveAndUpdateViews(statistic)(teamType)
    }

    def undoLast() {
        if (!undoStack.isEmpty) {
            val lastStatistic = undoStack.pop
            lastStatistic.markDeleted
            TeamType.forAllTeamTypes(saveAndUpdateViews(lastStatistic)_)
        }
    }

    def retrieveStatisticSum(statisticName: String, teamType: TeamType): String = {
        val statistic = StatisticTypeDao.retrieveStatisticTypeHierarchy(statisticName)
        val childStatistics = statistic.childStatistics
        var statisticsSize = 0
        if (childStatistics == Nil) {
            statisticsSize += retrieveSum(statistic, teamType.getTeam(matchInProgress))
        } else {
            for (childStatistic <- childStatistics) statisticsSize += retrieveSum(childStatistic, teamType.getTeam(matchInProgress))
        }
        Format.formatInteger(statisticsSize)
    }

    private def retrieveSum(statisticType: StatisticType, team: Team): Int = {
        val statistics = StatisticDao.retrieveAllStatistics(statisticType, team, matchInProgress)
        statistics.size
    }

    private def saveAndUpdateViews(statistic: Statistic)( teamType: TeamType) {
      StatisticDao.save(statistic)
        MatchStatsViewsService.refreshTeamStatisticViews(teamType)
    }
    
}