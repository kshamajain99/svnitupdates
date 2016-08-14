package app.svnit.svnitupdate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StudentChaptersContacts extends Activity {

	ArrayList<ChapterRow> econtacts = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updates);

	    // get the action bar
        ActionBar actionBar = getActionBar();
 
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

		XmlPullParserFactory pullParserFactory;
		
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			    InputStream in_s = getApplicationContext().getAssets().open("chaptersContacts.xml");
		        //parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in_s, null);

	            econtacts = getChapterContact(parser,"chapter");

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ListView lv = (ListView) findViewById(R.id.subcat_list); 
	    ChaptersAdapter eAdapter = new ChaptersAdapter(econtacts);
		lv.setAdapter(eAdapter);
	}
	
	
	ArrayList<ChapterRow> getChapterContact(XmlPullParser parser, String section) throws XmlPullParserException, IOException{
		ArrayList<ChapterRow> contacts=null;
		 int eventType = parser.getEventType();
	        ChapterRow currentcontact = null ;
	        while (eventType != XmlPullParser.END_DOCUMENT){
	            String name = null;
	            switch (eventType){
	                
	            	case XmlPullParser.START_DOCUMENT:
	                	contacts = new ArrayList<ChapterRow>();
	                	Log.d("NEW", "DOCUMENT");
	               // 	  eventType = XmlPullParser.START_TAG;
	                      
	                    break;
	                    
	                case XmlPullParser.START_TAG:
	                    name = parser.getName();
	                    Log.d("START TAG",name+"");
	                    if (name.equals(section)){
	                    	Log.d("NEW", "CONTACT");
	                        currentcontact = new ChapterRow();
	                    } else if (currentcontact != null){
	                        if (name.equals("name")){
								currentcontact.setChname(parser.nextText());
	                        } else if (name.equals("website")){
	                        	currentcontact.setWebsite(parser.nextText());
	                        } else if (name.equals("fb")){
	                        	currentcontact.setFb(parser.nextText());
	                        } else if (name.equals("cname")){
	                        	currentcontact.setCname(parser.nextText());
	                        } else if (name.equals("cnum")){
	                        	currentcontact.setCnum(parser.nextText());
	                        } else if (name.equals("cmail")){
	                        	currentcontact.setCmail(parser.nextText());
	                        }   
	                    }
	                    
	                    break;
	                case XmlPullParser.END_TAG:
	                    name = parser.getName();
	                    if (name.equalsIgnoreCase(section) && currentcontact != null){
	                    	contacts.add(currentcontact);
	                    	currentcontact = null; 
	                    }
	                    
	            }
	            eventType = parser.next();
	        }
		return contacts;
	}
	
	private class ChaptersAdapter extends BaseAdapter{
		private ArrayList<ChapterRow> eArrayList;
		public ChaptersAdapter(ArrayList<ChapterRow> contacts){
			eArrayList = contacts;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return eArrayList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return eArrayList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View eview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_chapters_contacts, null);
			
			TextView chname = (TextView) eview.findViewById(R.id.ChName);
			chname.setText(eArrayList.get(position).getChname());
			
			TextView website = (TextView) eview.findViewById(R.id.website);
			website.setText(eArrayList.get(position).getWebsite());
			
			TextView fb = (TextView) eview.findViewById(R.id.fb);
			fb.setText(eArrayList.get(position).getFb());
			
			TextView cname = (TextView) eview.findViewById(R.id.cname);
			cname.setText(eArrayList.get(position).getCname());
			
			TextView cnum = (TextView) eview.findViewById(R.id.cnum);
			cnum.setText(eArrayList.get(position).getCnum());
			
			TextView cmail = (TextView) eview.findViewById(R.id.cmail);
			cmail.setText(eArrayList.get(position).getCmail());
			
			if(position % 2 == 0){
				eview.setBackgroundResource(R.color.light_list);
				}
				else{
				eview.setBackgroundResource(R.color.dark_list);
				}
			
			return eview;
		}
		
	}
}
