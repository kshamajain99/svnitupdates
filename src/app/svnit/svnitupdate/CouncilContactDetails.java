package app.svnit.svnitupdate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class CouncilContactDetails extends Activity {

	 ExpandableListView expandableListView;
	    ExpandableListAdapter expandableListAdapter;
	    List<String> expandableListTitle;
	    LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_contact_details);
	        // get the action bar
	        ActionBar actionBar = getActionBar();
	 
	        // Enabling Back navigation on Action Bar icon
	        actionBar.setDisplayHomeAsUpEnabled(true);
			getIntent();
			
	     // For execution wing
			XmlPullParserFactory pullParserFactory;
			try {
				pullParserFactory = XmlPullParserFactory.newInstance();
				XmlPullParser parser = pullParserFactory.newPullParser();

				    InputStream in_s = getApplicationContext().getAssets().open("studentCouncil.xml");
			        //parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		            parser.setInput(in_s, null);

		            ContactsData.parseXML(parser,"ew");

			} catch (XmlPullParserException e) {

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//For sac
			try {
				pullParserFactory = XmlPullParserFactory.newInstance();
				XmlPullParser parser = pullParserFactory.newPullParser();

				    InputStream in_s = getApplicationContext().getAssets().open("studentCouncil.xml");
			        //parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		            parser.setInput(in_s, null);

		            ContactsData.parseXML(parser,"sac");

			} catch (XmlPullParserException e) {

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//for cc
			try {
				pullParserFactory = XmlPullParserFactory.newInstance();
				XmlPullParser parser = pullParserFactory.newPullParser();

				    InputStream in_s = getApplicationContext().getAssets().open("studentCouncil.xml");
			        //parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		            parser.setInput(in_s, null);

		            ContactsData.parseXML(parser,"cc");

			} catch (XmlPullParserException e) {

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
	        expandableListDetail = ContactsData.getCouncilData();
	        Log.d("SIZE", expandableListDetail.size()+"");
	        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
	           
	        expandableListAdapter = new StudentCouncilAdapter(this, expandableListTitle, expandableListDetail);
	        expandableListView.setAdapter(expandableListAdapter);
	        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {

	            @Override
	            public void onGroupExpand(int groupPosition) {
/*	                Toast.makeText(getApplicationContext(),
	                        expandableListTitle.get(groupPosition) + " List Expanded.",
	                        Toast.LENGTH_SHORT).show();
*/	            }
	        });

	        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

	            @Override
	            public void onGroupCollapse(int groupPosition) {
	             /*   Toast.makeText(getApplicationContext(),
	                        expandableListTitle.get(groupPosition) + " List Collapsed.",
	                        Toast.LENGTH_SHORT).show();
*/
	            }
	        });

	        expandableListView.setOnChildClickListener(new OnChildClickListener() {
	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v,
	                                        int groupPosition, int childPosition, long id) {
	                Toast.makeText(
	                        getApplicationContext(),
	                        expandableListTitle.get(groupPosition)
	                                + " -> "
	                                + expandableListDetail.get(
	                                expandableListTitle.get(groupPosition)).get(
	                                childPosition), Toast.LENGTH_SHORT
	                )
	                        .show();
	                return false;
	            }
	        });
	    }

}
