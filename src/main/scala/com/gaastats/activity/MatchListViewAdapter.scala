package com.gaastats.activity

import android.app.Activity
import android.view.View.OnClickListener
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.{BaseAdapter, Button, TextView}
import com.gaastats.R
import com.gaastats.activity.service.ActivityStarterService
import com.gaastats.dao.MatchDao
import com.gaastats.domain.Match
import main.scala.com.gaastats.ui.DeleteMatchDialogFragment

/**
 * Created by David on 2/15/2015.
 */
class MatchListViewAdapter(var allMatches: List[Match], activity: Activity, view: View) extends BaseAdapter {

  override def getCount = {
    allMatches.size
  }

  override def getItemId(position: Int): Long = {
    position
  }

  override def getView(position: Int, view: View, parent: ViewGroup): View = {
    var updatedView: View = null;
    if (view == null) {
      val inflater = LayoutInflater.from(parent.getContext());
      updatedView = inflater.inflate(R.layout.match_list_individual_entry, parent, false);
    } else {
      updatedView = view
    }

    val textView = updatedView.findViewById(R.id.matchListIndividualMatchTextView).asInstanceOf[TextView]
    textView.setText("%s - %s v %s, %s".format(getItem(position).competition, getItem(position).homeTeam, getItem(position).awayTeam, getItem(position).dateOfMatch))
    val viewButton = updatedView.findViewById(R.id.viewIndividualMatchButton).asInstanceOf[Button]
    viewButton.setOnClickListener(new OnClickListener {
      override def onClick(v: View) {
        ActivityStarterService.startMatchCentreActivity(getItem(position))
      }
    })
    val deleteButton = updatedView.findViewById(R.id.deleteIndividualMatchButton).asInstanceOf[Button]
    deleteButton.setOnClickListener(new OnClickListener {
      override def onClick(v: View) {
        new DeleteMatchDialogFragment(getItem(position), MatchListViewAdapter.this).show(activity.getFragmentManager, "Delete Match")
      }
    })
    updatedView
  }

  override def getItem(position: Int): Match = {
    allMatches(position)
  }

  def deleteItem(matchToDelete: Match) {
    allMatches = allMatches.filterNot(_ == matchToDelete)
    MatchDao.delete(matchToDelete)
    this.notifyDataSetChanged()
  }
}
