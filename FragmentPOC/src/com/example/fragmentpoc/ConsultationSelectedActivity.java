package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ConsultationSelectedActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultation_selected);
		
		// get the consultation fragment and update
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			FragmentManager fManager = getSupportFragmentManager();
			ConsultationFragment consultationFragment = (ConsultationFragment)fManager.findFragmentById(R.id.consultation_fragment);
			consultationFragment.updateConsultation(extras.getString(ConsultationFragment.CONSULTATION_NAME));
		}
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu; this adds items to the action bar if large screen
		if (((POCApp) getApplicationContext()).isLargeLayout())
		{
			getSupportMenuInflater().inflate(R.menu.main_large, menu);
			return true;
		}
		else
		{
			return true;
		}
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
