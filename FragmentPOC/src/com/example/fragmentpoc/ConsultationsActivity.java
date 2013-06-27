package com.example.fragmentpoc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.fragmentpoc.ConsultationListFragment.OnConsultationSelectedListener;

public class ConsultationsActivity extends SherlockFragmentActivity
	implements OnConsultationSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        // if using large layout, force landscape orientation (otherwise, portrait)
        setRequestedOrientation(
        	((POCApp) getApplicationContext()).isLargeLayout() 
        		? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 
        				: ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.activity_consultations);
		
		// set this as the listener for saved consultation selection
		FragmentManager fManager = getSupportFragmentManager();
		ConsultationListFragment listFragment = (ConsultationListFragment)fManager.findFragmentById(R.id.consultation_list_fragment);
		listFragment.setOnConsultationSelectedListener(this);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu; this adds items to the action bar if large screen
		if (((POCApp) getApplicationContext()).isLargeLayout())
		{
			getSupportMenuInflater().inflate(R.menu.consultations_large, menu);
		}
		return true;
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

	@Override
	public void OnConsultationSelected(String consultationName) {
		// if we're on a large screen, replace whatever is in the 
        // right fragment container view with this fragment
        if (((POCApp) getApplicationContext()).isLargeLayout()) 
     	{
        	// get existing topic fragment, and create new if none
        	FragmentManager fManager = getSupportFragmentManager();
        	ConsultationFragment consultationFragment = (ConsultationFragment)fManager.findFragmentByTag("Consultation");
        	boolean fragmentIsNull = consultationFragment == null;
        	if (fragmentIsNull)
        	{
        		consultationFragment = new ConsultationFragment();
                Bundle args = new Bundle();
                args.putString(ConsultationFragment.CONSULTATION_NAME, consultationName);
                consultationFragment.setArguments(args);
                
                // add the transaction to the back stack so the user can navigate back
                FragmentTransaction transaction = fManager.beginTransaction();
        		transaction.replace(R.id.consultation_fragment_container, consultationFragment, "Consultation");
        		transaction.addToBackStack(null);

            	// commit the transaction
            	transaction.commit();
        	}
        	
        	// if there is an existing fragment, just update parameters
        	else
        	{
        		consultationFragment.updateConsultation(consultationName);
        	}
     	}
        
        // we're on a small screen, so create a new activity and pass consultation name
     	else
     	{
     		Intent myIntent = new Intent(ConsultationsActivity.this, ConsultationSelectedActivity.class);
     		myIntent.putExtra(ConsultationFragment.CONSULTATION_NAME, consultationName);
     		ConsultationsActivity.this.startActivity(myIntent);
     	}
	}
}
