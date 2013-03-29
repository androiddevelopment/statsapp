package com.gaastats.domain

import org.apache.commons.lang.builder.ToStringBuilder

import com.j256.ormlite.field.DatabaseField


class Competition(@DatabaseField(id = true) var name: String, @DatabaseField var numberOfPlayers: Int, @DatabaseField var numberOfSubs: Int, @DatabaseField var lengthOfHalf: Int) {
	
  def this() = this(null, 0, 0, 0)
  
  override def toString = ToStringBuilder.reflectionToString(this)
}