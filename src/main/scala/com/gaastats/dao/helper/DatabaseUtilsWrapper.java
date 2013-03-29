package com.gaastats.dao.helper;

import android.app.Application;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.android.apptools.OpenHelperManager;

@Singleton
public class DatabaseUtilsWrapper {
	@Inject
	private Provider<Application> application;
	
	public DatabaseHelper getDatabaseHelper() {
		return OpenHelperManager.getHelper(application.get(), DatabaseHelper.class);
	}
	
	public void releaseHelper() {
		OpenHelperManager.releaseHelper();
	}
}
