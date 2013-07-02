package com.example.fragmentpoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class ConsultationResultsFragment extends SherlockFragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        	// Inflate the layout for this fragment
			View view = inflater.inflate(R.layout.consultation_results_fragment, container, false);
     
        	// Get ListView object from xml
     		final ListView listView = (ListView) view.findViewById(R.id.consultations_results_list);
             
             // Defined Array values to show in ListView
             String[] values = new String[] { "Cause 1", 
                                              "Cause 2",
                                              "Cause 3"
                                             };

             // Define a new Adapter
             // First parameter - Context
             // Second parameter - Layout for the row
             // Third parameter - ID of the TextView to which the data is written
             // Forth - the Array of data
             ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
             	R.layout.cause_cell, R.id.cause_title, values);

             // Assign adapter to ListView
             listView.setAdapter(adapter); 
             
             return view;
    }

}
