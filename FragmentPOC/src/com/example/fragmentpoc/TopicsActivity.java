package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class TopicsActivity extends SherlockFragmentActivity {
    
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
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        	case android.R.id.home:
        		onBackPressed();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
		}
	}
}
