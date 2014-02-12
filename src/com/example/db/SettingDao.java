package com.example.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SettingDao {
	public SettingDbHelper dbHelper;
	public SQLiteDatabase db;

	public SettingDao(Context context) {
		dbHelper = new SettingDbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public SettingDto getSettingInfo() {
		Cursor cs = db.rawQuery("SELECT * FROM setting_table LIMIT 1;", null);
		SettingDto settingDto = null;
		try {
			do {
				settingDto = new SettingDto(cs.getInt(0), cs.getInt(1),
						cs.getFloat(2), cs.getInt(3));
			} while (!cs.isLast());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQL에러");
		}finally{
			if(db!=null){
				db.close();
			}
			dbHelper.close();
		}
		return settingDto;
	}

	public void updateSetting() {
		//
	}

	public void deleteSetting() {
		//
	}
}
