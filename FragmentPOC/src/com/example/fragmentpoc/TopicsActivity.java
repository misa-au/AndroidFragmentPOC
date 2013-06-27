package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class TopicsActivity extends FragmentActivity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topics);
		
		// get the topics fragment and update
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			FragmentManager fManager = getSupportFragmentManager();
	    	TopicsFragment topicsFragment = (TopicsFragment)fManager.findFragmentById(R.id.topics_fragment);
	        topicsFragment.updateTopics(extras.getInt(TopicsFragment.TOPIC_CATEGORY));
		}
	}
}
