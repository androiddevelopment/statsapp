package com.gaastats.domain.enums

import android.view.View

abstract class MatchStageEnum(val matchStageInt: Int, val halfOrFullTimeButtonText: String, val currentHalfLabelText: String, val next: MatchStageEnum, val product: Int, val pauseOrResumeTimerVisibility: Int, val halfOrFullTimeButtonVisibility: Int) 

object MatchStageEnum {
	case object MatchNotStarted extends MatchStageEnum(0, "Start Match", "Match Not Started", FirstHalfInProgress, 0, View.INVISIBLE, View.VISIBLE)
	case object FirstHalfInProgress extends MatchStageEnum(1, "Half Time", "First Half", HalfTime, 0, View.VISIBLE, View.VISIBLE)
	case object HalfTime extends MatchStageEnum(2, "Start Second Half", "Half Time", SecondHalfInProgress, 1, View.INVISIBLE, View.VISIBLE)
	case object SecondHalfInProgress extends MatchStageEnum(3, "Full Time", "Second Half", FullTime, 0, View.VISIBLE, View.VISIBLE)
	case object FullTime extends MatchStageEnum(4, null, "Full Time", null, 2, View.INVISIBLE, View.INVISIBLE)
}

