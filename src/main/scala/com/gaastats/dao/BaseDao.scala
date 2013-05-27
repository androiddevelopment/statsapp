package com.gaastats.dao

import com.gaastats.dao.helper.DatabaseHelper
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import com.google.inject.Inject
import com.j256.ormlite.dao.Dao

abstract class BaseDao[T: ClassManifest] {
    @Inject
    var databaseUtils: DatabaseUtilsWrapper = null

    protected def retrieveByPropertyValue(propertyName: String, propertyValue: String): java.util.List[T] = {
        getDao().queryForEq(propertyName, propertyValue)
    }

    def save(objectToSave: T) = getDao().createOrUpdate(objectToSave)

    protected def getDao(): Dao[T, String] = {
        val databaseHelper: DatabaseHelper = databaseUtils.getDatabaseHelper
        val dao: Dao[T, String] = databaseHelper.getDao(classManifest[T].erasure)
        return dao
    }
}