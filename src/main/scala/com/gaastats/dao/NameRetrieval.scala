package com.gaastats.dao

import scala.collection.JavaConversions._

trait NameRetrieval[T <: { def name: String }] { self: BaseDao[T] =>
  def retrieveAllNames = {
    val list: java.util.List[_ <: T] = getDao.queryForAll
    list.map(_.name)    
  }
}