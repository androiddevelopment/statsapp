package com.gaastats.activity.service

import com.google.inject.Singleton
import com.gaastats.domain.Match
import com.gaastats.domain.enums.TeamType
import com.gaastats.domain.StatisticType
import com.gaastats.dao.StatisticDao
import com.google.inject.Inject
import com.gaastats.domain.Team
import com.gaastats.domain.Statistic
import com.gaastats.domain.Statistic
import scala.collection.mutable.Stack
import com.gaastats.util.ResourceHelper
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.dao.StatisticTypeDao
import scala.collection.JavaConversions._
import com.gaastats.dao.MatchDao
import com.gaastats.util.Format

@Singleton
class MatchStatsService {
    @Inject
    var statisticDao: StatisticDao = null
    @Inject
    var statisticTypeDao: StatisticTypeDao = null
    @Inject
    var resourceHelper: ResourceHelper = null
    private var matchInProgress: Match = null
    var undoStack: Stack[Statistic] = Stack()

    def setMatchInProgress(matchInProgress: Match) {
        this.matchInProgress = matchInProgress
    }

    def createNewStatistic(statisticType: StatisticType, teamType: TeamType) {
        var statistic = Statistic(matchInProgress, teamType.getTeam(matchInProgress), matchInProgress.matchTime, statisticType)
        undoStack.push(statistic)
        saveAndUpdateViews(statistic, teamType)
    }

    def undoLast() {
        if (!undoStack.isEmpty) {
            var lastStatistic = undoStack.pop
            lastStatistic.markDeleted
            for (teamType <- TeamType.allTypes) saveAndUpdateViews(lastStatistic, teamType)
        }
    }

    def retrieveStatisticSum(statisticName: String, teamType: TeamType): String = {
        val statistic = statisticTypeDao.retrieveStatisticTypeHierarchy(statisticName)
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
        var statistics: List[Statistic] = statisticDao.retrieveAllStatistics(statisticType, team, matchInProgress)
        statistics.size
    }

    private def saveAndUpdateViews(statistic: Statistic, teamType: TeamType) {
        statisticDao.save(statistic)
        resourceHelper.getActivity().asInstanceOf[MatchCentreActivity].refreshTeamStatisticViews(teamType)
    }

}