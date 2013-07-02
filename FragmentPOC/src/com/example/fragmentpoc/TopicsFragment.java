package com.example.fragmentpoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TopicsFragment extends Fragment {
    final static String TOPIC_CATEGORY = "category";
    int _categoryType = -1;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
	
		// If activity recreated (such as from screen rotate), restore
	    // the previous article selection set by onSaveInstanceState().
	    // This is primarily necessary when in the two-pane layout.
	    if (savedInstanceState != null) {
	    	_categoryType = savedInstanceState.getInt(TOPIC_CATEGORY);
	    }

	    // inflate the layout for this fragment
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
	    } else if (_categoryType != -1) {
	        // Set article based on saved instance state defined during onCreateView
	    	updateTopics(_categoryType);
	    }
	}

	public void updateTopics(int categoryType) {
		_categoryType = categoryType;
	    
	    // update list of topics
	    TextView textView = (TextView) getActivity().findViewById(R.id.select_consultation);
	    textView.setTextSize(40);
	    textView.setText(_categoryType == 0 
	    	? "Diagnostic" 
	    	: "Management");
	    

		// Get ListView object from xml
		final ListView listView = (ListView) getActivity().findViewById(R.id.topics_list_view);
        
        // Defined Array values to show in ListView
        String[] values = new String[] { "Knee Pain", 
                                         "Current Problem History",
                                         "Diabetes Management"
                                        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        	R.layout.topic_cell, R.id.topic_title, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter); 

        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, 
        		int position, long id) 
        	{
        		// start a new activity
         		Intent myIntent = new Intent(getActivity(), QuestionnaireActivity.class);
         		getActivity().startActivity(myIntent);
        	}
        });
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);

	    // Save the current category selection in case we need to recreate the fragment
	    outState.putInt(TOPIC_CATEGORY, _categoryType);
	}

}
