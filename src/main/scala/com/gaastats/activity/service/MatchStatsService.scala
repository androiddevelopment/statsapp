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

@Singleton
class MatchStatsService {
    var matchInProgress: Match = null
    var statisticDao: StatisticDao = null
    var statisticTypeDao: StatisticTypeDao = null
    var undoStack: Stack[Statistic] = Stack()
    @Inject
    var resourceHelper: ResourceHelper = null
    
    def createNewStatistic(statisticType: StatisticType, teamType: TeamType.Type) {
        var statistic = Statistic(matchInProgress, homeOrAwayTeam(teamType), matchInProgress.matchTime, statisticType)        
        undoStack.push(statistic)
    }

    def undoLast() {
        var lastStatistic = undoStack.pop
        lastStatistic.markDeleted        
    }
    
    def retrieveStatisticSum(statisticType: StatisticType, teamType: TeamType.Type): Int = {
        val statistic = statisticTypeDao.retrieveByName(statisticType.name)
        val childStatistics = statistic.childNames
        var statisticsSize = 0
        if(childStatistics == Nil) {
            statisticsSize += retrieveSum(statistic, homeOrAwayTeam(teamType))
        } else {
            for(childStatistic <- childStatistics)  statisticsSize += retrieveSum(childStatistic, homeOrAwayTeam(teamType))
        }
        statisticsSize
    }
    
    private def retrieveSum(statisticType: StatisticType, team: Team): Int = {
        var statistics: List[Statistic]  = statisticDao.retrieveAllStatistics(statisticType, team, matchInProgress)
        statistics.size
    }
    
    private def saveAndUpdateViews(statistic: Statistic, teamType: TeamType.Type) {
        statisticDao.save(statistic)
        resourceHelper.getActivity().asInstanceOf[MatchCentreActivity].refreshTeamScoreViews(teamType)
    }
    
    private def homeOrAwayTeam(teamType: TeamType.Type): Team = {
        teamType match {
            case TeamType.Home => matchInProgress.homeTeam
            case TeamType.Away => matchInProgress.awayTeam
        }
    }
}