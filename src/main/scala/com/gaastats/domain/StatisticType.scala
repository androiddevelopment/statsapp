package com.gaastats.domain

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import org.apache.commons.lang.builder.HashCodeBuilder

object StatisticType {
    def apply(name: String, childNames: List[StatisticType]): StatisticType = {
        val statisticType = new StatisticType
        statisticType.name = name
        statisticType.childNames = childNames
        return statisticType
    }
    
    def apply(name: String, viewID: Int): StatisticType = {
        val statisticType = new StatisticType
        statisticType.name = name
        statisticType.viewID = viewID
        return statisticType
    }
}

class StatisticType {
	@DatabaseField var name: String = null
	@ForeignCollectionField var childNames: java.util.List[StatisticType] = null
	var viewID = 0
	
	def types: Array[CharSequence] = {
	    def statisticTypes = ListBuffer[CharSequence]()
	    for(childName <- childNames) statisticTypes += childName.name
	    statisticTypes.toArray
	}
	
	def typeAtIndex(index: Int): StatisticType = {
	    childNames.get(index)
	}
	
	override def hashCode: Int = new HashCodeBuilder().append(name).toHashCode()
}