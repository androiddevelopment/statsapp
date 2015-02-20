package com.gaastats.domain

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder}

import scala.collection.JavaConversions.seqAsJavaList
import scala.collection.mutable.ListBuffer

object StatisticType {
    def apply(name: String, childStatistic: StatisticType): StatisticType = {
        val statisticType = new StatisticType
        statisticType.name = name
        statisticType.childStatistic = childStatistic
        return statisticType
    }
    
    def apply(name: String): StatisticType = {
        val statisticType = new StatisticType
        statisticType.name = name
        return statisticType
    }
    
    def apply(name: String, id: Int): StatisticType = {
        val statisticType = new StatisticType
        statisticType.id = id
        statisticType.name = name
        return statisticType
    }
    
    def apply(id: Int, name: String, childStatistic: StatisticType): StatisticType = {
        val statisticType = new StatisticType
        statisticType.id = id
        statisticType.name = name
        statisticType.childStatistic = childStatistic
        return statisticType
    }
    
    def apply(name: String, childStatistics: List[StatisticType]): StatisticType = {
         val statisticType = new StatisticType
         statisticType.name = name
         statisticType.childStatistics = childStatistics
         return statisticType
    }
}

@DatabaseTable
class StatisticType {
    @DatabaseField(generatedId = true) var id: Int = 0
    @DatabaseField(uniqueCombo = true) var name: String = null
    @DatabaseField(foreign = true, uniqueCombo = true) var childStatistic: StatisticType = null
    var viewID = 0
    var childStatistics: List[StatisticType] = Nil
    
    def types: Array[CharSequence] = {
        val statisticTypes = ListBuffer[CharSequence]()
        for (childStatistic <- childStatistics) statisticTypes += childStatistic.name
        statisticTypes.toArray
    }

    def typeAtIndex(index: Int): StatisticType = {
        childStatistics.toList.get(index)
    }

    override def hashCode: Int = new HashCodeBuilder().append(name).append(childStatistic).toHashCode()

    override def equals(that: Any) = {
        if (that.isInstanceOf[StatisticType]) {
            val other = that.asInstanceOf[StatisticType]
            new EqualsBuilder().append(name, other.name).append(childStatistic, other.childStatistic).isEquals()
        } else {
            false
        }
    }

    override def toString: String = name + "->" + childStatistics
}