package app.svnit.svnitupdate;

import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class UpdateDetailsActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_details);
	       // get the action bar
        ActionBar actionBar = getActionBar();
 
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);
 
		Intent i = getIntent();
		final String headline = i.getStringExtra("headline");
		final String detail = i.getStringExtra("details");
		final String tag = i.getStringExtra("tag");
		final Date timestamp = new Date();
		timestamp.setTime(i.getLongExtra("timestamp", -1));
		
		final String venue  = i.getStringExtra("venue");
		final Date date = new Date();
		date.setTime(i.getLongExtra("date", -1));
		
		TextView headlineTextView = (TextView) findViewById(R.id.updateHeadline);
		headlineTextView.setText(headline);
		TextView detailsTextView = (TextView) findViewById(R.id.updateDetails);
		detailsTextView.setText(detail);
		
		TextView tagTextView = (TextView) findViewById(R.id.textViewTag);
		tagTextView.setText(tag);
		
		TextView timeTextView = (TextView) findViewById(R.id.textViewTime);
		timeTextView.setText(timestamp.toLocaleString());
		
		
		ImageButton add = (ImageButton) findViewById(R.id.buttonAdd);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				addCalendarEvent(headline,date,venue);	
				
			}
		});
	}
	public void addCalendarEvent(String title, Date d, String venue){
		//Calendar cal = 
		
		Intent intent = new Intent(Intent.ACTION_EDIT);
	    intent.setType("vnd.android.cursor.item/event");
	    if(d!=null){
	    intent.putExtra("beginTime", d.getTime());
	    intent.putExtra("allDay", false);
	    intent.putExtra("endTime", d.getTime()+60*60*1000);
	    }
	    
	    intent.putExtra("title", title);
	    
	    if(venue!=null){
	    intent.putExtra("eventLocation", venue);
		}
	    startActivity(intent);
	    
	  }
	
}
