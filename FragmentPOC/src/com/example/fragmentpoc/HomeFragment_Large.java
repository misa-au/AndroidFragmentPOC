package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

public class HomeFragment_Large extends SherlockFragment 
	implements View.OnClickListener{
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // inflate the layout for this fragment
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		
		// hide keyboard
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		// add the button listeners
		final Button diagnosticButton = (Button) view.findViewById(R.id.diagnostic);
		final Button managementButton = (Button) view.findViewById(R.id.management);
		diagnosticButton.setOnClickListener(this);
		managementButton.setOnClickListener(this);
        return view;
    }
	
    @Override
	public void onClick(View view){
    	// find out with category type was picked and notify listener
    	int categoryType = view.getId() == R.id.diagnostic
    			? 0
    			: 1;
    	// get existing topic fragment or create new, and set or update parameters
    	FragmentManager fManager = getFragmentManager();
    	TopicsFragment topicsFragment = (TopicsFragment)fManager.findFragmentByTag("Topics");
    	boolean fragmentIsNull = topicsFragment == null;
    	if (fragmentIsNull)
    	{
    		topicsFragment = new TopicsFragment();
            Bundle args = new Bundle();
            args.putInt(TopicsFragment.TOPIC_CATEGORY, categoryType);
            topicsFragment.setArguments(args);
    	}
    	else
    	{
    		topicsFragment.updateTopics(categoryType);
    	}

        // add the transaction to the back stack so the user can navigate back
    	if (fragmentIsNull)
    	{
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
    		transaction.replace(R.id.topics_fragment_container, topicsFragment, "Topics");
    		transaction.addToBackStack(null);

        	// commit the transaction
        	transaction.commit();
    	}
    }
}
