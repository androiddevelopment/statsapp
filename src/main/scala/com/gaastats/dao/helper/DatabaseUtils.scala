package com.gaastats.dao.helper

import com.gaastats.util.ResourceHelper.application
/**
 * Created by David on 2/16/2015.
 */
object DatabaseUtils {
  val databaseUtilsWraper: DatabaseUtilsWrapper = new DatabaseUtilsWrapper

  def getDatabaseHelper() = {
    databaseUtilsWraper.getDatabaseHelper(application)
  }

  def releaseHelper() {
    databaseUtilsWraper.releaseHelper
  }
}
