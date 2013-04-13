package com.gaastats.activity

import roboguice.activity.RoboFragmentActivity
import android.os.Bundle
import com.gaastats.R
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.gaastats.domain.enums.TeamType
import android.widget.TextView

object MatchCentreActivity {
    def GoalDialog = "goalDialog"
}

class MatchCentreActivity extends RoboFragmentActivity {
    @Inject
    var resourceHelper: ResourceHelper = null
    
	override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_centre)        
    }
    
    def refreshTeamScoreViews(teamType: TeamType.Type) {
        var teamScoreLayout = 0
        teamType match {
            case TeamType.Home => teamScoreLayout = R.id.homeTeamScore
            case TeamType.Away => teamScoreLayout = R.id.awayTeamScore
        }
        var goalsView = findViewById(teamScoreLayout).findViewById(R.id.teamGoals).asInstanceOf[TextView]
        var pointsView = findViewById(teamScoreLayout).findViewById(R.id.teamPoints).asInstanceOf[TextView]
        
    }
    
    override def onResume() {
        super.onResume
        resourceHelper.setActivity(this)
    }
}