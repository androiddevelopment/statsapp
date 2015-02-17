package com.gaastats.ui.helper

import android.app.{DialogFragment, FragmentManager}
import android.os.Bundle

object DialogFragmentHelper {
    var fragmentManager: FragmentManager = null

    def showFragment(fragment: DialogFragment, fragmentName: String, parentViewID: Int) {
        fragment.setArguments(new Bundle)
        fragment.getArguments.putInt("parentViewID", parentViewID)
        fragment.show(fragmentManager, fragmentName)
    }

    def showFragment(fragment: DialogFragment, fragmentName: String) {
        fragment.show(fragmentManager, fragmentName)
    }
}
