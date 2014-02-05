package com.example.gpaplan_mainpage;

public class Class_info {
		private String year;
		private String semester;
		private String className;
		private boolean MajorOrNot;
		public Class_info(String year, String semester,String className,boolean MajorOrNot){
			this.year=year;
			this.semester=semester;
			this.className=className;
			this.MajorOrNot=MajorOrNot;
	//		this.setClassName(className);
	//		this.setMajorOrNot(MajorOrNot);
		}
		
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getSemester() {
			return semester;
		}
		public void setSemester(String semester) {
			this.semester = semester;
		}
		public String getClassName() {
			return className;
		}
		
		public void setClassName(String className) {
			this.className = className;
		}
		public boolean getMajorOrNot() {
			return MajorOrNot;
		}
		public void setMajorOrNot(boolean majorOrNot) {
			MajorOrNot = majorOrNot;
		}
}
