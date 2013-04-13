package com.gaastats.dao

import scala.collection.JavaConversions._
import com.gaastats.dao.helper.DatabaseHelper
import com.gaastats.domain.Competition
import com.j256.ormlite.dao.Dao
import com.google.inject.Singleton
import com.google.inject.Inject
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import java.util.ArrayList
import com.gaastats.domain.Team

@Singleton
class TeamDao extends BaseDao[Team] with NameRetrieval[Team]{
      
  def retrieveByName(name: String): Team = retrieveByPropertyValue("name", name)
  
}