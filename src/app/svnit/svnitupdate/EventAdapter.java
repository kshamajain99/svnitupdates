package app.svnit.svnitupdate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {

	ArrayList<UpdateRow> eventlist;
	 Date tempDate = new Date();
	
	public Date getTempDate() {
		return tempDate;
	}
	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}
	public EventAdapter(ArrayList<UpdateRow> events){
		eventlist = events;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return eventlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return eventlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public static Date getZeroTimeDate(Date fecha) {
	    Date res = fecha;
	    Calendar calendar = Calendar.getInstance();

	    calendar.setTime( fecha );
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    res = calendar.getTime();

	    return res;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View retrow, rethead;
		Log.d("TEMP-DATE1", getTempDate()+"");
		Date d = eventlist.get(position).getDate();
		DateFormat dft = new SimpleDateFormat("dd MMM,yyyy (EEEE)");
		Log.d("DATE FORMAT",dft.format(d)+"");
		

//		Date currentDate = new Date();// get current date           
		//Date eventDate = tempAppointments.get(i).mStartDate;
		int dateMargin = getZeroTimeDate(d).compareTo(getZeroTimeDate(getTempDate()));
		
		if(dateMargin < 0){
			return null;
		}
		
			else if(dateMargin > 0){
				DateFormat dft1 = new SimpleDateFormat("dd MMM,yyyy (EEEE)");
				//Log.d("DATE FORMAT",dft.format(d)+"");
				
				Log.d("TEMP-DATE", getTempDate()+"");
				rethead = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_header, null);
				setTempDate(d);
				TextView header = (TextView) rethead.findViewById(R.id.textHeader);
				header.setText(dft.format(d)+"");
				TextView title = (TextView) rethead.findViewById(R.id.textEvent);
				title.setText(eventlist.get(position).getHeadline());
				
				TextView venue = (TextView) rethead.findViewById(R.id.textVenue);
				venue.setText(eventlist.get(position).getVenue());
				
				DateFormat df = new SimpleDateFormat("hh:mm a");
				String hour = df.format(eventlist.get(position).getDate());
				
				TextView time = (TextView) rethead.findViewById(R.id.textTime);
				time.setText(hour+"");
				
				return rethead;
			}else{
				Log.d("TEMP-DATE2", getTempDate()+"");
				retrow = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_row, null);
				
				
				TextView title = (TextView) retrow.findViewById(R.id.textEvent);
				title.setText(eventlist.get(position).getHeadline());
				
				TextView venue = (TextView) retrow.findViewById(R.id.textVenue);
				venue.setText(eventlist.get(position).getVenue());
				
				DateFormat df = new SimpleDateFormat("hh:mm a");
				String hour = df.format(eventlist.get(position).getDate());
				
				TextView time = (TextView) retrow.findViewById(R.id.textTime);
				time.setText(hour+"");
				
				return retrow;

			}
	
	}

}
