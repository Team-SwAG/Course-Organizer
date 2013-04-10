package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class CourseActivity extends ListActivity {

	ArrayList<String> courseList = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		
		ArrayAdapter<String> cc = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, courseList);
		setListAdapter(cc);
		cc.add("CSC 4900");
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent criteria = new Intent(CourseActivity.this,CriteriaActivity.class);
		startActivity(criteria);
	}

	public void plusCourse(View view) {
//		LayoutInflater inflater = LayoutInflater.from(this);
//		View addView = inflater.inflate(R.layout.activity_inputyear, null);
//
//		new AlertDialog.Builder(this).setTitle("Add a Year").setView(addView)
//				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						processAdd(((EditText)findViewById(R.id.et_year)).getText().toString());
//					}
//				}).setNegativeButton("Cancel", null).show();
	}

//	@Override
//	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
//			long arg3) {
//		new AlertDialog.Builder(this).setTitle("Confirm Delete")
//				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//
//					}
//				}).setNegativeButton("Cancel", null).show();
//		return true;
//	}
//
	private void processAdd(String s) {
//		ContentValues cv = new ContentValues(1);
//
//		cv.put(DatabaseHelper.YEAR, s);
//		db.getWritableDatabase().insert("year", DatabaseHelper.YEAR, cv);
//		cursor.requery();
	}

	private void processDelete(long rowId) {
//		String[] args = { String.valueOf(rowId) };
//		db.getWritableDatabase().delete("year", "_ID=?", args);
	}
}
