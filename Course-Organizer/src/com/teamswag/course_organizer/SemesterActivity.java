package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SemesterActivity extends ListActivity {

	ArrayList<String> semesterList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semester);
		
		ArrayAdapter<String> bb = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, semesterList);
		setListAdapter(bb);
		bb.add("Fall");

	}
	@Override
	public void onResume() {
		super.onResume();
	}

	public void plusSemester(View view) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View addView = inflater.inflate(R.layout.activity_inputsemester, null);

		new AlertDialog.Builder(this).setTitle("Add a Semester").setView(addView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						processAdd(((EditText)findViewById(R.id.et_semester)).getText().toString());
					}
				}).setNegativeButton("Cancel", null).show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent course = new Intent(SemesterActivity.this,CourseActivity.class);
		startActivity(course);
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

	private void processAdd(String s) {
		ContentValues cv = new ContentValues(1);

//		cv.put(DatabaseHelper.YEAR, s);
//		db.getWritableDatabase().insert("year", DatabaseHelper.YEAR, cv);
//		cursor.requery();
	}

	private void processDelete(long rowId) {
//		String[] args = { String.valueOf(rowId) };
//		db.getWritableDatabase().delete("year", "_ID=?", args);
	}

	/* Removed the following and included the same dialog prompt as plusYear in YearActivity */
//	private void plusSemester() {
//			final View addView=getLayoutInflater().inflate(R.layout.activity_inputsemester, null);
//			
//			new AlertDialog.Builder(this)
//				.setTitle("Add a Semester")
//				.setView(addView)
//				.setPositiveButton("Ok",
//									new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						ArrayAdapter<String> adapter = (ArrayAdapter<String>)getListAdapter();
//						EditText title = (EditText)addView.findViewById(R.id.tv_semester);
//						
//						adapter.add(title.getText().toString());
//					}
//				})
//				.setNegativeButton("Cancel", null)
//				.show();
//										
//								
//	}
}

