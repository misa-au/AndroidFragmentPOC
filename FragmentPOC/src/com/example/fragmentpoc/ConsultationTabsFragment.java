package com.example.fragmentpoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

public class ConsultationTabsFragment extends SherlockFragment 
	implements View.OnClickListener{
    private static final String Button = null;
	OnSubTabSelectedListener onSubTabSelectedListener;

    public interface OnSubTabSelectedListener {
        /** called by ConsultationTasFragment when a sub-tab in a consult is selected */
        public void onSubTabSelected(int subTabType);
    }    
    
    public void onSubTabSelectedListener(SherlockFragment fragment) {
        // this makes sure that the container activity has implemented
        // the listener interface. If not, it throws an exception.
        try {
        	onSubTabSelectedListener = (OnSubTabSelectedListener) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString()
                    + " must implement OnTopicCategorySelectedListener");
        }
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.consultation_tabs_fragment, container, false);

		// add the button listeners
		view.findViewById(R.id.results).setOnClickListener(this);
		view.findViewById(R.id.physicians).setOnClickListener(this);
		view.findViewById(R.id.summary).setOnClickListener(this);
        return view;
    }

	public void resetTabs()
	{
		onSubTabSelectedListener.onSubTabSelected(0);
	}

	@Override
	public void onClick(View view) {
    	// find out with tab was picked and notify listener
		int viewID = view.getId();
    	int tabType = viewID == R.id.results
    			? 0
    			: viewID == R.id.physicians	
    				? 1
    				: 2;
    	onSubTabSelectedListener.onSubTabSelected(tabType);
	}
}
