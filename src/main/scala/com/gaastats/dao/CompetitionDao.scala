package com.gaastats.dao

import scala.collection.JavaConversions._
import com.gaastats.dao.helper.DatabaseHelper
import com.gaastats.domain.Competition
import com.j256.ormlite.dao.Dao
import com.google.inject.Singleton
import com.google.inject.Inject
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import java.util.ArrayList

@Singleton
class CompetitionDao extends BaseDao[Competition] with NameRetrieval[Competition] {
  
  def retrieveByName(name: String): Competition = retrieveByPropertyValue("name", name)  
}