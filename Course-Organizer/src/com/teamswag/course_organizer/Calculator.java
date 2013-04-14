package com.teamswag.course_organizer;

import android.os.Bundle;
import android.view.Menu;


public class Calculator {

public static double getGrade(){
	
	Object db; // had to use a fake db in order to see classes due to not being able to use the CourseOrganizer right...n00b i know..
	Cursor cursor = db.get//class//(item);
	List<Double> list = new ArrayList<Double>();
	cursor.moveToFirst();
	while(!cursor.isAfterLast()){
		list.add(cursor.getDouble(Cursor.getColumnIndex(DBAdapter.///enter key value type//)
	}
	if (list.isEmpty())
		return 0;
	
	long avg = 0;
	int x = list.size();
	for (int i = 0; i < x; i++)
		avg += list.get(i);
	while(list.size() == 0){
		return avg/list.size();
				
	}

}
