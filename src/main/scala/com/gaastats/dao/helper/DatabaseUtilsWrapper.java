package com.gaastats.dao.helper;

import android.app.Application;
import com.j256.ormlite.android.apptools.OpenHelperManager;

class DatabaseUtilsWrapper {

	public DatabaseHelper getDatabaseHelper(Application application) {
		return OpenHelperManager.getHelper(application.getApplicationContext(), DatabaseHelper.class);
	}
	
	public void releaseHelper() {
		OpenHelperManager.releaseHelper();
	}
}
