package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CriteriaActivity extends ListActivity {
	
	ArrayList<String> criteriaList = new ArrayList<String>();
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criteria);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, criteriaList);
		setListAdapter(aa);
		aa.add("bullshit");
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
	}
}
