package com.gaastats.ui

import com.gaastats.activity.events.StatisticTypeOnClickListener
import com.gaastats.dao.StatisticTypeDao
import com.google.inject.Inject
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface

class BaseStatisticTypeDialogFragment extends DialogFragment {
    @Inject
    var statisticTypeDao: StatisticTypeDao = null

    def createDialog(statisticType: String): Dialog = {
        var goalTypes = statisticTypeDao.retrieveByName(statisticType)
        var builder = new AlertDialog.Builder(getActivity())
        builder.setItems(goalTypes.types, new DialogInterface.OnClickListener() {
            def onClick(dialog: DialogInterface, which: Int) {
                
            }
        })
        builder.create()
    }

}