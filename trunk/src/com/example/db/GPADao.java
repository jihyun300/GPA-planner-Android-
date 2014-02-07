package com.example.db;

/*
 * created by doyoon
 * GPAdao.java 파일과 같음
 * 
 * update delete 구현 안됨  (Tab뷰와 연결되야함)
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GPADao {

	public GPADbHelper dbHelper;
	public SQLiteDatabase db;

	public GPADao(Context context) {
		dbHelper = new GPADbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	// 전체
	public List<GPADto> getAllList() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME + ";", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 1-1 성적
	public List<GPADto> getList_1_1() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '1%' AND semester like '1%' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 1-2 성적
	public List<GPADto> getList_1_2() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '1 %' AND semester like '2 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 2-1 성적
	public List<GPADto> getList_2_1() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '2 %' AND semester like '1 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 2-2 성적
	public List<GPADto> getList_2_2() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME + " WHERE year like '" + "2 "
					+ "%' AND semester like '2 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 3-1 성적
	public List<GPADto> getList_3_1() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '3 %' AND semester like '1 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 3-2 성적
	public List<GPADto> getList_3_2() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '3 %' AND semester like '2 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 4-1 성적
	public List<GPADto> getList_4_1() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '4 %' AND semester like '1 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// 4-2 성적
	public List<GPADto> getList_4_2() {
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT id, year, semester,"
					+ "credit, grade, major, subject FROM"
					+ GPADbHelper.TABLE_NAME
					+ " WHERE year like '4 %' AND semester like '2 %' ;", null);

			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}

		return dtoList;
	}

	// INSERT 하는 로직 지현이꺼에 쓰일 로직
	public void insertOneGPA(Object obj) {
		GPADto dto = (GPADto) obj;
		try {
			db.execSQL("INSERT INTO " + GPADbHelper.TABLE_NAME + "VALUES ("
					+ null + dto.getYear() + "," + dto.getSemester() + ","
					+ dto.getCredit() + ",'" + dto.getGrade() + "'," + "'"
					+ dto.getMajor() + "'" + dto.getSubject() + "' );");
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			dbHelper.close();
		}
	}

	// MainView에 보여져야함
	// UPDATE

	//
}
