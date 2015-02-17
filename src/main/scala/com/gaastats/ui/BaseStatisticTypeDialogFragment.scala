package com.gaastats.ui

import android.app.{AlertDialog, Dialog, DialogFragment}
import android.content.DialogInterface
import android.os.Bundle
import com.gaastats.activity.MatchCentreActivity
import com.gaastats.activity.service.MatchStatsService
import com.gaastats.dao.StatisticTypeDao
import com.gaastats.domain.enums.TeamType
import com.gaastats.ui.helper.DialogFragmentHelper

/**
 * TODO Could potentially create this as a singleton, as we will only ever have one dialog open at a time *
 */
class BaseStatisticTypeDialogFragment extends DialogFragment {
    var statisticName: String = null

    def this(statisticName: String) = {
        this
        this.statisticName = statisticName
    }
    
    def createDialog(statisticTypeName: String, teamType: TeamType): Dialog = {
        val statisticOptions = StatisticTypeDao.retrieveStatisticTypeHierarchy(statisticTypeName)
        val builder = new AlertDialog.Builder(getActivity())
        builder.setItems(statisticOptions.types, new DialogInterface.OnClickListener() {
            def onClick(dialog: DialogInterface, which: Int) {
                val statisticType = statisticOptions.typeAtIndex(which)
                if (statisticType.childStatistics.isEmpty) {
                  MatchStatsService.createNewStatistic(statisticType, teamType)
                } else {
                  DialogFragmentHelper.showFragment(new BaseStatisticTypeDialogFragment(statisticType.name), MatchCentreActivity.StatisticLauncherDialog, teamType.layoutID)
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