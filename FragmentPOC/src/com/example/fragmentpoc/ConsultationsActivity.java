package com.example.fragmentpoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.example.fragmentpoc.ConsultationListFragment.OnConsultationSelectedListener;

public class ConsultationsActivity extends FragmentActivity
	implements OnConsultationSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultations);
		
		// set this as the listener for saved consultation selection
		FragmentManager fManager = getSupportFragmentManager();
		ConsultationListFragment listFragment = (ConsultationListFragment)fManager.findFragmentById(R.id.consultation_list_fragment);
		listFragment.setOnConsultationSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu; this adds items to the action bar if large screen
		if (((POCApp) getApplicationContext()).isLargeLayout())
		{
			getMenuInflater().inflate(R.menu.main_large, menu);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void OnConsultationSelected(String consultationName) {
		// if we're on a large screen, replace whatever is in the 
        // right fragment container view with this fragment
        if (((POCApp) getApplicationContext()).isLargeLayout()) 
     	{
        	// get existing topic fragment or create new, and set or update parameters
        	FragmentManager fManager = getSupportFragmentManager();
        	ConsultationFragment consultationFragment = (ConsultationFragment)fManager.findFragmentByTag("Consultation");
        	boolean fragmentIsNull = consultationFragment == null;
        	if (fragmentIsNull)
        	{
        		consultationFragment = new ConsultationFragment();
                Bundle args = new Bundle();
                args.putString(ConsultationFragment.CONSULTATION_NAME, consultationName);
                consultationFragment.setArguments(args);
        	}
        	else
        	{
        		consultationFragment.updateConsultation(consultationName);
        	}

            // add the transaction to the back stack so the user can navigate back
        	if (fragmentIsNull)
        	{
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        		transaction.replace(R.id.consultation_fragment_container, consultationFragment, "Consultation");
        		transaction.addToBackStack(null);

            	// commit the transaction
            	transaction.commit();
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
