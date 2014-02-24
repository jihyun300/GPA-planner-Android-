package com.example.gpaplan_mainpage;

import java.util.*;

public class GroupItem {
	private String group_name ;
	private float group_grade;
	public ArrayList<ChildItem> items;
	public void setName(String label){
		this.group_name=label;
	}
	public void setGrade(float gpa){
		this.group_grade=gpa;
	}
	public String getName(){
		return this.group_name;
	}
	public float getGrade(){
		return this.group_grade; 
	}
	public void setItems(ArrayList<ChildItem> items){
		this.items=items;
	}
	public ArrayList<ChildItem> getItems(){
		return this.items;
	}	
}
