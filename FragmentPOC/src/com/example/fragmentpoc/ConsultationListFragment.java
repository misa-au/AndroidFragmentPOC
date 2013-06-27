package com.example.fragmentpoc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ConsultationListFragment extends Fragment {
    OnConsultationSelectedListener onConsultationSelectedListener;

    // the container Activity must implement this interface so the frag can deliver messages
    public interface OnConsultationSelectedListener {
        /** called by ConsultationListFragment when a consultation is selected */
        public void OnConsultationSelected(String consultationName);
    }    
    
    public void setOnConsultationSelectedListener(Activity activity) {
        // this makes sure that the container activity has implemented
        // the listener interface. If not, it throws an exception.
        try {
        	onConsultationSelectedListener = (OnConsultationSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnConsultationSelectedListener");
        }
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.consultation_list_fragment, container, false);

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
                 int position, long id) {
               
               // ListView Clicked item value
               String  consultationName = (String)listView.getItemAtPosition(position);
                  
               onConsultationSelectedListener.OnConsultationSelected(consultationName);
              }

         });
		
        return view;
    }

}
