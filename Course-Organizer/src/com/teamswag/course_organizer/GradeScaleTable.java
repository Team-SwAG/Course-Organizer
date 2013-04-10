package com.teamswag.course_organizer;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GradeScaleTable {
	
		public static final String NAME = "grade_scale";
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_COURSE_ID = "course_id";
		public static final String COLUMN_A = "A", COLUMN_A_MINUS = "A minus",
				COLUMN_B_PLUS = "B plus", COLUMN_B = "B",
				COLUMN_B_MINUS = "B minus", COLUMN_C_PLUS = "C plus",
				COLUMN_C = "C", COLUMN_C_MINUS = "C minus",
				COLUMN_D_PLUS = "D plus", COLUMN_D = "D",
				COLUMN_D_MINUS = "D minus";

		public static void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + NAME + " ( " + COLUMN_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_A
					+ " REAL, " + COLUMN_A_MINUS + " REAL, " + COLUMN_B_PLUS
					+ " REAL, " + COLUMN_B + " REAL, " + COLUMN_B_MINUS 
					+ " REAL, " + COLUMN_C_PLUS + " REAL, " + COLUMN_C 
					+ " REAL, " + COLUMN_C_MINUS + " REAL, " + COLUMN_D_PLUS 
					+ " REAL, " + COLUMN_D + " REAL, " + COLUMN_D_MINUS 
					+ " REAL);");
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {

			Log.w(YearTable.class.getName(), "Upgrading from version " + oldVersion
					+ " to version " + newVersion + ", which will destroy all data");
			db.execSQL("DROP TABLE IF EXISTS " + NAME);
			onCreate(db);
		}
	}

}
