package com.example.gpaplan_mainpage;

public class ChildItem {
	public String score_en;
	public String subjectname ;
	public String major;
	public double score;
	public void setSubData(String score_en, String subjectname,String major, double score){
		this.score_en = score_en;
		this.subjectname = subjectname;
		this.major 	= major;
		this.score =score;
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
	
	
}
