package com.gaastats.activity

import android.app.Activity
import android.os.Bundle
import com.gaastats.R
import com.gaastats.activity.events.SetupButtonOnClickListener
import com.gaastats.dao.helper.{DatabaseUtils, TestDataHelper}
import com.gaastats.util.ResourceHelper


object HomeScreenActivity {
    def CompetitionDialog = "competitionDialog"
    def MatchDialog = "matchDialog"
    def TeamDialog = "teamDialog"
}

class HomeScreenActivity extends Activity {

    override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        ResourceHelper.setApplication(getApplication)
        setContentView(R.layout.setup)
        SetupButtonOnClickListener.activity = this
        List(R.id.competitionSetupButton, R.id.matchSetupButton, R.id.teamSetupButton, R.id.viewMatchesButton).foreach(buttonID => findViewById(buttonID).setOnClickListener(SetupButtonOnClickListener))

    }

    override def onResume() {
        super.onResume
        // TestDataHelper.saveTestData
    }

    override def onDestroy() {
        super.onDestroy()
        DatabaseUtils.releaseHelper()
    }



}		