package app.svnit.svnitupdate;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UpdateAdapter extends BaseAdapter{
	private static ArrayList<UpdateRow> updatearrayList;
	//FragmentActivity f;
	int i;
	public UpdateAdapter( ArrayList<UpdateRow> rows){
		updatearrayList = rows;
//		f = context;
	}
	
	@Override
	public int getCount() {

		return updatearrayList.size();
	}

	@Override
	public Object getItem(int position) {

		return updatearrayList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return (long)position;
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View retval;
		retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_events, null);
		try{
			//-----------------------------------------Initializing all the variables-------------------------------------------------------
			i=position;
			
			TextView headline = (TextView) retval.findViewById(R.id.textHeadline);
			headline.setText(updatearrayList.get(position).getHeadline());
			
			TextView tag = (TextView) retval.findViewById(R.id.textTag);
			tag.setText(updatearrayList.get(position).getTag());
			
			TextView time = (TextView) retval.findViewById(R.id.textTime);
			time.setText(updatearrayList.get(position).getTime().toLocaleString());
			
			//retval.setTag(String.valueOf(position));
			
			/*ImageButton add = (ImageButton) retval.findViewById(R.id.buttonAdd);
			add.setTag(String.valueOf(position));
			add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int pos = Integer.parseInt(v.getTag().toString());
					
					addCalendarEvent(updatearrayList.get(pos).getHeadline(),updatearrayList.get(pos).getDate(),updatearrayList.get(pos).getVenue());	
					
				}
			});
			
			*/
			/*retval.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("POSITION", ""+i);
					
					int pos = Integer.parseInt(v.getTag().toString());
					 final Intent intent = new Intent(v.getContext(), UpdateDetailsActivity.class);
				        intent.putExtra("headline", updatearrayList.get(pos).getHeadline()+"");
				        intent.putExtra("details", updatearrayList.get(pos).getArticle()+"");
				        intent.putExtra("tag", updatearrayList.get(pos).getTag()+"");
				        intent.putExtra("timestamp",updatearrayList.get(pos).getTime()+"");
				        intent.putExtra("venue",updatearrayList.get(pos).getVenue()+"");
				        intent.putExtra("date",updatearrayList.get(pos).getDate().getTime());
						v.getContext().startActivity(intent);
						
						
					AlertDialog.Builder builder = new AlertDialog.Builder(f);
			        builder.setMessage(updatearrayList.get(pos).getArticle()).setTitle("Details");
			        
			        AlertDialog alert =builder.create();
			        alert.show();
			    }
			});*/
			if(position % 2 == 0){
				retval.setBackgroundResource(R.color.light_list);
				}
				else{
				retval.setBackgroundResource(R.color.dark_list);
				}
			return retval;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return retval;
	}
}