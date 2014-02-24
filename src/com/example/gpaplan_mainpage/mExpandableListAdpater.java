package com.example.gpaplan_mainpage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.gpaplan_mainpage.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
public class mExpandableListAdpater extends BaseExpandableListAdapter{
	public Context mContext;
	public ExpandableListView mListView;
	public ArrayList<GroupItem> grouplist;
	private int groupclickedpos;
	public ArrayList childlist;
	public LayoutInflater inflater;
	private HashMap<String,Integer>imggrade = new HashMap<String,Integer>();

	public void setClickedPos(int pos){
		this.groupclickedpos=pos;		
	}
	public int getClickedPos(){
		return groupclickedpos;
	}
	public mExpandableListAdpater(Context context, ExpandableListView ListView,  ArrayList<GroupItem> list){
		this.mContext=context;
		this.mListView=ListView;
		this.grouplist= list;
		this.inflater=(LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
		this.groupclickedpos =0;
		Settingimggrade();

	}
	private void Settingimggrade(){
		imggrade.put("A+", R.drawable.grade_aplus);
		imggrade.put("A", R.drawable.grade_a);
		imggrade.put("A-",R.drawable.grade_aminus);
		imggrade.put("B+", R.drawable.grade_bplus);
		imggrade.put("B", R.drawable.grade_b);
		imggrade.put("B-",R.drawable.grade_bminus);
		imggrade.put("C+", R.drawable.grade_cplus);
		imggrade.put("C", R.drawable.grade_c);
		imggrade.put("C-", R.drawable.grade_cminus);
		imggrade.put("D+", R.drawable.grade_dplus);
		imggrade.put("D", R.drawable.grade_d);
		imggrade.put("D-", R.drawable.grade_dminus);
		imggrade.put("F", R.drawable.grade_f);
	}
	public void addItem(ChildItem citem, GroupItem gitem){
		grouplist.add(gitem);
		int index= grouplist.indexOf(gitem);
		ArrayList lstChild = ((GroupItem)(grouplist.get(index))).getItems();
		lstChild.add(citem);
		((GroupItem)(grouplist.get(index))).setItems(lstChild);

	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		ArrayList item= ((GroupItem)grouplist.get(groupPosition)).getItems();
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
		convertView= inflater.inflate(R.layout.list_item, null);
		((ImageView)convertView.findViewById(R.id.list_item_grade)).setImageResource(imggrade.get(citem.getScore_en()));
		((TextView)convertView.findViewById(R.id.list_item_subjectname)).setText(citem.getSubjectname());
		((TextView)convertView.findViewById(R.id.list_item_major)).setText(citem.getMajor());
		((TextView)convertView.findViewById(R.id.list_item_credit)).setText(Integer.toString(citem.getCredit()));
		
		if(groupPosition==getClickedPos()){
			Animation fadeAnimation;
			if(mListView.isGroupExpanded(groupPosition)) 
				fadeAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.slidedown);
			else
				fadeAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.slideup);
			
			convertView.startAnimation(fadeAnimation);
		}
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return ((GroupItem)grouplist.get(groupPosition)).getItems().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return grouplist.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return grouplist.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GroupItem temp = (GroupItem)getGroup(groupPosition);
		convertView = inflater.inflate(R.layout.group_item, null);
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
		}
		TextView groupName= (TextView)convertView.findViewById(R.id.group_label);
		TextView groupGrade = (TextView)convertView.findViewById(R.id.group_grade);
		groupName.setText(temp.getName());
		//소수점 두번째 자리에서 자르기
		DecimalFormat format = new DecimalFormat("0.00");
		groupGrade.setText(format.format(temp.getGrade()));

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