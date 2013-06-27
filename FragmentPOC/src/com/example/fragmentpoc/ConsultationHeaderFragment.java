package com.example.fragmentpoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class ConsultationHeaderFragment extends SherlockFragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.consultation_header_fragment, container, false);
    }

	public void updateHeaderName(String name)
	{
		// update name
	    TextView textView = (TextView) getActivity().findViewById(R.id.header_name);
	    textView.setTextSize(20);
	    textView.setText(name);
	}
}
