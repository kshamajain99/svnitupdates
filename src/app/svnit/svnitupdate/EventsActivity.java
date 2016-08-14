package app.svnit.svnitupdate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventsActivity extends ListFragment {
	
	UpdateRow updateObject = new UpdateRow();
	ArrayList<String> updates = new ArrayList<String>();
	List<ParseObject> ob;
	ArrayList<UpdateRow> updateList = new ArrayList<UpdateRow>();
	String detail,pinName="events";
	boolean flag =true;
	private PullToRefreshListView mPullToRefreshListView;
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnected();
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 View layout = inflater.inflate(R.layout.events_list, container, false);
	        ListView lv = (ListView) layout.findViewById(R.id.subevent_list);

	        mPullToRefreshListView = new PullToRefreshListView(getActivity());
	        mPullToRefreshListView.setLayoutParams(lv.getLayoutParams());

	        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>()
	                                {
	                                    @Override
	                                    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
	                                    	if(isOnline()){
	                                    		il  = mPullToRefreshListView.getLoadingLayoutProxy();
	                                    		il.setRefreshingLabel("Loading...");
	                                    		il.setLoadingDrawable(null);
	                                    		updates = new ArrayList<String>();
		                                		updateList = new ArrayList<UpdateRow>();
		                                	//	setListAdapter(null);
	                                    		fetchRemoteData();
	                                    	}else{
	                                    		
	                                    		noNetwork();	                                    		
	                                    	}
	                                    }
	                                });

	        return mPullToRefreshListView;
	}
	ILoadingLayout il;
	@SuppressWarnings("deprecation")
	public void noNetwork(){
		il  = mPullToRefreshListView.getLoadingLayoutProxy();
		il.setRefreshingLabel("No Network Connection!! :(");
		il.setLoadingDrawable(null);
		mPullToRefreshListView.onRefreshComplete();
	//	Toast.makeText(getActivity(), "No network Connection!!", Toast.LENGTH_LONG ).show();
		
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updates = new ArrayList<String>();
		updateList = new ArrayList<UpdateRow>();
		//setListAdapter(null);
		if(isOnline()){
			if(flag){
				fetchRemoteData();
				flag=false;
			}else{
				populateEvents();
			}
				}else{
					populateEvents();
				}
	}

	
	void populateEvents(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Updates");
		
		query.fromPin("events");
	//	setEmptyText("Fetching Events...");
		query.whereContains("type", "event");
		query.whereEqualTo("status", 1);
		query.orderByAscending("eventDate");
		query.whereGreaterThanOrEqualTo("eventDate", new Date());
		//query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				
				      // add data to adapter
				    		if (e == null) {
		    	        	if(objects.size()>0){
		    	        		ob=objects;
		    	        	for(int i=0;i<objects.size();i++){
		    	        	updateObject =  new UpdateRow();
		    	        	//    updates.add(objects.get(i).getString("title"));
		    	            updateObject.setHeadline(objects.get(i).getString("title"));
		    	            updateObject.setTag(objects.get(i).getString("tag"));
		    	            updateObject.setTime(objects.get(i).getUpdatedAt());
		    	            updateObject.setArticle(objects.get(i).getString("desc"));
		    	     
		    	            updateObject.setDate(objects.get(i).getDate("eventDate"));
		    	            updateObject.setVenue(objects.get(i).getString("eventVenue"));
		    	            updateList.add(updateObject);
		    	            Log.d("LOG",""+objects.get(i).getObjectId());
		    	            }
		    	        	}else{
		    	        //		setEmptyText("No Recent Events");
		    	        		//fetchRemoteData();
		    	        	}
		    	        } else {
		    	        //	setEmptyText("Sorry.. Error fetching the events :(");
		    	        	//fetchRemoteData();
		    	            Log.d("Error Log", "Error: " + e.getMessage());
		    	        }
						EventAdapter eadapter = new EventAdapter(updateList);
						mPullToRefreshListView.onRefreshComplete();
						setListAdapter(null);
						setListAdapter(eadapter);
				    //  progressBarInvisible();
				   
			}
		});
		/* {
		   // inside success callback
		   
		});*/


		}

	// Fetch from remote and replace local pin
	public void fetchRemoteData() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Updates");
		//setEmptyText("Fetching Events...");
		query.whereContains("type", "event");
		query.orderByAscending("eventDate");
		query.whereEqualTo("status", 1);
		query.whereGreaterThanOrEqualTo("eventDate", new Date());
		
		query.findInBackground(new FindCallback<ParseObject>() {
		
		   @Override
		public void done(List<ParseObject> objects, ParseException e) {
			// TODO Auto-generated method stub
			   if (e == null) {
   	        	if(objects.size()>0){
   	        		ob=objects;
   	        	for(int i=0;i<objects.size();i++){
   	        	updateObject =  new UpdateRow();
   	        	//    updates.add(objects.get(i).getString("title"));
   	            updateObject.setHeadline(objects.get(i).getString("title"));
   	            updateObject.setTag(objects.get(i).getString("tag"));
   	            updateObject.setTime(objects.get(i).getUpdatedAt());
   	            updateObject.setArticle(objects.get(i).getString("desc"));
   	     
   	            updateObject.setDate(objects.get(i).getDate("eventDate"));
   	            updateObject.setVenue(objects.get(i).getString("eventVenue"));
   	            updateList.add(updateObject);
   	            Log.d("LOG",""+objects.get(i).getObjectId());
   	            }
   	        	}else{
   	        	//	setEmptyText("No Recent Events");
   	        	}
   	        } else {
   	        	//setEmptyText("Sorry.. Error fetching the events :(");
   	            Log.d("Error Log", "Error: " + e.getMessage());
   	            populateEvents();
   	        }
				EventAdapter eadapter = new EventAdapter(updateList);
				setListAdapter(null);
				setListAdapter(eadapter);
				mPullToRefreshListView.onRefreshComplete();
				unpinAndRepin(objects);
		}
	   }); 
	
	}
	
	
	public void unpinAndRepin(final List<ParseObject> data){
	
		ParseObject.unpinAllInBackground("events", new DeleteCallback() {
			
			@Override
			public void done(ParseException arg0) {
				// TODO Auto-generated method stub
				// Add the latest results for this query to the cache.
				   ParseObject.pinAllInBackground("events", data);
			}
		} );
		   
	}
/*	{
	      // inside success callback
	      // add data to adapter
	      
		   progressBarInvisible();
	      unpinAndRepin(data);
	   });
	}

	// Unpin old data and pin new data to local datastore
	unpinAndRepin(data) {

	   ParseObject.unpinAllInBackground(pinName, data, new DeleteCallback() { ... });
	   // Add the latest results for this query to the cache.
	   ParseObject.pinAllInBackground(pinName, data);
	}
*/	
	
	
	}


