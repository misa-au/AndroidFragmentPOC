package com.example.fragmentpoc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class MainActivity extends SherlockFragmentActivity 
	implements HomeFragment.OnTopicCategorySelectedListener {	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
        // if using large layout, force landscape orientation (otherwise, portrait)
        boolean isLargeLayout = ((POCApp) getApplicationContext()).isLargeLayout();
        setRequestedOrientation(
        	isLargeLayout == true 
        		? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 
        				: ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
		setContentView(R.layout.activity_main);
		
		if (isLargeLayout == true)
		{
			// set up tabs nav for large screen
			final ActionBar bar = getSupportActionBar();
			bar.addTab(bar.newTab().
					setText("Home").
					setTabListener(new TabListener<HomeFragment_Large>(this, "Home", HomeFragment_Large.class)));
			bar.addTab(bar.newTab().
					setText("Consultations").
					setTabListener(new TabListener<ConsultationsFragment>(this, "Consultations", ConsultationsFragment.class)));
			bar.setDisplayShowTitleEnabled(false);
			bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			if (savedInstanceState != null) 
			{
	            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
	        }
		}
		else
		{
			// set this as the listener for topic category selection for phone screen
        	FragmentManager fManager = getSupportFragmentManager();
        	HomeFragment homeFragment = (HomeFragment)fManager.findFragmentById(R.id.home_fragment);
        	homeFragment.setOnTopicCategorySelectedListener(this);
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu; this adds items to the action bar if large screen, and to settings if small screen
		int menuID = ((POCApp) getApplicationContext()).isLargeLayout()
				? R.menu.main_large
				: R.menu.main;
		getSupportMenuInflater().inflate(menuID, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                Toast.makeText(this, "Tapped profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_medications:
                Toast.makeText(this, "Tapped medications", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_consultations:
            	Intent myIntent = new Intent(MainActivity.this, ConsultationsActivity.class);
            	MainActivity.this.startActivity(myIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }	
    
	public void onTopicCategorySelected(int categoryType){

     		Intent myIntent = new Intent(MainActivity.this, TopicsActivity.class);
     		myIntent.putExtra(TopicsFragment.TOPIC_CATEGORY, categoryType);
        	MainActivity.this.startActivity(myIntent);
    }
}
