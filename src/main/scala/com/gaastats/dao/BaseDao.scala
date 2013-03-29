package com.gaastats.dao

import com.j256.ormlite.dao.Dao
import com.gaastats.dao.helper.DatabaseHelper
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import com.google.inject.Inject
import scala.collection.JavaConversions._
import java.util.ArrayList

abstract class BaseDao[T: ClassManifest] {
	@Inject
	var databaseUtils: DatabaseUtilsWrapper = null
	
	protected def retrieveByPropertyValue(propertyName: String, propertyValue: String): T = {
	  getDao().queryForEq(propertyName, propertyValue).head
	}
	
	def save(objectToSave: T) = getDao().createOrUpdate(objectToSave)
	
	protected def getDao() : Dao[T, String] = {
	    val databaseHelper: DatabaseHelper = databaseUtils.getDatabaseHelper
	    val dao: Dao[T, String] = databaseHelper.getDao(classManifest[T].erasure)
    return dao
  }
}