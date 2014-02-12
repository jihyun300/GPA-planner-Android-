package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SettingDao {
	public SettingDbHelper dbHelper;
	public SQLiteDatabase db;
	
	public SettingDao(Context context){
		dbHelper = new SettingDbHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	
	public SettingDto getSettingInfo(){
		return null;
	}
}
