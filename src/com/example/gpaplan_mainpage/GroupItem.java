package com.example.gpaplan_mainpage;

import java.util.*;


public class GroupItem {
	public String group_label ;
	public List<ChildItem> items;
	public void setData(String label){
		this.group_label=label;
	}
	public String getData(){
		return this.group_label;
	}
	public void setItems(List<ChildItem> items){
		this.items=items;
	}
	public List<ChildItem> getItems(){
		return this.items;
	}	
}
