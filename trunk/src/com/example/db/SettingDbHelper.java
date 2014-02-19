package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SettingDbHelper extends SQLiteOpenHelper {
	public static final String TABLE_NAME = "setting_table";
	public static final String ID = "settingId";
	public static final String COL_GPASYSTEM = "gpaSystem";
	public static final String COL_GPATARGET = "gpaTarget";
	public static final String COL_CREDITFORGRADU = "creditForGraduate";
	
	private static final String DATABASE_CREATE = SettingDbHelper.dataBaseCreateSQL();
	
	private static final String DATABASE_NAME = "setting.db";
	
	public SettingDbHelper(Context context){
		super(context,DATABASE_NAME,null,1);
	}
	
	private static String dataBaseCreateSQL(){
		StringBuilder sd = new StringBuilder();
		sd.append("CREATE TABLE ");
		sd.append(TABLE_NAME);
		sd.append(" ( ");
		sd.append(ID+" INTEGER PRIMARY KEY AUTOINCREMENT,");
		sd.append(COL_GPASYSTEM); //0 or 1
		sd.append(" FLOAT,");
		sd.append(COL_GPATARGET);
		sd.append(" FLOAT,");
		sd.append(COL_CREDITFORGRADU);
		sd.append(" INTEGER );");
		return sd.toString();
	} 

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}
	public synchronized void close(){
		super.close();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
