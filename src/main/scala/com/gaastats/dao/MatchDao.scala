package com.gaastats.dao

import com.gaastats.domain.Match
import com.gaastats.util.Converters._
import com.google.inject.Singleton



@Singleton
class MatchDao extends BaseDao[Match] {
    def retrieveMatchByID(matchID: Int): Match = getDao.queryForEq("id", matchID).head

    def retrieveAllMatches(): List[Match] = getDao().queryForAll()
}