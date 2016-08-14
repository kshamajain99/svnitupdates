package app.svnit.svnitupdate;


import java.util.Locale;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class MainActivity extends ActionBarActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	Fragment contactsFragment,updatesFragment, eventsFragment;
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	
	ViewPager mViewPager;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	getWindow().requestFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		setProgressBarIndeterminate(true);

		// To track statistics around application
        ParseAnalytics.trackAppOpened(getIntent());
 
        // inform the Parse Cloud that it is ready for notifications
        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(1);
		mViewPager.setOffscreenPageLimit(3);
		//syncData();
	/*	Log.d("test1", "HELLLLOOOOO");
		handleIntent(getIntent());
		Log.d("test2", "HELLLLOOOOO");*/
	}

//	public void syncData(){
		
	//}
	/*@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
		Log.d("test3", "HELLLLOOOOO");
	}

	private void handleIntent(Intent intent) {
		
		Log.d("test5", "HELLLLOOOOO");
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			Log.d("test4", "HELLLLOOOOO");
			
			Intent intent5 =  new Intent(this, SearchActivity.class);
			intent5.putExtra("query", query);
			startActivity(intent5);
			
						Fragment fragment = new UpdatesActivity();
			Bundle args = new Bundle();
			args.putString("query", query.toUpperCase());
			fragment.setArguments(args);
		FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.addToBackStack(null);

			transaction.replace(R.id.frame_container, fragment).commit();
			// use the query to search your data somehow
		}
	}
*/
	/*Fragment myFragment = new UpdatesActivity();
	
	public void onSaveInstanceState(Bundle outState){
	    getSupportFragmentManager().putFragment(outState,"myfragment",myFragment);
	}
	
	
	public void onRestoreInstanceState(Bundle inState){
	    myFragment = getSupportFragmentManager().getFragment(inState,"myfragment");
	}
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
	/*	// Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.action_search).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName())); */
		return true; 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*case R.id.action_settings:
		
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return true;
		
	*/	
		
        switch (item.getItemId()) {
      /*  case R.id.action_search:
        	handleIntent(getIntent());
        	return true;
      */  	
		case R.id.action_feedback:	
			final Intent intent1 = new Intent(this, WebviewActivity.class);
	        intent1.putExtra("url", "https://docs.google.com/forms/d/1K-BNxeqYI64kTornyWm7jPwitAigedl-h3mKJxYIZjA/viewform?c=0&w=1");
			startActivity(intent1);
			return true;

	case R.id.action_sendnews:
		final Intent intent2 = new Intent(this, WebviewActivity.class);
        intent2.putExtra("url", "https://docs.google.com/forms/d/1POnJ0CWzXsHf5DZBXxS-_1t25I-GZyxiJUavlLGNbpQ/viewform?usp=send_form");
		startActivity(intent2);
		return true;

	case R.id.action_sendevent:

		    final Intent intent3 = new Intent(this, WebviewActivity.class);
	        intent3.putExtra("url", "https://docs.google.com/forms/d/1UsyMhjsuDRpSa2MExoRv45R0gK-5FMfDZ4A6enPWTvY/viewform?usp=send_form");
			startActivity(intent3);
			return true;

	case R.id.cnum:
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("About")
	    
	    .setMessage(Html.fromHtml("SVNIT Updates is to keep you updated with the latest happenings and " +
	    		"events in the SVNIT campus! It also provides with the important contacts, academic calendar and " +
	    		"institute holidays information, all at one place. <br> You can reach us out on svnit.update@gmail.com " +
	    		"or on +919722122310 for any query or assistance. <br> You can send us Event/Updates/Feeback via the main menu options." +
	    		"<br> <br> <b><u> Contributors </u></b> <br>Pallavi Khandelwal<br>B.Tech COED-2015 " +
	    		"<br> <br>Kshama Jain<br>B.Tech COED-2015 <br> <br>Devanshi Patel<br>B.Tech COED-2015" ) )
	    .setPositiveButton(android.R.string.ok, null)
	     .show();
			
	   
		return true;
	default:
		return super.onOptionsItemSelected(item);
	}
}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			switch(position){
			case 0:
				
				if(contactsFragment == null)
			          contactsFragment = new ContactsActivity();
			    return contactsFragment;				
			case 1:				
				if(updatesFragment == null)
		//			setProgressBarIndeterminateVisibility(true);
				updatesFragment = new UpdatesActivity();
		    return updatesFragment;
			case 2:
				if(eventsFragment == null)
			//		setProgressBarIndeterminateVisibility(true);  
					eventsFragment = new EventsActivity();
			    return eventsFragment;			
			    }
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

}
