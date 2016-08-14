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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EmergencyContacts extends Activity {

	ArrayList<EmergencyRow> econtacts = null;
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

			    InputStream in_s = getApplicationContext().getAssets().open("emergencyContacts.xml");
		        //parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in_s, null);

	            econtacts = getEmergencyContact(parser,"contact");

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ListView lv = (ListView) findViewById(R.id.subcat_list); 
	    EmergencyAdapter eAdapter = new EmergencyAdapter(econtacts);
		lv.setAdapter(eAdapter);
	}
	
	
	ArrayList<EmergencyRow> getEmergencyContact(XmlPullParser parser, String section) throws XmlPullParserException, IOException{
		ArrayList<EmergencyRow> contacts=null;
		 int eventType = parser.getEventType();
	        EmergencyRow currentcontact = null ;
	        while (eventType != XmlPullParser.END_DOCUMENT){
	            String name = null;
	            switch (eventType){
	                
	            	case XmlPullParser.START_DOCUMENT:
	                	contacts = new ArrayList<EmergencyRow>();
	                	Log.d("NEW", "DOCUMENT");
	               // 	  eventType = XmlPullParser.START_TAG;
	                      
	                    break;
	                    
	                case XmlPullParser.START_TAG:
	                    name = parser.getName();
	                    Log.d("START TAG",name+"");
	                    if (name.equals(section)){
	                    	Log.d("NEW", "CONTACT");
	                        currentcontact = new EmergencyRow();
	                    } else if (currentcontact != null){
	                        if (name.equals("name")){
	                           	//currentcontact.designation = parser.nextText();
								currentcontact.setName(parser.nextText());
	                        	//Log.d("NAME!!",currentcontact.name+"");
	                        } else if (name.equals("number")){
	                        	//currentcontact.name = parser.nextText();
	                        	currentcontact.setNumber(parser.nextText());
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
	
	private class EmergencyAdapter extends BaseAdapter{
		private ArrayList<EmergencyRow> eArrayList;
		public EmergencyAdapter(ArrayList<EmergencyRow> contacts){
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
			
			View eview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_emergency_contacts, null);
			
			TextView ename = (TextView) eview.findViewById(R.id.Ename);
			ename.setText(eArrayList.get(position).getName());
			
			TextView enumber = (TextView) eview.findViewById(R.id.Enumber);
			enumber.setText(eArrayList.get(position).getNumber());
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

