package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class ConsultationsFragment extends SherlockFragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.consultations_fragment, container, false);

		// Get ListView object from xml
		final ListView listView = (ListView) view.findViewById(R.id.consultations);
        
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
        	R.layout.consultation_cell, R.id.consultation_title, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, 
        		int position, long id) 
        	{
               
        		// ListView Clicked item value
        		String consultationName = (String)listView
        				.getItemAtPosition(position);
                  
        		// get existing topic fragment, and create new if none
        		FragmentManager fManager = getFragmentManager();
        		ConsultationFragment_Large consultationFragment = 
        			(ConsultationFragment_Large)fManager.
        				findFragmentByTag("Consultation");
        		boolean fragmentIsNull = consultationFragment == null;
        		if (fragmentIsNull)
        		{
        			consultationFragment = new ConsultationFragment_Large();
        			Bundle args = new Bundle();
        			args.putString(ConsultationFragment.CONSULTATION_NAME,
        				consultationName);
        			consultationFragment.setArguments(args);
                   
        			// add the transaction to the back stack 
        			FragmentTransaction transaction = fManager.beginTransaction();
        			transaction.replace(R.id.consultation_fragment_container, 
        				consultationFragment, "Consultation");
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
        });
        return view;
    }
}
