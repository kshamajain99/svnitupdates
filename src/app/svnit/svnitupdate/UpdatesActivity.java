package app.svnit.svnitupdate;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class UpdatesActivity extends ListFragment{
	
	public static ProgressBar pbar ;
	UpdateRow updateObject = new UpdateRow();
	ArrayList<String> updates = new ArrayList<String>();
	List<ParseObject> ob;
	ArrayList<UpdateRow> updateList = new ArrayList<UpdateRow>();
	String detail;
	String arg = new String();
	boolean flag =true,flagProgress=true;
	private PullToRefreshListView mPullToRefreshListView;
	ActionBar actionBar;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//setEmptyText("Loading..");
		 View layout = inflater.inflate(R.layout.activity_updates, container, false);
	    
		 ListView lv = (ListView) layout.findViewById(R.id.subcat_list);

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
//		mPullToRefreshListView.setLoadingDrawable(null);
	//	mPullToRefreshListView.setRefreshingLabel("No Network Connection!! :(");
		
		mPullToRefreshListView.onRefreshComplete();
		
	//	Toast.makeText(getActivity(), "No network Connection!!", Toast.LENGTH_LONG ).show();
		
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnected();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//getActivity().setProgressBarIndeterminate(true);
		
		//actionBar =  getActivity().getActionBar();
		
		updates = new ArrayList<String>();
		updateList = new ArrayList<UpdateRow>();
		//setListAdapter(null);
		if(isOnline()){
		if(flag){

	
			mPullToRefreshListView.setRefreshing();
			getActivity().setProgressBarIndeterminateVisibility(true);
			fetchRemoteData();
			
			
			flag=false;
		}else{

			populateListView();
		}
			}else{
	
				populateListView();
			}
		
		//	Bundle args = new Bundle();
		
	//	if(getArguments()==null){
		//	populateListView();
		//}
		/*else{
			arg = getArguments().getString("query");
			populateSearch();
		}
		*/
	}
	
	private void populateListView(){
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Updates");
		query.fromPin("updates");
		//setEmptyText("Fetching  Updates...");
		query.orderByDescending("updatedAt");
		query.whereEqualTo("status", 1);
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
    	        		//setEmptyText("No Recent  Updates");
    	       // 		fetchRemoteData();
    	        	}
    	        } else {
    	        	//setEmptyText("Sorry.. Error fetching the  updates :(");
    	            Log.d("Error Log", "Error: " + e.getMessage());
    	        //    fetchRemoteData();
    	        }
				
				UpdateAdapter uadapter = new UpdateAdapter(updateList);
				setListAdapter(null);
				setListAdapter(uadapter);
				
				if(mPullToRefreshListView.isRefreshing()){
					mPullToRefreshListView.onRefreshComplete();
					}
				getActivity().setProgressBarIndeterminateVisibility(false);
		//			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,updates);
	//			setListAdapter(adapter);
    	    
			}
		});
		
	}
	/*
private void populateSearch(){
		Log.d("search", "function");
		String s = arg.toUpperCase();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Updates");
		setEmptyText("Searching Updates...");
		query.whereEqualTo("status", 1);
		query.orderByDescending("updatedAt");
		//query.whereContains("tag", "COED");
		query.whereEqualTo("search", s);
	//	query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
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
    	        		setEmptyText("No Such Updates");
    	        	}
    	        } else {
    	        	setEmptyText("Sorry.. Error fetching the updates :(");
    	        	fetchRemoteData();
    	            Log.d("Error Log", "Error: " + e.getMessage());
    	        }
				
				UpdateAdapter uadapter = new UpdateAdapter(updateList);
				setListAdapter(uadapter);
	//			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,updates);
	//			setListAdapter(adapter);
    	    
			}
		});
	}
*/
		// Fetch from remote and replace local pin
	public void fetchRemoteData() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Updates");
	
	//	setEmptyText("Fetching Updates...");
	query.orderByDescending("updatedAt");
	query.whereEqualTo("status", 1);
	
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
	      //  		setEmptyText("No Such Updates");
	        	}
	        } else {
	        //	setEmptyText("Sorry.. Error fetching the updates :(");
	//        	fetchRemoteData();
	        	populateListView();
	            Log.d("Error Log", "Error: " + e.getMessage());
	        }
			
			UpdateAdapter uadapter = new UpdateAdapter(updateList);
			setListAdapter(null);
			setListAdapter(uadapter);
			if(mPullToRefreshListView.isRefreshing()){
			mPullToRefreshListView.onRefreshComplete();
			}
			getActivity().setProgressBarIndeterminateVisibility(false);
			 unpinAndRepin(objects);
	}
   }); 

}


public void unpinAndRepin(final List<ParseObject> data){
	Log.d("UPDATES", "Unpinning...");
	ParseObject.unpinAllInBackground("updates", new DeleteCallback() {
		
		@Override
		public void done(ParseException arg0) {
			// TODO Auto-generated method stub
			// Add the latest results for this query to the cache.
			Log.d("UPDATES", "Unpinned!");
			ParseObject.pinAllInBackground("updates", data);
			   Log.d("UPDATES", "Pinning...");
		}
	} );
	   
}

	

	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
      //  super.onListItemClick(l, v, position, id);
       // l.getChildAt(position).setBackgroundColor(Color.DKGRAY);
		position = position -1;
		  Intent intent = new Intent(v.getContext(), UpdateDetailsActivity.class);
	        intent.putExtra("headline", updateList.get(position).getHeadline()+"");
	        intent.putExtra("details", updateList.get(position).getArticle()+"");
	        intent.putExtra("tag", updateList.get(position).getTag()+"");
	        intent.putExtra("timestamp",updateList.get(position).getTime().getTime());       
	        if(updateList.get(position).getVenue() !=null){
	        intent.putExtra("venue",updateList.get(position).getVenue()+"");
	        }else{
	        	intent.putExtra("venue","");
	        }
	        if(updateList.get(position).getDate() !=null){
	        intent.putExtra("date",updateList.get(position).getDate().getTime());
	        }else{
	        	intent.putExtra("date","");
	        }
			v.getContext().startActivity(intent);

    	
}


}
