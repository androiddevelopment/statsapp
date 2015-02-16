package com.gaastats.ui

import com.gaastats.activity.service.MatchStatsService
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.domain.enums.TeamType
import com.google.inject.Inject
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import com.gaastats.ui.helper.DialogFragmentHelper
import com.gaastats.activity.MatchCentreActivity

/**
 * TODO Could potentially create this as a singleton, as we will only ever have one dialog open at a time *
 */
class BaseStatisticTypeDialogFragment extends DialogFragment {
    @Inject
    var dialogFragmentHelper: DialogFragmentHelper = null
    @Inject
    var statisticTypeDao: StatisticTypeDao = null
    @Inject
    var matchStatsService: MatchStatsService = null
    var statisticName: String = null

    def this(statisticName: String) = {
        this
        this.statisticName = statisticName
    }
    
    def createDialog(statisticTypeName: String, teamType: TeamType): Dialog = {
        val statisticOptions = statisticTypeDao.retrieveStatisticTypeHierarchy(statisticTypeName)
        val builder = new AlertDialog.Builder(getActivity())
        builder.setItems(statisticOptions.types, new DialogInterface.OnClickListener() {
            def onClick(dialog: DialogInterface, which: Int) {
                val statisticType = statisticOptions.typeAtIndex(which)
                if (statisticType.childStatistics.isEmpty) {
                  matchStatsService.createNewStatistic(statisticType, teamType)
                } else {
                  dialogFragmentHelper.showFragment(new BaseStatisticTypeDialogFragment(statisticType.name), MatchCentreActivity.StatisticLauncherDialog, teamType.layoutID)
                }
                dismiss
            }
        })

        builder.create()
    }

    override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
        val parentViewID = getArguments.get("parentViewID").asInstanceOf[Int]
        createDialog(statisticName, TeamType.getTypeForLayoutID(parentViewID))
    }

    override def onPause {
        super.onPause
        getArguments().remove("parentViewID")
    }

}