package com.gaastats.activity.service

import android.os.CountDownTimer
import com.gaastats.domain.Match
import com.gaastats.domain.enums.MatchStage
import org.joda.time.DateTime
import android.widget.TextView

class MatchTimer(secondsRemaining: Int, var minutesElapsed: Int, var secondsElapsed: Int, matchService: MatchTimerService) extends CountDownTimer(secondsRemaining * 1000, 1 * 1000) {
    
    override def onFinish {
    	matchService.endHalf
    }

    override def onTick(millisUntilFinished: Long) {
        secondsElapsed += 1
        if (secondsElapsed == 60) {
            secondsElapsed = 0
            minutesElapsed += 1
        }
        
    }
}