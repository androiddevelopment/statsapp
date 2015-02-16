package com.gaastats.activity.service

import android.os.CountDownTimer

class MatchTimer(secondsRemaining: Int, var minutesElapsed: Int, var secondsElapsed: Int, matchTimerService: MatchTimerService) extends CountDownTimer(secondsRemaining * 1000, 1 * 1000) {
    
    override def onFinish {
    	matchTimerService.endHalf
    }

    override def onTick(millisUntilFinished: Long) {
        secondsElapsed += 1
        if (secondsElapsed == 60) {
            secondsElapsed = 0
            minutesElapsed += 1
        }
        matchTimerService.updateMatchTime(minutesElapsed, secondsElapsed)
        
    }
}