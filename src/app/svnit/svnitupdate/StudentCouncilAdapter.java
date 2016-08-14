package app.svnit.svnitupdate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class StudentCouncilAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail;

    public StudentCouncilAdapter(Context context, List<String> expandableListTitle,
                                 LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        //Log.d("DEE",this.expandableListDetail.get(expandableListDetail).get(0).getDesignation()+"");
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
    	Log.d("IN CHILD VIEW", "HELLO3");
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        //final String expandedListText = (String) getChild(listPosition, expandedListPosition);
    	Log.d("IN CHILD VIEW", "HELLO1");
    	StudentCouncilContact s = (StudentCouncilContact) getChild(listPosition, expandedListPosition);
        Log.d("IN CHILD VIEW", "HELLO2");
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.contact_detail_item, null);
        }
        Log.d("DEsignation", s.getDesignation()+"");
        TextView designation = (TextView) convertView
                .findViewById(R.id.designation);
        designation.setText(s.getDesignation());
        
        TextView name = (TextView) convertView
                .findViewById(R.id.Ename);
        name.setText(s.getName());
        
        TextView number = (TextView) convertView
                .findViewById(R.id.contact);
        number.setText(s.getNumber());
        
        TextView email = (TextView) convertView
                .findViewById(R.id.email);
        email.setText(s.getEmail());
        
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        //listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
