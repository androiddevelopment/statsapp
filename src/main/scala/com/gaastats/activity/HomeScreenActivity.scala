package com.gaastats.activity

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import com.gaastats.R
import android.widget.Button
import android.content.DialogInterface.OnClickListener
import android.content.DialogInterface
import android.app.Dialog
import com.gaastats.activity.events.CompetitionSetupDialogHandler
import android.view.ViewGroup
import com.gaastats.activity.events.MatchSetupDialogHandler
import com.gaastats.dao.helper.DatabaseUtilsWrapper
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.gaastats.dao.helper.DatabaseHelper
import roboguice.activity.RoboActivity
import com.google.inject.Inject
import com.gaastats.util.ResourceHelper
import com.gaastats.ui.CompetitionSetupDialogFragment
import android.util.Log
import roboguice.activity.RoboFragmentActivity
import android.util.Log
import android.util.Log
import android.util.Log
import android.util.Log
import roboguice.inject.InjectView
import android.widget.EditText
import com.gaastats.activity.events.SetupButtonOnClickListener

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
    var competitionSetupDialogFragment: CompetitionSetupDialogFragment = null
    @Inject
    var setupButtonOnClickListener: SetupButtonOnClickListener = null
    var databaseHelper: DatabaseHelper = null

    override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setup)
        databaseHelper = databaseUtils.getDatabaseHelper
        for (buttonID <- Array(R.id.competitionSetupButton, R.id.matchSetupButton, R.id.teamSetupButton)) findViewById(buttonID).setOnClickListener(setupButtonOnClickListener)
    }

    override def onStart() {
        super.onStart
        resourceHelper.setActivity(this)
    }

    override def onDestroy() {
        super.onDestroy()
        if (databaseHelper != null) {
            databaseUtils.releaseHelper()
            databaseHelper = null;
        }
    }

}		