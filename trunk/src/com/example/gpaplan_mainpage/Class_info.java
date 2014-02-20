package com.example.gpaplan_mainpage;

public class Class_info {
	private int year;;
	private int semester;
	private String grade;
	private String credit;
	private int credit_int;
	private boolean major;
	private String subject;
	
	
	public Class_info(String subject, String grade, String credit, boolean major){
		this.subject=subject;
		this.credit =credit;
		this.grade=grade;
		this.setMajor(major);
	}
	
	
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean isMajor() {
		return major;
	}
	public void setMajor(boolean major) {
		this.major = major;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}



	 public String getMajorString(){
         if(this.isMajor()){
                 return "전공";
         }
         else{
                 return "교양";
         }
                 
	 }
	
		
}
