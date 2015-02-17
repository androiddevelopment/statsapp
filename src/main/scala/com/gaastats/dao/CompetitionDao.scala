package com.gaastats.dao

import com.gaastats.domain.Competition

import scala.collection.JavaConversions._

object CompetitionDao extends BaseDao[Competition] with NameRetrieval[Competition] {
  
  def retrieveByName(name: String): Competition = retrieveByPropertyValue("name", name).head  
}