package com.teamswag.course_organizer;

public class GradeCalcObject {
	
	public String criteriaName = null;
	public int numItems = 0;
	
	void setCrit(String x) {
		String criteriaName = x;
	}
	
	String getCrit(){
		return criteriaName;
	}
	
	void setItems(int y){
		int numItems = y;
	}
	
	public int getItems(){
		return numItems;
	}
}


