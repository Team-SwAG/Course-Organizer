package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.database.Cursor;

public class GpaCalculator {
	static double A_plus = 4.00;
	static double A = 4.00;
	static double A_minus = 3.70;
	static double B_plus = 3.33;
	static double B = 3.00;
	static double B_minus = 2.70;
	static double C_plus = 2.30;
	static double C = 2.00;
	static double C_minus = 1.70;
	static double D_plus = 1.30;
	static double D = 1.00;
	static double D_minus = 0.70;
	static double F = 0;

	private static double getScore(ArrayList<Course> list, DatabaseHelper db) {
		double GPA;
		double gradePoints;
		int totalHours;
		for (int i = 0; i < list.size(); i++) {
			int creditHours;


			Course course = list.get(i);
			// Grade and creditHours need to be stored in the DB
			Cursor cursor = db.getReadableDatabase().rawQuery(
					"SELECT " + CourseTable.COLUMN_GRADE + " FROM "
							+ CourseTable.NAME + " WHERE "
							+ CourseTable.COLUMN_ID + "=\'" + course.id + "\'",
					null);

			int count = cursor.getCount();
			if (count <= 0)
				continue;

			String grade = course.grade.toString();

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				gradePoints += getPoints(grade, creditHours);}
				cursor.moveToNext();
			}

		return (gradePoints / totalHours);

	}

public static double getPoints(String grade, int creditHours){
	if ((grade == "F")) {
		return F* creditHours;
	} else if (grade == "D-") {
		return D_minus* creditHours;
	} else if (grade == "D") {
		return D* creditHours;
	} else if (grade == "D+") {
		return D_plus* creditHours;
	} else if (grade == "C-") {
		return C_minus* creditHours;
	} else if (grade == "C") {
		return C* creditHours;
	} else if (grade == "C+") {
		return C_plus* creditHours;
	} else if (grade == "B-") {
		return B_minus* creditHours;
	} else if (grade == "B") {
		return B* creditHours;
	} else if (grade == "B+") {
		return B_plus* creditHours;
	} else if (grade == "A-") {
		return A_minus* creditHours;
	} else if (grade == "A") {
		return A* creditHours;
	} else {
		return A_plus* creditHours;
	}
}

	class Course {
		protected String name;
		protected String creditHour;
		protected String id;
		protected String grade;

		public Course(String name, String creditHour, String grade, String id) {
			this.creditHour = creditHour;
			this.grade = grade;
			this.id = id;
		}
	}
}