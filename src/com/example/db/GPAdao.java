package com.example.db;
/*
 * created by doyoon
 * GPAdao.java 파일과 같음
 * 
 * update delete 구현 안됨
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class  GPAdao {

	public  GPAdbHelper dbHelper;
	public SQLiteDatabase db;

	public  GPAdao(Context context) {
		dbHelper = new  GPAdbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	// 전체
	public List< GPAdto> getAllList() {
		List< GPAdto> dtoList = new ArrayList< GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+  GPAdbHelper.TABLE_NAME  +";", null);

		while (cs.moveToNext()) {
			dtoList.add(new  GPAdto(cs.getInt(0),cs.getInt(1), cs.getInt(2), cs.getInt(3),
					cs.getString(4), cs.getString(5), cs.getString(6)));
		}

		return dtoList;
	}

	// 1-1 성적
	public List<GPAdto> getList_1_1() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '1%' AND semester like '1%' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0) ,cs.getInt(1), cs.getInt(2), cs.getInt(3),
					cs.getString(4), cs.getString(5), cs.getString(6)));
		}

		return dtoList;
	}
	// 1-2 성적
	public List<GPAdto> getList_1_2() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '1 %' AND semester like '2 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0), cs.getInt(1), cs.getInt(2), cs.getInt(3),
					cs.getString(4), cs.getString(5), cs.getString(6)));
		}

		return dtoList;
	}
	// 2-1 성적
	public List<GPAdto> getList_2_1() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '2 %' AND semester like '1 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0) ,cs.getInt(1), cs.getInt(2), cs.getInt(3),
					cs.getString(4), cs.getString(5), cs.getString(6)));
		}

		return dtoList;
	}
	// 2-2 성적
	public List<GPAdto> getList_2_2() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '"+"2 "+"%' AND semester like '2 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0), cs.getInt(1), cs.getInt(2),
					cs.getInt(3), cs.getString(4), cs.getString(5),cs.getString(6)));
		}

		return dtoList;
	}
	// 3-1 성적
	public List<GPAdto> getList_3_1() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '3 %' AND semester like '1 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0), cs.getInt(1), cs.getInt(2),
					cs.getInt(3), cs.getString(4), cs.getString(5),cs.getString(6)));
		}

		return dtoList;
	}
	//3-2 성적
	public List<GPAdto> getList_3_2() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '3 %' AND semester like '2 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0), cs.getInt(1), cs.getInt(2),
					cs.getInt(3), cs.getString(4), cs.getString(5),cs.getString(6)));
		}

		return dtoList;
	}
	//4-1 성적
	public List<GPAdto> getList_4_1() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '4 %' AND semester like '1 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0), cs.getInt(1), cs.getInt(2),
					cs.getInt(3), cs.getString(4), cs.getString(5),cs.getString(6)));
		}

		return dtoList;
	}
	//4-2 성적
	public List<GPAdto> getList_4_2() {
		List<GPAdto> dtoList = new ArrayList<GPAdto>();
		Cursor cs = db.rawQuery("SELECT id, year, semester,"
				+ "credit, grade, major, subject FROM"
				+ GPAdbHelper.TABLE_NAME
				+ " WHERE year like '4 %' AND semester like '2 %' ;", null);

		while (cs.moveToNext()) {
			dtoList.add(new GPAdto(cs.getInt(0), cs.getInt(1), cs.getInt(2),
					cs.getInt(3), cs.getString(4), cs.getString(5),cs.getString(6)));
		}

		return dtoList;
	}

	
	
	
	// INSERT 하는 로직  지현이꺼에 쓰일 로직
	public void insertOneGPA(Object obj) {
		GPAdto dto = (GPAdto) obj;
		db.execSQL("INSERT INTO " + GPAdbHelper.TABLE_NAME + "VALUES ("+null
				+ dto.getYear() + "," + dto.getSemester() + ","
				+ dto.getCredit() + ",'" + dto.getGrade() + "'," + "'"
				+ dto.getMajor() + "'" + dto.getSubject() + "' );");
	}
	
//MainView에 보여져야함
	//UPDATE
	
	//
}
