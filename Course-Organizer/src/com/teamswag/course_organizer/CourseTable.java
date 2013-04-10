package com.teamswag.course_organizer;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CourseTable {
	public static final String NAME = "course";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_YEAR_ID = "year_id";
	public static final String COLUMN_SEMESTER_ID = "semester_id";
	
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + NAME + " ( " + COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME
				+ " TEXT NOT NULL, " + COLUMN_YEAR_ID + " INTEGER, "
				+ COLUMN_SEMESTER_ID + " TEXT NOT NULL);");
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {

		Log.w(YearTable.class.getName(), "Upgrading from version " + oldVersion
				+ " to version " + newVersion + ", which will destroy all data");
		db.execSQL("DROP TABLE IF EXISTS " + NAME);
		onCreate(db);
	}
}
