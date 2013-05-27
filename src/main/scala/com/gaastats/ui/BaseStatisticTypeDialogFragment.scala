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

class BaseStatisticTypeDialogFragment extends DialogFragment {
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
        var goalTypes = statisticTypeDao.retrieveStatisticTypeHierarchy(statisticTypeName)
        var builder = new AlertDialog.Builder(getActivity())
        builder.setItems(goalTypes.types, new DialogInterface.OnClickListener() {
            def onClick(dialog: DialogInterface, which: Int) {
                val statisticType = goalTypes.typeAtIndex(which)
                matchStatsService.createNewStatistic(statisticType, teamType)
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