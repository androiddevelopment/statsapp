package com.gaastats.ui.helper

import android.app.DialogFragment
import android.os.Bundle
import com.google.inject.Injector
import com.gaastats.util.ResourceHelper
import com.google.inject.Inject
import com.google.inject.Singleton

@Singleton
class DialogFragmentHelper {
    @Inject
    var injector: Injector = null
    @Inject
    var resourceHelper: ResourceHelper = null

    def showFragment(fragment: DialogFragment, fragmentName: String, parentViewID: Int) {
        injector.injectMembers(fragment)
        fragment.setArguments(new Bundle)
        fragment.getArguments.putInt("parentViewID", parentViewID)
        fragment.show(resourceHelper.getFragmentManager(), fragmentName)
    }

    def showFragment(fragment: DialogFragment, fragmentName: String) {
        injector.injectMembers(fragment)
        fragment.show(resourceHelper.getFragmentManager(), fragmentName)
    }
}