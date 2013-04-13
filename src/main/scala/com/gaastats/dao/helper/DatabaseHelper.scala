package com.gaastats.dao.helper

import com.gaastats.domain.Competition
import scala.collection.JavaConversions._
import com.gaastats.domain.Match
import com.google.inject.Inject
import com.google.inject.Singleton
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.google.inject.Provider
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.gaastats.domain.Team
import com.gaastats.domain.Statistic
import com.gaastats.domain.StatisticType

class DatabaseHelper(context: Context) extends OrmLiteSqliteOpenHelper(context, "GaaStatsApp.db", null, 1) {
    val tables = List(classOf[Match], classOf[Competition], classOf[Team], classOf[Statistic], classOf[StatisticType])

    override def onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        for (table <- tables) TableUtils.createTable(connectionSource, table)
    }

    override def onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        for (table <- tables.reverse) TableUtils.dropTable(connectionSource, table, true)
        onCreate(database, connectionSource)
    }
    
    private def populateStatisticTypes {
        
    }
}