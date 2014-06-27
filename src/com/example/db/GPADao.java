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
	}
	// 전체
	public List<GPADto> getAllList() {
		db = dbHelper.getWritableDatabase();
		List<GPADto> dtoList = new ArrayList<GPADto>();
		try {
			Cursor cs = db.rawQuery("SELECT * FROM "
					+ GPADbHelper.TABLE_NAME + " ORDER BY year, semester ASC ;", null);
			while (cs.moveToNext()) {
				dtoList.add(new GPADto(cs.getInt(0), 
													cs.getInt(1),
													cs.getInt(2), 
													cs.getInt(3), 
													cs.getString(4),
													cs.getString(5), 
													cs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return dtoList;
	}
	//학년 학기별 
	public List<GPADto> getGPADtoList(int year, int semester){
		db = dbHelper.getWritableDatabase();
		List<GPADto> dtoList = new ArrayList<GPADto>();
		String query = "SELECT id, year, semester, credit, grade, major, subject"
								+" FROM "+GPADbHelper.TABLE_NAME
								+" WHERE year LIKE '"+year+"%'"
								+" AND semester LIKE '"+semester+"%';";
		try{
			Cursor cs = db.rawQuery(query, null);
			while(cs.moveToNext()){
				dtoList.add(new GPADto(cs.getInt(0), 
													cs.getInt(1),
													cs.getInt(2), 
													cs.getInt(3), 
													cs.getString(4), 
													cs	.getString(5),
													cs.getString(6)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(db!=null){
				db.close();
			}
		}
		return dtoList;
	}
	// INSERT 하는 로직 지현이꺼에 쓰일 로직
	public void insertGPADto(Object obj) {
		db = dbHelper.getWritableDatabase();
		GPADto dto = (GPADto) obj;
		try {
			db.execSQL("INSERT INTO " + GPADbHelper.TABLE_NAME + 
								" VALUES ("+ " null, " + 
												dto.getYear() + "," +
												dto.getSemester() + ","+ 
												dto.getCredit() + ",'" + 
												dto.getGrade() + "'," + "'"	+
												dto.getMajor() + "','" + 
												dto.getSubject() + "' );");
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}
	//ID 값으로 과목 정보만 불러오기 made by 대현
	public GPADto getDtoById(int DBid){
		db = dbHelper.getWritableDatabase();
		GPADto dto_temp = new GPADto();
		try {
			Cursor cs = db.rawQuery("SELECT * FROM "
					+ GPADbHelper.TABLE_NAME
					+ " WHERE id like '"+DBid+"%' ;", null);
			while (cs.moveToNext()) {
				dto_temp = new GPADto(cs.getInt(0), cs.getInt(1),
						cs.getInt(2), cs.getInt(3), cs.getString(4), cs
								.getString(5), cs.getString(6));
			}
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			//dbHelper.close();
		}
		return dto_temp;
	}
	
	// MainView에 보여져야함
	
	// UPDATE
	public void updateOneGpa(int DBid,Object obj){
		db = dbHelper.getWritableDatabase();
		GPADto dto = (GPADto) obj;
		String updatesql =  "UPDATE "+GPADbHelper.TABLE_NAME+" SET "
						+ "year = "+ dto.getYear() +", semester = "+ dto.getSemester()
						+ ", credit = "+ dto.getCredit() +", grade = '"+dto.getGrade() 
						+"', major = '"+dto.getMajor()+"', subject = '"+dto.getSubject()
						+ "' WHERE id ="+DBid+";";
		try {
			
			db.execSQL(updatesql);
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			//dbHelper.close();
		}
	}
	//
	//DELETE
	public void DeleteOneGpa(int DBid){
		db = dbHelper.getWritableDatabase();
		String deletesql =  "DELETE FROM "+GPADbHelper.TABLE_NAME+ 
							" WHERE id = '"+DBid+"' ;";						
		try {
			
			db.execSQL(deletesql);
		} catch (Exception e) {
			System.out.println("SQL오류");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
			//dbHelper.close();
		}

	}
	public void DeleteAll(){
		db = dbHelper.getWritableDatabase();
		
	}
}
