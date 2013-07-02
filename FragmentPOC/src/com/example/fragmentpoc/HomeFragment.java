package com.example.fragmentpoc;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

public class HomeFragment extends SherlockFragment 
	implements View.OnClickListener{
    OnTopicCategorySelectedListener onTopicCategorySelectedListener;

    // the container Activity must implement this interface so the frag can deliver messages
    public interface OnTopicCategorySelectedListener {
        /** called by HomeFragment when a topic category is selected */
        public void onTopicCategorySelected(int categoryType);
    }    
    
    public void setOnTopicCategorySelectedListener(Activity activity) {
        // this makes sure that the container activity has implemented
        // the listener interface. If not, it throws an exception.
        try {
        	onTopicCategorySelectedListener = (OnTopicCategorySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTopicCategorySelectedListener");
        }
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // inflate the layout for this fragment
		View view = inflater.inflate(R.layout.home_fragment, container, false);

		// add the button listeners
		final Button diagnosticButton = (Button) view.findViewById(R.id.diagnostic);
		final Button manamgementButton = (Button) view.findViewById(R.id.management);
		diagnosticButton.setOnClickListener(this);
		manamgementButton.setOnClickListener(this);
        return view;
    }
	
    @Override
	public void onClick(View view){
    	// find out with category type was picked and notify listener
    	int categoryType = view.getId() == R.id.diagnostic
    			? 0
    			: 1;
    	onTopicCategorySelectedListener.onTopicCategorySelected(categoryType);
    }
}
