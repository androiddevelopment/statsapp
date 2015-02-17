package com.gaastats.dao

import com.gaastats.domain.Team

import scala.collection.JavaConversions._

object TeamDao extends BaseDao[Team] with NameRetrieval[Team]{
      
  def retrieveByName(name: String): Team = retrieveByPropertyValue("name", name).head
  
}