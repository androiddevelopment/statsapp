package com.gaastats.domain

import org.apache.commons.lang.builder.ToStringBuilder

import com.j256.ormlite.field.DatabaseField

class Match(@DatabaseField(foreign = true) competition: Competition, @DatabaseField(foreign = true) homeTeam: Team, @DatabaseField(foreign = true) awayTeam: Team, @DatabaseField firstHalfTimeLeft: Integer, @DatabaseField secondHalfTimeLeft: Integer) {
 @DatabaseField(generatedId = true, index = true) var id: Int = 0
	
 def this() = this (null, null, null, null, null)
 
  override def toString = ToStringBuilder.reflectionToString(this)
}