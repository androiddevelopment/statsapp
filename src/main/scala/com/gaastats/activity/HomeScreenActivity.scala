package com.gaastats.activity

import android.content.{Context, Intent}
import android.os.Bundle
import com.gaastats.R
import com.gaastats.activity.events.SetupButtonOnClickListener
import com.gaastats.dao.helper.{DatabaseHelper, DatabaseUtilsWrapper, TestDataHelper}
import com.gaastats.domain.Match
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import roboguice.activity.RoboFragmentActivity

object HomeScreenActivity {
    def CompetitionDialog = "competitionDialog"
    def MatchDialog = "matchDialog"
    def TeamDialog = "teamDialog"
}

class HomeScreenActivity extends RoboFragmentActivity {
    @Inject
    var databaseUtils: DatabaseUtilsWrapper = null
    @Inject
    var resourceHelper: ResourceHelper = null
    @Inject
    var setupButtonOnClickListener: SetupButtonOnClickListener = null
    @Inject
    var testDataHelper: TestDataHelper = null
    
    var databaseHelper: DatabaseHelper = null

    override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setup)
        databaseHelper = databaseUtils.getDatabaseHelper
        List(R.id.competitionSetupButton, R.id.matchSetupButton, R.id.teamSetupButton, R.id.viewMatchesButton).foreach(buttonID => findViewById(buttonID).setOnClickListener(setupButtonOnClickListener))
    }

    override def onResume() {
        super.onResume
        resourceHelper.setActivity(this)
        testDataHelper.saveTestData
    }

    override def onDestroy() {
        super.onDestroy()
        if (databaseHelper != null) {
            databaseUtils.releaseHelper()
            databaseHelper = null;
        }
    }

    def startMatchCentreActivity(matchToStart: Match) = {
        val matchCentreActivity = new Intent(this.asInstanceOf[Context], classOf[MatchCentreActivity])
        matchCentreActivity.putExtra("matchID", matchToStart.id)
        startActivity(matchCentreActivity)
    }

    def startMatchListViewActivity() = {
      val matchListViewActivity = new Intent(this.asInstanceOf[Context], classOf[MatchListActivity])
      startActivity(matchListViewActivity)
    }

}		