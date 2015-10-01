package com.gaastats.domain.enums

import android.view.View

abstract class MatchStageEnum(val matchStageInt: Int, val halfOrFullTimeButtonText: String, val currentHalfLabelText: String, val next: MatchStageEnum, val product: Int, val viewVisibility: Int, val halfOrFullTimeButtonVisibility: Int) 

object MatchStageEnum {
	case object MatchNotStarted extends MatchStageEnum(0, "Start Match", "Match Not Started", FirstHalfInProgress, 0, View.INVISIBLE, View.VISIBLE)
	case object FirstHalfInProgress extends MatchStageEnum(1, "Half Time", "First Half", HalfTime, 0, View.VISIBLE, View.VISIBLE)
	case object HalfTime extends MatchStageEnum(2, "Start Second Half", "Half Time", SecondHalfInProgress, 1, View.INVISIBLE, View.VISIBLE)
	case object SecondHalfInProgress extends MatchStageEnum(3, "Full Time", "Second Half", FullTime, 0, View.VISIBLE, View.VISIBLE)
	case object FullTime extends MatchStageEnum(4, null, "Full Time", null, 2, View.INVISIBLE, View.INVISIBLE)

  def get(stageValue: Int) = {
    stageValue match {
      case 0 => MatchNotStarted
      case 1 => FirstHalfInProgress
      case 2 => HalfTime
      case 3 => SecondHalfInProgress
      case 4 => FullTime
      case _ => throw new IllegalArgumentException("Invalid match stage ID specified")
    }
  }
}

