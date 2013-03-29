package com.gaastats.util;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.view.View;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ResourceHelper {
	@Inject
	private Provider<Application> application;
	private Activity activity;
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public View findViewById(int viewID) {
		return activity.findViewById(viewID);
	}
	
	public Context getContext() {
		return application.get();
	}
	
	public FragmentManager getFragmentManager() {
		return activity.getFragmentManager();
	}
}
