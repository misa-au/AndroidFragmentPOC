package com.example.fragmentpoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class ConsultationHeaderFragment extends SherlockFragment {
    final static String HEADER_NAME = "name";
    String _headerName;
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
	
		// If activity recreated (such as from screen rotate), restore
	    // the previous article selection set by onSaveInstanceState().
	    // This is primarily necessary when in the two-pane layout.
	    if (savedInstanceState != null) {
	    	_headerName = savedInstanceState.getString(HEADER_NAME);
	    }
	    
        return inflater.inflate(R.layout.consultation_header_fragment, container, false);
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
	        // Set category based on argument passed in
	    	updateHeaderName(args.getString(HEADER_NAME));
	    } else if (_headerName != null) {
	        // Set article based on saved instance state defined during onCreateView
	    	updateHeaderName(_headerName);
	    }
	}
	
	public void updateHeaderName(String name)
	{
		_headerName = name;
		// update name
	    TextView textView = (TextView) getActivity().findViewById(R.id.header_name);
	    textView.setTextSize(20);
	    textView.setText(name);
	}
}
