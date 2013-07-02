package com.example.fragmentpoc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ConsultationFragment_Large extends SherlockFragment 
	implements ConsultationTabsFragment.OnSubTabSelectedListener{
    final static String CONSULTATION_NAME = "consultation_name";
    String _consultationName = null;
	Fragment _tabContentFragment;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

		// If activity recreated (such as from screen rotate), restore
	    // the previous article selection set by onSaveInstanceState().
	    // This is primarily necessary when in the two-pane layout.
	    if (savedInstanceState != null) {
	    	_consultationName = savedInstanceState.getString(CONSULTATION_NAME);
	    }

	    setHasOptionsMenu(true);
	    
	    // inflate the layout for this fragment
	    return inflater.inflate(R.layout.consultation_fragment, container, false);
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
	        // Set consultation based on argument passed in
	    	updateConsultation(args.getString(CONSULTATION_NAME));
	    } else if (_consultationName != null) {
	        // Set consultation based on saved instance state defined during onCreateView
	    	updateConsultation(_consultationName);
	    }
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		MenuItem print = menu.add(Menu.NONE, R.id.menu_print, 100, R.string.menu_print);
		MenuItem email = menu.add(Menu.NONE, R.id.menu_email, 100, R.string.menu_email);
		print.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		email.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getActivity(), "Tapped home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_print:
                Toast.makeText(getActivity(), "Tapped print", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_email:
                Toast.makeText(getActivity(), "Tapped email", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
	public void updateConsultation(String consultationName)
	{
		_consultationName = consultationName;
		if (_consultationName == null)
		{
			return;
		}
		// tell header fragment to update contents
		FragmentManager fManager = getFragmentManager();

	    	ConsultationHeaderFragment headerFragment = (ConsultationHeaderFragment)fManager.findFragmentByTag("Header");
	    	boolean fragmentIsNull = headerFragment == null;
	    	if (fragmentIsNull)
	    	{
	    		headerFragment = new ConsultationHeaderFragment();
	            Bundle args = new Bundle();
	            args.putString(ConsultationHeaderFragment.HEADER_NAME, _consultationName);
	            headerFragment.setArguments(args);
	    	}
	    	else
	    	{
	    		headerFragment.updateHeaderName(consultationName);
	    	}

	        // add the transaction to the back stack so the user can navigate back
	    	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	    	if (fragmentIsNull)
	    	{
	    		transaction.replace(R.id.consultation_header_fragment_container, headerFragment, "Header");
	    		transaction.addToBackStack(null);
	    	}
	    	
	    	// add tabs fragment if necessary
	    	ConsultationTabsFragment tabsFragment = (ConsultationTabsFragment)fManager.findFragmentByTag("Tabs");
	    	if (tabsFragment == null)
	    	{
	    		tabsFragment = new ConsultationTabsFragment();
	    		tabsFragment.onSubTabSelectedListener = this;
	    		transaction.replace(R.id.consultation_tabs_fragment_container, tabsFragment, "Tabs");
	    		transaction.addToBackStack(null);
	    	}
	    	// or reset
	    	else
	    	{
	    		tabsFragment.resetTabs();
	    		_tabContentFragment = null;
	    	}

        	// commit the transaction
        	transaction.commit();
	}

	@Override
	public void onSubTabSelected(int subTabType) {
		FragmentManager fManager = getFragmentManager();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		// replace tab content container with appropriate fragment
		Fragment fragment;
		switch(subTabType)
		{
			case 0:
			{
				fragment = (ConsultationResultsFragment)fManager.findFragmentByTag("Results");
				if (fragment == null)
				{
					fragment = new ConsultationResultsFragment();
				}
				transaction.add(R.id.consultation_tab_content_container, fragment, "Results");
				_tabContentFragment = fragment;
				break;
			}
				
			case 1:
				transaction.remove(_tabContentFragment);
				break;
				
			case 2:
				transaction.remove(_tabContentFragment);
				break;
		}

    	// commit the transaction
    	transaction.commit();
	}
}
