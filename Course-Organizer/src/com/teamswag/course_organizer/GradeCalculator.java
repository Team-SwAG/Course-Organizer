package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.database.Cursor;

public class GradeCalculator {

	public static String getGrade(String courseId, DatabaseHelper db) {
		Cursor cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + CriteriaTable.COLUMN_NAME + ", "
						+ CriteriaTable.COLUMN_WEIGHT + " FROM ", null);
		ArrayList<Double> weightList = new ArrayList<Double>();
		cursor.moveToFirst();
		if (weightList.isEmpty())
			return "";

		double avg = 0;
		for (int i = 0; i < weightList.size(); i++)
			avg += weightList.get(i);
		return "";		

	}
}
