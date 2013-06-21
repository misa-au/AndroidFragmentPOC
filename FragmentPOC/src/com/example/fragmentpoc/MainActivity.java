package com.example.fragmentpoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of HomeFragment
            HomeFragment firstFragment = new HomeFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
        else if (findViewById(R.id.home_right_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of HomeRightFragment
            HomeRightFragment secondFragment = new HomeRightFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            secondFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'home_right_fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_right_fragment_container, secondFragment).commit();
        }
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
//                Toast.makeText(this, "Tapped consultations", Toast.LENGTH_SHORT).show();
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
    
	/** Called when the user clicks the Diagnostic or Management buttons */
    public void showTopics(View view){
    	
    	// Create topics fragment based on which category type was picked
    	int categoryType = view.getId() == R.id.diagnostic
    			? 0
    			: 1;
    	TopicsFragment topicsFragment = new TopicsFragment();
        Bundle args = new Bundle();
        args.putInt(TopicsFragment.TOPIC_CATEGORY, categoryType);
        topicsFragment.setArguments(args);
		
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        if (findViewById(R.id.fragment_container) != null) {
        	transaction.replace(R.id.fragment_container, topicsFragment);
        }
        else if (findViewById(R.id.home_right_fragment_container) != null) {
        	transaction.replace(R.id.home_right_fragment_container, topicsFragment);
        }
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    } 
}
