package com.gaastats.domain.enums

object MatchStage extends Enumeration{
	type Stage = Value
	val MatchNotStarted = Value(0, "Start Match")
	val FirstHalfInProgress = Value(1, "Half Time")
	val HalfTime = Value(2, "Start Second Half")
	val SecondHalfInProgress = Value(3, "Full Time")
	val FullTime = Value(3, null)
}