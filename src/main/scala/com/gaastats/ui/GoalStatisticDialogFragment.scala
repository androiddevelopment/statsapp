package com.gaastats.ui;

import android.app.Dialog
import android.os.Bundle
import com.gaastats.domain.enums.TeamType
import android.app.FragmentManager
import com.gaastats.domain.enums.TeamType

class GoalStatisticDialogFragment extends BaseStatisticTypeDialogFragment {
	override def onCreateDialog(savedInstanceState: Bundle): Dialog = {
        createDialog("Goal")
    }	
}
