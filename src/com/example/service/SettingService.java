package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.SettingDao;
import com.example.db.SettingDbHelper;
import com.example.db.SettingDto;

public class SettingService {

	public SettingDao settingDao;
	public SettingDbHelper settingDbHelper;
	public SQLiteDatabase settingDb;
	public static int gpaSys;

	public SettingService(Context context){
		settingDao = new SettingDao(context);
		settingDbHelper = settingDao.dbHelper;
		settingDb = settingDbHelper.getWritableDatabase(); //GPAService 와 다른 부분
	}
	
	public SettingDto getSettingInfo(){
		return settingDao.getSettingInfo(); 
	}
	
	//0이면  4.3  1이면 4.5
	public int infoOfGPASys(){
		return gpaSys=getSettingInfo().getGpaSystem();
	}
}
