package com.example.fragmentpoc;

import android.app.Application;
import android.content.res.Configuration;

public class POCApp extends Application {
	private boolean largeLayout;
	
	public boolean isLargeLayout() {
		return largeLayout;
	}
	
	public void onCreate() {
		largeLayout = (getResources().getConfiguration().screenLayout & 
				Configuration.SCREENLAYOUT_SIZE_MASK) >= 
		        Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
}
