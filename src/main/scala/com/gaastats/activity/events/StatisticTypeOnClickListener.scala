package com.gaastats.activity.events


import android.view.View
import com.gaastats.R
import com.gaastats.activity.service.MatchTimerService
import com.google.inject.Inject
import com.gaastats.util.ResourceHelper
import android.content.DialogInterface.OnClickListener
import android.content.DialogInterface
import android.app.AlertDialog

class StatisticTypeOnClickListener extends OnClickListener {
	@Inject
	var resourceHelper: ResourceHelper = null
    
    override def onClick(dialog: DialogInterface, which: Int) {
//	    var parentViewID = dialog.asInstanceOf[AlertDialog].getParent().asInstanceOf[View].getId
//        view.getId() match {
//            case R.id.goalButton => 
//                
//            }
//        }
    }
}