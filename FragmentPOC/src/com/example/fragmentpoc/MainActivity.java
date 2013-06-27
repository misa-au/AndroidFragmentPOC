package com.example.fragmentpoc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends SherlockFragmentActivity 
	implements HomeFragment.OnTopicCategorySelectedListener{	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
        // if using large layout, force landscape orientation (otherwise, portrait)
        setRequestedOrientation(
        	((POCApp) getApplicationContext()).isLargeLayout() 
        		? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 
        				: ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
		setContentView(R.layout.activity_main);
		
		// set this as the listener for topic category selection
		FragmentManager fManager = getSupportFragmentManager();
		HomeFragment homeFragment = (HomeFragment)fManager.findFragmentById(R.id.home_fragment);
    	homeFragment.setOnTopicCategorySelectedListener(this);
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
            case android.R.id.home:
                Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_consultations:
            	Intent myIntent = new Intent(MainActivity.this, ConsultationsActivity.class);
            	MainActivity.this.startActivity(myIntent);
                break;

            case R.id.menu_profile:
                Toast.makeText(this, "Tapped profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_medications:
                Toast.makeText(this, "Tapped medications", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }	
    
	public void onTopicCategorySelected(int categoryType){

        // if we're on a large screen, replace whatever is in the 
        // right fragment container view with this fragment
        if (((POCApp) getApplicationContext()).isLargeLayout()) 
     	{	
        	// get existing topic fragment or create new, and set or update parameters
        	FragmentManager fManager = getSupportFragmentManager();
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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        		transaction.replace(R.id.home_right_fragment_container, topicsFragment, "Topics");
        		transaction.addToBackStack(null);

            	// commit the transaction
            	transaction.commit();
        	}
     	}
     	
     	// we're on a small screen, so create a new activity and pass category type
     	else
     	{  
     		Intent myIntent = new Intent(MainActivity.this, TopicsActivity.class);
     		myIntent.putExtra(TopicsFragment.TOPIC_CATEGORY, categoryType);
        	MainActivity.this.startActivity(myIntent);
     	}
    } 
}
