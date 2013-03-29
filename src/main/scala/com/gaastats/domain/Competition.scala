package com.gaastats.domain

import org.apache.commons.lang.builder.ToStringBuilder

import com.j256.ormlite.field.DatabaseField

object Competition {
    def apply(name: String, numberOfPlayers: Int, numberOfSubs: Int, lengthOfHalf: Int): Competition = {
        var competition = new Competition()
        competition.name = name
        competition.numberOfPlayers = numberOfPlayers
        competition.numberOfSubs = numberOfSubs
        competition.lengthOfHalf = lengthOfHalf
        return competition
    }
}

class Competition {
    @DatabaseField(id = true) var name: String = null
    @DatabaseField var numberOfPlayers: Int = 0
    @DatabaseField var numberOfSubs: Int = 0
    @DatabaseField var lengthOfHalf: Int = 0

    
    override def toString = ToStringBuilder.reflectionToString(this)
}