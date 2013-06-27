package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConsultationFragment extends Fragment {
    final static String CONSULTATION_NAME = "consultation_name";
    String _consultationName = null;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

		// If activity recreated (such as from screen rotate), restore
	    // the previous article selection set by onSaveInstanceState().
	    // This is primarily necessary when in the two-pane layout.
	    if (savedInstanceState != null) {
	    	_consultationName = savedInstanceState.getString(CONSULTATION_NAME);
	    }

	    // inflate the layout for this fragment
        return inflater.inflate(R.layout.consultation_fragment, container, false);
	}

	@Override
	public void onStart() {
	    super.onStart();

	    // During startup, check if there are arguments passed to the fragment.
	    // onStart is a good place to do this because the layout has already been
	    // applied to the fragment at this point so we can safely call the method
	    // below that sets the article text.
	    Bundle args = getArguments();
	    if (args != null) {
	        // Set consultation based on argument passed in
	    	updateConsultation(args.getString(CONSULTATION_NAME));
	    } else if (_consultationName != null) {
	        // Set consultation based on saved instance state defined during onCreateView
	    	updateConsultation(_consultationName);
	    }
	}
	public void updateConsultation(String consultationName)
	{
		_consultationName = consultationName;
		// tell header fragment to update contents
		FragmentManager fManager = getFragmentManager();
    	ConsultationHeaderFragment headerFragment = (ConsultationHeaderFragment)fManager.findFragmentById(R.id.consultation_header_fragment);
    	headerFragment.updateHeaderName(consultationName);
	}
}
