package com.gaastats.ui

import android.app.Dialog
import android.app.AlertDialog
import android.view.View
import android.app.DialogFragment
import com.gaastats.R
import android.content.DialogInterface.OnClickListener
import android.content.DialogInterface

abstract class BaseSetupDialogFragment extends DialogFragment{
  
	protected def createDialog(dialogView: View, messageID: Int, onClickFunction: Unit): Dialog = {
    val builder = new AlertDialog.Builder(getActivity())
    builder.setMessage(getString(messageID))
      .setCancelable(false)
      .setView(dialogView)
      .setNegativeButton(R.string.cancel, new OnClickListener {
        def onClick(dialog: DialogInterface, which: Int) {
          dismiss()
        }
      })
      .setPositiveButton(R.string.create, new OnClickListener {
        def onClick(dialog: DialogInterface, which: Int) {
          onClickFunction        }
      })

    builder.create()
  }
}