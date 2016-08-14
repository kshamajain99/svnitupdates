package app.svnit.svnitupdate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends FragmentActivity {

	TextView t;
	String query;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_search);
	 
	        
	        Intent i = getIntent();
	        query = i.getExtras().getString("query");
	        // get the action bar
	        ActionBar actionBar = getActionBar();
	 
	        // Enabling Back navigation on Action Bar icon
	        actionBar.setDisplayHomeAsUpEnabled(true);
	 
	        t = (TextView) this.findViewById(R.id.Enumber);
	        t.setText("Searching...");
	        handleIntent(getIntent());
	    }
	 
/*	    @Override
	    protected void onNewIntent(Intent intent) {
	        setIntent(intent);
	        handleIntent(intent);
	    }
	 
*/	    /**
	     * Handling intent data
	     */
	    private void handleIntent(Intent intent) {
	    	Fragment fragment = new UpdatesActivity();
			Bundle args = new Bundle();
			Log.d("args", query+"");
			args.putString("query", query+"");
			
			fragment.setArguments(args);
	    	FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.addToBackStack(null);

			transaction.replace(R.id.frame_container, fragment).commit();
	        //    String query = intent.getStringExtra(SearchManager.QUERY);
	    //        t.setText(query+"");
	        
	 
	    }
}
