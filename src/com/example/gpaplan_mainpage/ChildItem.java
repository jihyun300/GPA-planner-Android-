package com.example.gpaplan_mainpage;

public class ChildItem {
	public int DBid;
	public String score_en;
	public String subjectname ;
	public String major;
	public int credit;
	public void setSubData(int DBid, String score_en, String subjectname,String major, int credit){
		this.score_en = score_en;
		this.subjectname = subjectname;
		this.major 	= major;
		this.credit =credit;
		this.DBid=DBid;
		//////////
	}
	public String getScore_en() {
		return score_en;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public String getMajor() {
		return major;
	}
	public int getCredit() {
		return credit;
	}
	public int getDBid(){
		return DBid;
	}
	
	
}
