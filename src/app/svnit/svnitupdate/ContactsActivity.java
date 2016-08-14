package app.svnit.svnitupdate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class ContactsActivity extends Fragment {

	public GridView gridview;
	ArrayList<LinkedHashMap<String, Object>> listImageItem;
	
	String[] info = {"Student Council", "Student Chapters","Departments","Administration","Hostels","Emergency","Academic Calendar", "Institute Holidays","Institute Phonebook"};
	//String[] info = this.getResources().getStringArray(R.array.contacts);
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		populate();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	//	View view = getView();
//		gridview = new GridView(this.getActivity());
		View view = inflater.inflate(R.layout.activity_contacts, container, false);
		gridview = (GridView) view.findViewById(R.id.gridView1);
		gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v,
	                int position, long id) {
	            /*Toast.makeText(getActivity(), position + "#Selected",
	                    Toast.LENGTH_SHORT).show();
	            */switch (position) {
				case 0:
					final Intent intent = new Intent(getActivity(), CouncilContactDetails.class);
		           
					startActivity(intent);
					break;
				case 1:
					//Toast.makeText(getActivity(), "Coming soon!", Toast.LENGTH_SHORT).show();
					final Intent intent1 = new Intent(getActivity(), StudentChaptersContacts.class);
		            startActivity(intent1);
					break;
				case 2:
					final Intent intent2 = new Intent(getActivity(), DepartmentContactDetails.class);
		            startActivity(intent2);
					break;
				case 3:
					final Intent intent3 = new Intent(getActivity(), AdminContactDetails.class);
		            startActivity(intent3);
					break;
				case 4:
					final Intent intent4 = new Intent(getActivity(), HostelContactDetails.class);
		            startActivity(intent4);
					break;
				case 5:
					final Intent intent5 = new Intent(getActivity(), EmergencyContacts.class);
		            startActivity(intent5);
					break;
				case 6:
					PDFTools.showPDFUrl(getActivity(), "http://www.svnit.ac.in/qlinks/pdfs/acad_cal_2014_15.pdf");
					break;
				case 7:
					PDFTools.showPDFUrl(getActivity(), "http://www.svnit.ac.in/qlinks/pdfs/PUBLICHOLIDAY2015.pdf");
					break;
				case 8:
					PDFTools.showPDFUrl(getActivity(), "http://www.svnit.ac.in/directory/phonebook.pdf");
					break;
				default:
					break;
					
				}
	            
	        }

	    });
		return view;
	}

	public void populate() {
		// Generation of dynamic array, and transferred to the data #In the onStart () method for loading data, can dynamically refresh data in Fragment#
		listImageItem = new ArrayList<LinkedHashMap<String, Object>>();
			for (int i = 0; i <9; i++) {
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				Object t = info[i];
				switch(i){
					case 0:
						map.put("ItemImage", R.drawable.student_council);
						break;
					case 1:
						map.put("ItemImage", R.drawable.communities);
						break;
					case 2:
						map.put("ItemImage", R.drawable.department);
						break;
					case 3:
						map.put("ItemImage", R.drawable.administration);
						break;
					case 4:
						map.put("ItemImage", R.drawable.hostels);
						break;
					case 5:
						map.put("ItemImage", R.drawable.emergency);
						break;
					case 6:
						map.put("ItemImage", R.drawable.calender);
						break;
					case 7:
						map.put("ItemImage", R.drawable.holiday);
						break;
					case 8:
						map.put("ItemImage", R.drawable.phonebook);
						break;
					
				}
			//  map.put("ItemImage", R.drawable.contact);// Add image resources ID
				map.put("ItemText", t);// Do ItemText by serial number
				listImageItem.add(map);
			}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(
				getActivity().getApplicationContext(), listImageItem,
				R.layout.contact_list_item_gridview, new String[] {
						"ItemImage", "ItemText" }, new int[] { R.id.displayImg,
						R.id.displayName });
		//Add picture binding
		simpleAdapter.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if (view instanceof ImageView && data instanceof Drawable) {
					ImageView iv = (ImageView) view;
					iv.setImageDrawable((Drawable) data);
					return true;
				} else
					return false;
			}
		});
		
		gridview.setAdapter(simpleAdapter);
		super.onStart();
	}
	
	
}