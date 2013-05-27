package com.gaastats.dao.helper

import scala.collection.JavaConversions._
import com.gaastats.domain.Competition
import com.gaastats.domain.Match
import com.gaastats.domain.Statistic
import com.gaastats.domain.StatisticType
import com.gaastats.domain.Team
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import scala.collection.mutable.ListBuffer

class DatabaseHelper(context: Context) extends OrmLiteSqliteOpenHelper(context, "GaaStatsApp.db", null, 1) {
    val tables = List(classOf[Match], classOf[Competition], classOf[Team], classOf[Statistic], classOf[StatisticType])

    override def onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        for (table <- tables) TableUtils.createTable(connectionSource, table)
    }

    override def onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        for (table <- tables.reverse) TableUtils.dropTable(connectionSource, table, true)
        onCreate(database, connectionSource)
    }
    
    def executeQuery(query: String, arguments: List[String]) = {
        val columnValuesList : ListBuffer[Map[String, Any]] = ListBuffer()
        var cursor = getReadableDatabase.rawQuery(query, arguments.toArray)
        while (cursor.moveToNext) {      
            val columnValues = scala.collection.mutable.Map[String, Any]()        
            for(i <- 0 to cursor.getColumnNames().length - 1) {
                var column = cursor.getColumnName(i)
                columnValues(column) = cursor.getString(i)
            }
            columnValuesList += columnValues.toMap
        } 
        columnValuesList.toList
    }
}