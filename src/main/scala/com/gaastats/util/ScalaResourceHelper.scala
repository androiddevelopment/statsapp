package com.gaastats.util

import android.app.{FragmentManager, Activity}
import android.content.Context
import android.view.View

/**
 * Created by David on 2/16/2015.
 */
object ScalaResourceHelper {
  private var activity: Activity = null

  def getActivity: Activity = {
    return activity
  }

  def setActivity(activity: Activity) {
    this.activity = activity
  }

  def findViewById(viewID: Int): View = {
    return activity.findViewById(viewID)
  }

  def getFragmentManager: FragmentManager = {
    return activity.getFragmentManager
  }
}
