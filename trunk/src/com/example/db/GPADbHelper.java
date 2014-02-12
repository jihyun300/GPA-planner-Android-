package com.example.db;

/*
 * created by doyoon
 * MyGPAdbHelper.java 와 같은 클래스
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GPADbHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "gpa_table";

	public static final String ID = "id";
	public static final String COL_YEAR = "year";
	public static final String COL_SEMESTER = "semester";
	public static final String COL_CREDIT = "credit";
	public static final String COL_GRADE = "grade";
	public static final String COL_MAJOR = "major";
	public static final String COL_SUBJECT = "subject";

	private static final String DATABASE_CREATE = GPADbHelper
			.dataBaseCreateSQL();

	private static final String DATABASE_NAME = "gpa.db";

	public GPADbHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL(DATABASE_CREATE);
	}

	public synchronized void close() {
		super.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	private static String dataBaseCreateSQL() {
		StringBuilder sd = new StringBuilder();
		sd.append("CREATE TABLE ");
		sd.append(TABLE_NAME);
		sd.append(" ("+ ID+" INTEGER AUTOINCREMENT, ");
		sd.append(COL_YEAR + " INTEGER,");
		sd.append(COL_SEMESTER + "INTEGER,");
		sd.append(COL_CREDIT + "INTEGER,");
		sd.append(COL_GRADE + "TEXT,");
		sd.append(COL_MAJOR + "TEXT,");
		sd.append(COL_SUBJECT + "TEXT");
		sd.append(");");
		return sd.toString();
	}

}