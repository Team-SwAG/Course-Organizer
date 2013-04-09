package com.teamswag.course_organizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "db";
	static final String YEAR = "year";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE year (_id integer primary key autoincrement, year text");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		android.util.Log.w("Year", "Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS year");
		
	}

}
