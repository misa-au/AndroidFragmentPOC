package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class ConsultationSelectedActivity extends FragmentActivity {

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
	}
}
