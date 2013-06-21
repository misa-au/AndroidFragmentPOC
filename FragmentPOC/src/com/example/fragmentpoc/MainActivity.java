package com.example.fragmentpoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
	implements View.OnClickListener{	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
		if (savedInstanceState != null) {
            return;
        }
		
		// add the right side fragment if we're on a large screen device
		if (((POCApp) getApplicationContext()).isLargeLayout())
		{
            // create an instance of HomeRightFragment
            HomeRightFragment secondFragment = new HomeRightFragment();

            // in case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            secondFragment.setArguments(getIntent().getExtras());

            // add the fragment to the 'home_right_fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_right_fragment_container, secondFragment).commit();
        }
		
		// add the button listeners
		final Button diagnosticButton = (Button) findViewById(R.id.diagnostic);
		final Button manamgementButton = (Button) findViewById(R.id.management);
		diagnosticButton.setOnClickListener(this);
		manamgementButton.setOnClickListener(this);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

            case R.id.action_settings:
                Toast.makeText(this, "Tapped settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }	
    
    @Override
	public void onClick(View view){
    	
    	// create topics fragment based on which category type was picked
    	int categoryType = view.getId() == R.id.diagnostic
    			? 0
    			: 1;
    	TopicsFragment topicsFragment = new TopicsFragment();
        Bundle args = new Bundle();
        args.putInt(TopicsFragment.TOPIC_CATEGORY, categoryType);
        topicsFragment.setArguments(args);
		
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // if we're on a large screen, replace whatever is in the 
        // right fragment container view with this fragment, 
        // add the transaction to the back stack so the user can navigate back
        if (((POCApp) getApplicationContext()).isLargeLayout()) 
     	{
        	transaction.replace(R.id.home_right_fragment_container, topicsFragment);
        	transaction.addToBackStack(null);

        	// commit the transaction
        	transaction.commit();
     	}
     	
     	// we're on a small screen, so create a new activity
     	else
     	{
     		Intent myIntent = new Intent(MainActivity.this, TopicsActivity.class);
        	MainActivity.this.startActivity(myIntent);
     	}
    } 
}
