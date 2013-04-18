package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.database.Cursor;

public class GradeCalculator {

	public static String getGrade(String courseId, DatabaseHelper db) {
		Cursor cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + CriteriaTable.COLUMN_NAME + ", " + CriteriaTable.COLUMN_WEIGHT + " FROM "
						+ CriteriaTable.NAME + " WHERE "
						+ CriteriaTable.COLUMN_COURSE_ID + "=\'" + courseId
						+ "\'", null);
		ArrayList<Double> weightList = new ArrayList<Double>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast())
			weightList.add(Double.parseDouble(cursor.getString(0)));

		// Add weightedAverage, weight variable
		String gradeAverage;
		double average = 0;

		for (int i = 0; i <= weightList.size(); i++)
			average += weightList.get(i);
			average = average / weightList.size();
			weightedAverage = average * weight;
			gradeAverage = Double.toString(weightedAverage);
		return "";

	}
}
