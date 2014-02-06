package com.example.db;
/*
 * created by doyoon
 * MyGPAdto와 같음
 * Settingdto 는 따로 만들어야함
 */

public class GPADto {
	private int id; // auto increment id
	private int year; // 학년
	private int semester; // 학기
	private int credit; // 학점
	private String grade; // A B+ C-
	private String major; // 전공 교양 ///// 전공이면 true 교양이면 false
	private String subject; // 과목명

	public GPADto() {
		super();
	}

	public GPADto(int id, int year, int semester, int credit, String grade,
			String major, String subject) {
		super();
		this.id = id;
		this.year = year;
		this.semester = semester;
		this.credit = credit;
		this.grade = grade;
		this.major = major;
		this.subject = subject;
	}

	public GPADto(int year, int semester, int credit, String grade,
			String major, String subject) {
		super();
		this.year = year;
		this.semester = semester;
		this.credit = credit;
		this.grade = grade;
		this.major = major;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId() {
		// /
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

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyGPAdto [id=");
		builder.append(id);
		builder.append(", year=");
		builder.append(year);
		builder.append(", semester=");
		builder.append(semester);
		builder.append(", credit=");
		builder.append(credit);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", major=");
		builder.append(major);
		builder.append(", subject=");
		builder.append(subject);
		builder.append("]");
		return builder.toString();
	}
}
