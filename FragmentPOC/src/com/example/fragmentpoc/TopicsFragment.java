package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopicsFragment extends Fragment {
    final static String TOPIC_CATEGORY = "category";
    int categoryType = -1;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
	
		// If activity recreated (such as from screen rotate), restore
	    // the previous article selection set by onSaveInstanceState().
	    // This is primarily necessary when in the two-pane layout.
	    if (savedInstanceState != null) {
	    	categoryType = savedInstanceState.getInt(TOPIC_CATEGORY);
	    }

	    // Inflate the layout for this fragment
	    return inflater.inflate(R.layout.topics_fragment, container, false);
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
	    	updateTopics(args.getInt(TOPIC_CATEGORY));
	    } else if (categoryType != -1) {
	        // Set article based on saved instance state defined during onCreateView
	    	updateTopics(categoryType);
	    }
	}

	public void updateTopics(int type) {
	    categoryType = type;
	    
	    // update list of topics
	    TextView textView = (TextView) getActivity().findViewById(R.id.select_consultation);
	    textView.setTextSize(40);
	    textView.setText(categoryType == 0 ? "Diagnostic" : "Management");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);

	    // Save the current category selection in case we need to recreate the fragment
	    outState.putInt(TOPIC_CATEGORY, categoryType);
	}

}
