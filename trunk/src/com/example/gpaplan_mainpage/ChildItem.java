package com.example.gpaplan_mainpage;

public class ChildItem {
	public int DBid;
	public String score_en;
	public String subjectname ;
	public String major;
	public double score;
	public void setSubData(int DBid, String score_en, String subjectname,String major, double score){
		this.score_en = score_en;
		this.subjectname = subjectname;
		this.major 	= major;
		this.score =score;
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
	public double getScore() {
		return score;
	}
	public int getDBid(){
		return DBid;
	}
	
	
}
