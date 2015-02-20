package com.gaastats.domain

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

object Team{
    def apply(name: String): Team = {
        val team = new Team
        team.name = name
        return team        
    }
}

@DatabaseTable
class Team() {
    @DatabaseField(id = true) var name: String = null

    override def toString = name
}