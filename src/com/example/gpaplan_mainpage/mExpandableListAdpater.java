package com.example.gpaplan_mainpage;

import java.util.List;

import com.example.gpaplan_mainpage.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
public class mExpandableListAdpater extends BaseExpandableListAdapter{
	public Context mContext;
	public ExpandableListView mListView;
	public List mlist;
	public mExpandableListAdpater(Context context, ExpandableListView ListView, List list){
		this.mContext=context;
		this.mListView=ListView;
		this.mlist= list;
		//dddddddd
		
		
	}
	public void addItem(ChildItem citem, GroupItem gitem){
		mlist.add(gitem);
		int index= mlist.indexOf(gitem);
		List lstChild = ((GroupItem)(mlist.get(index))).getItems();
		lstChild.add(citem);
		((GroupItem)(mlist.get(index))).setItems(lstChild);
		
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		List item= ((GroupItem)mlist.get(groupPosition)).getItems();
		return item.get(childPosition);
				
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChildItem citem = (ChildItem)getChild(groupPosition,childPosition);
		if(convertView==null){
			LayoutInflater inflater= (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			convertView= inflater.inflate(R.layout.list_item, null);
		}
		((TextView)convertView.findViewById(R.id.list_item_label)).setText(citem.getScore_en());
		((TextView)convertView.findViewById(R.id.list_item_subjectname)).setText(citem.getSubjectname());
		((TextView)convertView.findViewById(R.id.list_item_major)).setText(citem.getMajor());
		((TextView)convertView.findViewById(R.id.list_item_score)).setText(Double.toString(citem.getScore()));
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return ((GroupItem)mlist.get(groupPosition)).getItems().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mlist.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GroupItem temp = (GroupItem)getGroup(groupPosition);
		if(convertView == null){
			   LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			   convertView = inflater.inflate(R.layout.group_item, null);
		}
		TextView groupLabel= (TextView)convertView.findViewById(R.id.group_label);
		groupLabel.setText(temp.getData());
	
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}