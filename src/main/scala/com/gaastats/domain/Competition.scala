package com.gaastats.domain

import org.apache.commons.lang.builder.ToStringBuilder
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

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

@DatabaseTable
class Competition {
    @DatabaseField(id = true) var name: String = null
    @DatabaseField var numberOfPlayers: java.lang.Integer = 0
    @DatabaseField var numberOfSubs: java.lang.Integer = 0
    @DatabaseField var lengthOfHalf: java.lang.Integer = 0

    
    override def toString = ToStringBuilder.reflectionToString(this)
}