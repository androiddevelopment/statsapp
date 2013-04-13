package com.gaastats.domain.enums

object TimerStatus extends Enumeration {
	type Status = Value
	val InProgress = Value(1, "Pause Timer")
	val Paused = Value(-1, "Resume Timer")	
}

