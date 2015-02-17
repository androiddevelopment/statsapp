package com.gaastats.util

import android.app.{Activity, Application, FragmentManager}
import android.view.View


/**
 * Created by David on 2/16/2015.
 */
object ResourceHelper {
  implicit var application: Application = null

  def setApplication(application: Application) {
    this.application = application
  }
}
