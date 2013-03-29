package com.gaastats.domain

import com.j256.ormlite.field.DatabaseField
import org.apache.commons.lang.builder.ToStringBuilder

class Team(@DatabaseField(index = true)var name: String) {

  override def toString = ToStringBuilder.reflectionToString(this)
}