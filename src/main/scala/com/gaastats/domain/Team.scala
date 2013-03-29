package com.gaastats.domain

import com.j256.ormlite.field.DatabaseField
import org.apache.commons.lang.builder.ToStringBuilder

object Team{
    def apply(name: String): Team = {
        val team = new Team
        team.name = name
        return team        
    }
}

class Team() {
    @DatabaseField(id = true) var name: String = null

    override def toString = ToStringBuilder.reflectionToString(this)
}