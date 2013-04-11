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
import android.widget.TextView;

public class CourseActivity extends ListActivity {

	ArrayList<String> courseList = new ArrayList<String>();
	TextView changePathYear;
	TextView changePathSemester;
	String yearPath;
	String semesterPath;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		
		ArrayAdapter<String> cc = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, courseList);
		setListAdapter(cc);
		cc.add("CSC 4900");
		
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			semesterPath = bundle.getString("semesterP");
			yearPath = bundle.getString("yearP");

		}
		
		changePathYear = (TextView) findViewById(R.id.tv_courseyear);
		
		changePathYear.setText(
			    yearPath);
		changePathSemester = (TextView) findViewById(R.id.tv_coursesemester);
		
		changePathSemester.setText(" -> " +
			    semesterPath);
		
		
	}
	
	public void pathYear (View v){
		Intent path = new Intent(CourseActivity.this, YearActivity.class);
		startActivity(path);;
	}
	
	public void pathSemester (View v){
		Intent path = new Intent(CourseActivity.this, SemesterActivity.class);
		path.putExtra("yearP", yearPath);
		startActivity(path);;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String coursePath = courseList.get(position);
		Intent criteria = new Intent(CourseActivity.this,CriteriaActivity.class);
		criteria.putExtra("yearP", yearPath);
		criteria.putExtra("semesterP", semesterPath);
		criteria.putExtra("courseP", coursePath);
		startActivity(criteria);
	}

	public void plusCourse(View view) {
	    AlertDialog.Builder b = new AlertDialog.Builder(this);
	    b.setTitle("Add a Course");
	    final EditText input = new EditText(this);
	    b.setView(input);
	    b.setPositiveButton("OK", new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int whichButton)
	        {
	           // SHOULD NOW WORK
	           processAdd(input.getText().toString());
	        }
	    });
	    b.setNegativeButton("CANCEL", null);
	    b.create().show();
		
		
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
		courseList.add(s);
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
