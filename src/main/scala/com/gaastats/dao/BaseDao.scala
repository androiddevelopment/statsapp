package com.gaastats.dao

import com.gaastats.dao.helper.DatabaseUtils
import com.j256.ormlite.dao.Dao

abstract class BaseDao[T: ClassManifest] {

    protected def retrieveByPropertyValue(propertyName: String, propertyValue: String): java.util.List[T] = {
        getDao().queryForEq(propertyName, propertyValue)
    }

    def save(objectToSave: T) = getDao().createOrUpdate(objectToSave)

    def delete(objectToDelete: T) = getDao().delete(objectToDelete)

    protected def getDao(): Dao[T, String] = {
        DatabaseUtils.getDatabaseHelper.getDao(classManifest[T].erasure)
    }
}